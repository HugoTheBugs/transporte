package edu.fema.transporte.controller;

import edu.fema.transporte.dto.AbastecimentoDto;
import edu.fema.transporte.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;
    private final PostoService postoService;
    private final MotoristaService motoristaService;
    private final VeiculoService veiculoService;
    private final CombustivelService combustivelService;

    @GetMapping
    public String getAllAbastecimentos(Model model,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim,
                                       @RequestParam(required = false) Long veiculoId,
                                       @RequestParam(required = false) Long motoristaId,
                                       @RequestParam(required = false) Long postoId,
                                       @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AbastecimentoDto> abastecimentos = abastecimentoService.buscarComFiltros(dataInicio, dataFim, veiculoId, motoristaId, postoId, pageable);

        model.addAttribute("abastecimentos", abastecimentos);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("veiculoId", veiculoId);
        model.addAttribute("motoristaId", motoristaId);
        model.addAttribute("postoId", postoId);
        carregarDadosRelacionados(model);
        return "abastecimentos/abastecimentoList";
    }



    @GetMapping("/create")
    public String createAbastecimentoForm(Model model) {
        AbastecimentoDto abastecimentoDto = new AbastecimentoDto();
        model.addAttribute("abastecimento", abastecimentoDto);
        carregarDadosRelacionados(model);
        return "abastecimentos/createAbastecimento";
    }

    @PostMapping
    public String saveAbastecimento(@ModelAttribute("abastecimento") AbastecimentoDto abastecimentoDto) {
        abastecimentoService.createAbastecimento(abastecimentoDto);
        return "redirect:/abastecimentos?success";
    }

    @GetMapping("/edit/{id}")
    public String editAbastecimentoForm(@PathVariable Long id, Model model) {
        AbastecimentoDto abastecimentoDto = abastecimentoService.getAbastecimentoById(id);
        model.addAttribute("abastecimento", abastecimentoDto);
        carregarDadosRelacionados(model);
        return "abastecimentos/editAbastecimento";
    }

    @PostMapping("/{id}")
    public String updateAbastecimento(@PathVariable Long id, @ModelAttribute("abastecimento") AbastecimentoDto abastecimentoDto) {
        abastecimentoService.updateAbastecimento(id, abastecimentoDto);
        return "redirect:/abastecimentos?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteAbastecimento(@PathVariable Long id) {
        abastecimentoService.deleteAbastecimento(id);
        return "redirect:/abastecimentos?deleted";
    }

    @GetMapping("/posto/{postoId}")
    public String getByPosto(@PathVariable Long postoId, Model model) {
        List<AbastecimentoDto> abastecimentos = abastecimentoService.findByPostoId(postoId);
        model.addAttribute("abastecimentos", abastecimentos);
        model.addAttribute("postoSelecionado", postoService.getPostoById(postoId).getNome());
        return "abastecimentos/abastecimentoList";
    }

    @GetMapping("/motorista/{motoristaId}")
    public String getByMotorista(@PathVariable Long motoristaId, Model model) {
        List<AbastecimentoDto> abastecimentos = abastecimentoService.findByMotoristaId(motoristaId);
        model.addAttribute("abastecimentos", abastecimentos);
        model.addAttribute("motoristaSelecionado", motoristaService.getMotoristaById(motoristaId).getNome());
        return "abastecimentos/abastecimentoList";
    }

    @GetMapping("/veiculo/{veiculoId}")
    public String getByVeiculo(@PathVariable Long veiculoId, Model model) {
        List<AbastecimentoDto> abastecimentos = abastecimentoService.findByVeiculoId(veiculoId);
        model.addAttribute("abastecimentos", abastecimentos);
        model.addAttribute("veiculoSelecionado", veiculoService.getVeiculoById(veiculoId).getPlaca());
        return "abastecimentos/abastecimentoList";
    }

    @GetMapping("/combustivel/{combustivelId}")
    public String getByCombustivel(@PathVariable Long combustivelId, Model model) {
        List<AbastecimentoDto> abastecimentos = abastecimentoService.findByCombustivelId(combustivelId);
        model.addAttribute("abastecimentos", abastecimentos);
        model.addAttribute("combustivelSelecionado", combustivelService.getCombustivelById(combustivelId).getNome());
        return "abastecimentos/abastecimentoList";
    }

    private void carregarDadosRelacionados(Model model) {
        model.addAttribute("postos", postoService.getAllPostos());
        model.addAttribute("motoristas", motoristaService.getAllMotoristas());
        model.addAttribute("veiculos", veiculoService.getAllVeiculos());
        model.addAttribute("combustiveis", combustivelService.getAllCombustiveis());
    }
}