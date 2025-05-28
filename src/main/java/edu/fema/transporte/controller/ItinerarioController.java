package edu.fema.transporte.controller;

import edu.fema.transporte.dto.ItinerarioDto;
import edu.fema.transporte.service.ClienteService;
import edu.fema.transporte.service.ItinerarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ItinerarioController {

    private final ItinerarioService itinerarioService;
    private final ClienteService clienteService;

    @GetMapping("/itinerarios")
    public String getAllItinerarios(Model model) {
        List<ItinerarioDto> itinerarios = itinerarioService.getAllItinerarios();
        model.addAttribute("itinerarios", itinerarios);
        return "itinerarios/itinerarioList";
    }

    @GetMapping("/createItinerario")
    public String createItinerarioForm(Model model) {
        ItinerarioDto itinerarioDto = new ItinerarioDto();
        model.addAttribute("itinerario", itinerarioDto);

        var clientes = clienteService.getAllClientes();
        model.addAttribute("clientes", clientes);

        return "itinerarios/createItinerario";
    }

    @PostMapping("/itinerarios")
    public String saveItinerario(@ModelAttribute("itinerario") ItinerarioDto itinerarioDto) {
        itinerarioService.createItinerario(itinerarioDto);
        return "redirect:/itinerarios?success";
    }

    @GetMapping("/itinerarios/edit/{id}")
    public String editItinerarioForm(@PathVariable Long id, Model model) {
        ItinerarioDto itinerarioDto = itinerarioService.getItinerarioById(id);
        model.addAttribute("itinerario", itinerarioDto);
        model.addAttribute("clientes", clienteService.getAllClientes()); // Adicione esta linha
        return "itinerarios/editItinerario";
    }

    @PostMapping("/itinerarios/{id}")
    public String updateItinerario(@PathVariable Long id, @ModelAttribute ItinerarioDto itinerarioDto) {
        itinerarioService.updateItinerario(id, itinerarioDto);
        return "redirect:/itinerarios?updated";
    }

    @GetMapping("/itinerarios/delete/{id}")
    public String deleteItinerario(@PathVariable Long id) {
        itinerarioService.deleteItinerario(id);
        return "redirect:/itinerarios?deleted";
    }
}