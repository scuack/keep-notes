package com.example.todo.respositories;

import com.example.todo.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.idNote = :idNote AND n.user.idUser = :idUser")
    Optional<Note> findByIdNoteAndIdUser(@Param("idNote") Long idNote,@Param("idUser") Long idUser);

    @Query("SELECT n FROM Note n WHERE n.user.idUser = :idUser")
    List<Note> findAllByIdUser(@Param("idUser") Long idUser);

    @Query("SELECT n FROM Note n WHERE n.user.idUser = :idUser")
    Optional<Note> createNoteForUser(@Param("idUser") Long idUser);
}
