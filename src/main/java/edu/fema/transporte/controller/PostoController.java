package edu.fema.transporte.controller;
import edu.fema.transporte.dto.PostoDto;
import edu.fema.transporte.service.PostoService;
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
public class PostoController {

    private PostoService postoService;

    @GetMapping("/postos")
    public String getAllPostos(Model model){
        List<PostoDto> postos = postoService.getAllPostos();
        model.addAttribute("postos", postos);
        return "postos/postoList";
    }

    @PostMapping("/postos")
    public String savePosto(@ModelAttribute("posto") PostoDto postoDto, Model model){
        postoService.createPosto(postoDto);
        return "redirect:/postos?success";
    }

    @GetMapping("/createPosto")
    public String createPostoForm(Model model){
        PostoDto postoDto = new PostoDto();
        model.addAttribute("posto", postoDto);
        return "postos/createPosto.html";
    }

    @GetMapping("/postos/delete/{id}")
    public String deletePosto(@PathVariable("id") Long id){
        postoService.deletePosto(id);
        return "redirect:/postos";
    }

    @GetMapping("/postos/edit/{id}")
    public String editPosto(@PathVariable ("id") Long id, Model model){
        PostoDto postoDto = postoService.getPostoById(id);
        model.addAttribute("posto", postoDto);
        return "postos/editPosto";
    }

    @PostMapping("/postos/{id}")
    public String updatePosto(@PathVariable("id")  Long id, @ModelAttribute("posto") PostoDto postoDto){
        postoDto.setId(id);
        postoService.updatePosto(postoDto);
        return "redirect:/postos?updated";
    }
}
