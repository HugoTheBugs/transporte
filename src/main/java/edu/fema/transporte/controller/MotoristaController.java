package edu.fema.transporte.controller;
import edu.fema.transporte.dto.MotoristaDto;
import edu.fema.transporte.service.MotoristaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MotoristaController {

    private MotoristaService motoristaService;

    @GetMapping("/motoristas")
    public String getAllMotoristas(Model model){
        List<MotoristaDto> motoristas = motoristaService.getAllMotoristas();
        model.addAttribute("motoristas", motoristas);
        return "motoristas/motoristaList";
    }

    @PostMapping("/motoristas")
    public String saveMotorista(@ModelAttribute("motorista") MotoristaDto motoristaDto, Model model){
        motoristaService.createMotorista(motoristaDto);
        return "redirect:/motoristas?success";
    }

    @GetMapping("/createMotorista")
    public String createMotoristaForm(Model model){
        MotoristaDto motoristaDto = new MotoristaDto();
        model.addAttribute("motorista", motoristaDto);
        return "motoristas/createMotorista.html";
    }

    @GetMapping("/motoristas/delete/{id}")
    public String deleteMotorista(@PathVariable("id") Long id){
        motoristaService.deleteMotorista(id);
        return "redirect:/motoristas";
    }

    @GetMapping("/motoristas/edit/{id}")
    public String editMotorista(@PathVariable ("id") Long id, Model model){
        MotoristaDto motoristaDto = motoristaService.getMotoristaById(id);
        model.addAttribute("motorista", motoristaDto);
        return "motoristas/editMotorista";
    }

    @PostMapping("/motoristas/{id}")
    public String updateMotorista(@PathVariable("id")  Long id, @ModelAttribute("motorista") MotoristaDto motoristaDto){
        motoristaDto.setId(id);
        motoristaService.updateMotorista(motoristaDto);
        return "redirect:/motoristas?updated";
    }
}
