package edu.fema.transporte.controller;

import edu.fema.transporte.dto.VeiculoDto;
import edu.fema.transporte.service.CombustivelService;
import edu.fema.transporte.service.MarcaService;
import edu.fema.transporte.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final MarcaService marcaService;
    private final CombustivelService combustivelService;

    @GetMapping
    public String getAllVeiculos(Model model) {
        List<VeiculoDto> veiculos = veiculoService.getAllVeiculos();
        model.addAttribute("veiculos", veiculos);
        return "veiculos/veiculoList";
    }

    @GetMapping("/create")
    public String createVeiculoForm(Model model) {
        VeiculoDto veiculoDto = new VeiculoDto();
        model.addAttribute("veiculo", veiculoDto);

        model.addAttribute("marcas", marcaService.getAllMarcas());
        model.addAttribute("combustiveis", combustivelService.getAllCombustiveis());

        return "veiculos/createVeiculo";
    }

    @PostMapping
    public String saveVeiculo(@ModelAttribute("veiculo") VeiculoDto veiculoDto) {
        veiculoService.createVeiculo(veiculoDto);
        return "redirect:/veiculos?success";
    }

    @GetMapping("/edit/{id}")
    public String editVeiculoForm(@PathVariable Long id, Model model) {
        VeiculoDto veiculoDto = veiculoService.getVeiculoById(id);
        model.addAttribute("veiculo", veiculoDto);

        model.addAttribute("marcas", marcaService.getAllMarcas());
        model.addAttribute("combustiveis", combustivelService.getAllCombustiveis());

        return "veiculos/editVeiculo";
    }

    @PostMapping("/{id}")
    public String updateVeiculo(@PathVariable Long id, @ModelAttribute("veiculo") VeiculoDto veiculoDto) {
        veiculoService.updateVeiculo(id, veiculoDto);
        return "redirect:/veiculos?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteVeiculo(id);
        return "redirect:/veiculos?deleted";
    }

    // Métodos adicionais para filtros
    @GetMapping("/marca/{marcaId}")
    public String getVeiculosByMarca(@PathVariable Long marcaId, Model model) {
        List<VeiculoDto> veiculos = veiculoService.findByMarcaId(marcaId);
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("marcaSelecionada", marcaService.getMarcaById(marcaId).getNome());
        return "veiculos/veiculoList";
    }

    @GetMapping("/combustivel/{combustivelId}")
    public String getVeiculosByCombustivel(@PathVariable Long combustivelId, Model model) {
        List<VeiculoDto> veiculos = veiculoService.findByCombustivelId(combustivelId);
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("combustivelSelecionado", combustivelService.getCombustivelById(combustivelId).getNome());
        return "veiculos/veiculoList";
    }
}