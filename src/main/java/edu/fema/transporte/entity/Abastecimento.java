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
@Table(name="tb_abastecimento")
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    @Column(name = "km_atual")
    private Double kmAtual;
    private Double litros;
    @Column(name = "valor_total")
    private Double valorTotal;
    @ManyToOne
    @JoinColumn(name = "posto_id")
    private Posto posto;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "combustivel_id")
    private Combustivel combustivel;
}