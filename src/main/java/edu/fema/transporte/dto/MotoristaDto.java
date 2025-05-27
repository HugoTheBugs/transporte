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
public class MotoristaDto {
    private Long id;
    private String nome;
    private String cnh;
    private String cnhCategoria;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cnhVencimento;
    private String telefone;
    private String status;
}