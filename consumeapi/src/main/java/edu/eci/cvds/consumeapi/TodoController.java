package edu.eci.cvds.consumeapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TodoController {

    @GetMapping("/todo")
    public String getTodo(Model model) {
        // Crear un cliente HTTP RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Hacer la solicitud GET a la API
        Todo todo = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", Todo.class);

        // Agregar los datos del todo al modelo
        model.addAttribute("todo", todo);

        // Devolver la vista correspondiente
        return "todo";
    }
}
