package edu.fema.transporte.controller;

import edu.fema.transporte.dto.ClienteDto;
import edu.fema.transporte.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String getAllClientes(Model model){
        List<ClienteDto> clientes = clienteService.getAllClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/clienteList";
    }

    @PostMapping("/clientes")
    public String saveCliente(@ModelAttribute("cliente") ClienteDto clienteDto, Model model){
        clienteService.createCliente(clienteDto);
        return "redirect:/clientes?success";
    }

    @GetMapping("/createCliente")
    public String createClienteForm(Model model){
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("cliente", clienteDto);
        return "clientes/createCliente.html";
    }

}
