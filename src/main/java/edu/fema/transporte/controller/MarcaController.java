package edu.fema.transporte.controller;
import edu.fema.transporte.dto.MarcaDto;
import edu.fema.transporte.service.MarcaService;
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
public class MarcaController {

    private MarcaService marcaService;

    @GetMapping("/marcas")
    public String getAllMarcas(Model model){
        List<MarcaDto> marcas = marcaService.getAllMarcas();
        model.addAttribute("marcas", marcas);
        return "marcas/marcaList";
    }

    @PostMapping("/marcas")
    public String saveMarca(@ModelAttribute("marca") MarcaDto marcaDto, Model model){
        marcaService.createMarca(marcaDto);
        return "redirect:/marcas?success";
    }

    @GetMapping("/createMarca")
    public String createMarcaForm(Model model){
        MarcaDto marcaDto = new MarcaDto();
        model.addAttribute("marca", marcaDto);
        return "marcas/createMarca.html";
    }

    @GetMapping("/marcas/delete/{id}")
    public String deleteMarca(@PathVariable ("id") Long id){
        marcaService.deleteMarca(id);
        return "redirect:/marcas";
    }

    @GetMapping("/marcas/edit/{id}")
    public String editMarca(@PathVariable ("id") Long id, Model model){
        MarcaDto marcaDto = marcaService.getMarcaById(id);
        model.addAttribute("marca", marcaDto);
        return "marcas/editMarca";
    }

    @PostMapping("/marcas/{id}")
    public String updateMarca(@PathVariable("id")  Long id, @ModelAttribute("marca") MarcaDto marcaDto){
        marcaDto.setId(id);
        marcaService.updateMarca(marcaDto);
        return "redirect:/marcas?updated";
    }

}
