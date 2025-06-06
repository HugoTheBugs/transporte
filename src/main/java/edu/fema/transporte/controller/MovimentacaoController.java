package edu.fema.transporte.controller;

import edu.fema.transporte.dto.AbastecimentoDto;
import edu.fema.transporte.dto.MovimentacaoDto;
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
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;
    private final VeiculoService veiculoService;
    private final MotoristaService motoristaService;
    private final ItinerarioService itinerarioService;

    @GetMapping
    public String getAllMovimentacoes(Model model,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim,
                                      @RequestParam(required = false) Long veiculoId,
                                      @RequestParam(required = false) Long motoristaId,
                                      @RequestParam(required = false) Long itinerarioId,
                                      @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<MovimentacaoDto> movimentacoes = movimentacaoService.buscarComFiltros(dataInicio, dataFim, veiculoId, motoristaId, itinerarioId, pageable);

        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("veiculoId", veiculoId);
        model.addAttribute("motoristaId", motoristaId);
        model.addAttribute("itinerarioId", itinerarioId);
        carregarDadosRelacionados(model);
        return "movimentacoes/movimentacaoList";
    }

    @GetMapping("/create")
    public String createMovimentacaoForm(Model model) {
        MovimentacaoDto movimentacaoDto = new MovimentacaoDto();
        model.addAttribute("movimentacao", movimentacaoDto);

        carregarDadosRelacionados(model);

        return "movimentacoes/createMovimentacao";
    }

    @PostMapping
    public String saveMovimentacao(@ModelAttribute("movimentacao") MovimentacaoDto movimentacaoDto) {
        movimentacaoService.createMovimentacao(movimentacaoDto);
        return "redirect:/movimentacoes?success";
    }

    @GetMapping("/edit/{id}")
    public String editMovimentacaoForm(@PathVariable Long id, Model model) {
        MovimentacaoDto movimentacaoDto = movimentacaoService.getMovimentacaoById(id);
        model.addAttribute("movimentacao", movimentacaoDto);

        carregarDadosRelacionados(model);

        return "movimentacoes/editMovimentacao";
    }

    @PostMapping("/{id}")
    public String updateMovimentacao(@PathVariable Long id, @ModelAttribute("movimentacao") MovimentacaoDto movimentacaoDto) {
        movimentacaoService.updateMovimentacao(id, movimentacaoDto);
        return "redirect:/movimentacoes?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovimentacao(@PathVariable Long id) {
        movimentacaoService.deleteMovimentacao(id);
        return "redirect:/movimentacoes?deleted";
    }

    @GetMapping("/veiculo/{veiculoId}")
    public String getByVeiculo(@PathVariable Long veiculoId, Model model) {
        List<MovimentacaoDto> movimentacoes = movimentacaoService.findByVeiculoId(veiculoId);
        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("veiculoSelecionado", veiculoService.getVeiculoById(veiculoId).getPlaca());
        return "movimentacoes/movimentacaoList";
    }

    @GetMapping("/motorista/{motoristaId}")
    public String getByMotorista(@PathVariable Long motoristaId, Model model) {
        List<MovimentacaoDto> movimentacoes = movimentacaoService.findByMotoristaId(motoristaId);
        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("motoristaSelecionado", motoristaService.getMotoristaById(motoristaId).getNome());
        return "movimentacoes/movimentacaoList";
    }

    @GetMapping("/itinerario/{itinerarioId}")
    public String getByItinerario(@PathVariable Long itinerarioId, Model model) {
        List<MovimentacaoDto> movimentacoes = movimentacaoService.findByItinerarioId(itinerarioId);
        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("itinerarioSelecionado", itinerarioService.getItinerarioById(itinerarioId).getDescricao());
        return "movimentacoes/movimentacaoList";
    }

    private void carregarDadosRelacionados(Model model) {
        model.addAttribute("veiculos", veiculoService.getAllVeiculos());
        model.addAttribute("motoristas", motoristaService.getAllMotoristas());
        model.addAttribute("itinerarios", itinerarioService.getAllItinerarios());
    }
}