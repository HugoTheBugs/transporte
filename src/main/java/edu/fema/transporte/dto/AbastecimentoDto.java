package edu.fema.transporte.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbastecimentoDto {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    private Double kmAtual;
    private Double litros;
    private Double valorTotal;
    private Long postoId;
    private String postoNome;
    private Long motoristaId;
    private String motoristaNome;
    private Long veiculoId;
    private String veiculoPlaca;
    private Long combustivelId;
    private String combustivelNome;
}