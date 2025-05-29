package edu.fema.transporte.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDto {
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private float tanqueCapacidade;
    private String categoria;
    private String status;
    private Long marcaId;
    private String marcaNome;
    private Long combustivelId;
    private String combustivelNome;
}