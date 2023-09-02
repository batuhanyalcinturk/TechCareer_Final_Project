package com.graysan;

import com.graysan.data.entity.ToDoItem;
import com.graysan.data.repository.ToDoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@RequiredArgsConstructor
@EnableWebMvc
public class TechcareerFinalProjectApplication implements CommandLineRunner {

	private final ToDoItemRepository toDoItemRepository;
	//private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TechcareerFinalProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*ToDoItem toDoItem = new ToDoItem();
		toDoItem.setId(1L);
		toDoItem.setTitle("Baslik");
		toDoItem.setCompleted(true);

		toDoItemRepository.save(toDoItem);*/
	}
}

