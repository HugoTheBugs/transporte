package edu.fema.transporte.controller;
import edu.fema.transporte.dto.CombustivelDto;
import edu.fema.transporte.service.CombustivelService;
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
public class CombustivelController {

    private CombustivelService combustivelService;

    @GetMapping("/combustiveis")
    public String getAllCombustiveis(Model model){
        List<CombustivelDto> combustiveis = combustivelService.getAllCombustiveis();
        model.addAttribute("combustiveis", combustiveis);
        return "combustiveis/combustivelList";
    }

    @PostMapping("/combustiveis")
    public String saveCombustivel(@ModelAttribute("combustivel") CombustivelDto combustivelDto, Model model){
        combustivelService.createCombustivel(combustivelDto);
        return "redirect:/combustiveis?success";
    }

    @GetMapping("/createCombustivel")
    public String createCombustivelForm(Model model){
        CombustivelDto combustivelDto = new CombustivelDto();
        model.addAttribute("combustivel", combustivelDto);
        return "combustiveis/createCombustivel.html";
    }

    @GetMapping("/combustiveis/delete/{id}")
    public String deleteCombustivel(@PathVariable ("id") Long id){
        combustivelService.deleteCombustivel(id);
        return "redirect:/combustiveis";
    }

    @GetMapping("/combustiveis/edit/{id}")
    public String editCombustivel(@PathVariable ("id") Long id, Model model){
        CombustivelDto combustivelDto = combustivelService.getCombustivelById(id);
        model.addAttribute("combustivel", combustivelDto);
        return "combustiveis/editCombustivel";
    }

    @PostMapping("/combustiveis/{id}")
    public String updateCombustivel(@PathVariable("id")  Long id, @ModelAttribute("combustivel") CombustivelDto combustivelDto){
        combustivelDto.setId(id);
        combustivelService.updateCombustivel(combustivelDto);
        return "redirect:/combustiveis?updated";
    }

}
