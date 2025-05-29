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
public class MovimentacaoDto {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    private double kmInicial;
    private double kmFinal;
    private Long veiculoId;
    private String veiculoPlaca;
    private Long motoristaId;
    private String motoristaNome;
    private Long itinerarioId;
    private String itinerarioDescricao;
}