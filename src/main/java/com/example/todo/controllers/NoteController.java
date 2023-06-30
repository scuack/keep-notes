package com.example.todo.controllers;

import com.example.todo.entities.Note;
import com.example.todo.entities.User;
import com.example.todo.respositories.NoteRepository;
import com.example.todo.respositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired

    private UserRepository userRepository;

    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{idUser}/note")
    public ResponseEntity<List<Note>> noteUserFindAll(@PathVariable Long idUser){
        Optional<List<Note>> noteOptional = Optional.ofNullable(noteRepository.findAllByIdUser(idUser));
        if (noteOptional.isPresent()){
            return ResponseEntity.ok(noteOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{idUser}/note/{idNote}")
    public ResponseEntity<Note> noteUserFind(@PathVariable Long idUser, @PathVariable Long idNote){
        Optional<Note> noteOptional = noteRepository.findByIdNoteAndIdUser(idNote, idUser);
        if (noteOptional.isPresent()){
            return ResponseEntity.ok(noteOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/{idUser}/note")
    public ResponseEntity<Note> createNote (@PathVariable("idUser") Long idUser, @RequestBody Note note, @RequestHeader HttpHeaders header){
        Optional<User> user = userRepository.findById(idUser);
        note.setUser(user.get());
        if(note.getIdNote() != null){
            log.warn("Note already exists");
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(noteRepository.save(note));
        }
    }
}
