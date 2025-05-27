package edu.fema.transporte.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_motorista")
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnh;
    @Column(name = "cnh_categoria")
    private String cnhCategoria;
    @Column(name = "cnh_vencimento")
    private Date cnhVencimento;
    private String telefone;
    private String status;
}