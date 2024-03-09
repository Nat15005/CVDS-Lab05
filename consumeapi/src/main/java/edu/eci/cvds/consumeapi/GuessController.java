package edu.eci.cvds.consumeapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class GuessController {

    private int targetNumber;
    private int prize;

    @GetMapping("/guess")
    public String guessForm(Model model) {
        // Generar un nuevo número objetivo para adivinar
        targetNumber = new Random().nextInt(10) + 1;
        // Establecer el premio inicial
        prize = 100000;
        // Pasar el premio y el número objetivo al modelo
        model.addAttribute("prize", prize);
        model.addAttribute("targetNumber", targetNumber);
        return "guess";
    }

    @PostMapping("/guess")
    public String handleGuess(@RequestParam(name = "number") int number, Model model) {
        // Verificar si el número ingresado por el usuario coincide con el objetivo
        if (number == targetNumber) {
            // El usuario adivinó el número, mostrar mensaje de éxito y premio
            model.addAttribute("message", "¡Felicidades! Has adivinado el número y ganaste $" + prize + ".");
            model.addAttribute("prize", prize);
        } else {
            // El usuario no adivinó el número, reducir el premio y mostrar mensaje de intento fallido
            prize -= 10000;
            model.addAttribute("message", "Número incorrecto. Sigue intentando.");
            model.addAttribute("prize", prize);
        }
        // Pasar el número objetivo al modelo
        model.addAttribute("targetNumber", targetNumber);
        return "guess";
    }


    @GetMapping("/reset")
    public String resetGame() {
        // Redirigir al usuario a la página de inicio del juego
        return "redirect:/guess";
    }
}
