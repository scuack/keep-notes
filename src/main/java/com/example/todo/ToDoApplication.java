package com.example.todo;

import com.example.todo.entities.Note;
import com.example.todo.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ToDoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ToDoApplication.class, args);


		User user1 = new User(null, "Diego", "diego@gmail.com", "clave", null);
		User user2 = new User(null, "Marta", "marta@gmail.com", "clave2", null);

		Note note1 = new Note(null, user1, "Prueba", "lorem ipsum dolor sit amet, consectetur adipis");
		Note note2 = new Note(null, user1, "Prueba2", "lorem ipsum dolor sit amet, consectetur adipis");
		Note note3 = new Note(null, user2, "Prueba3", "lorem ipsum dolor sit amet, consectetur adipis");


	}

}
