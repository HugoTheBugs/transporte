package edu.fema.transporte.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDto {
    private Long id;
    private String descricao;
    private Long clienteId;
    private String clienteNome;
}
