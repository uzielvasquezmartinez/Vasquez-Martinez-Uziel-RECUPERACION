package mx.com.edu.utez.ultima.model.ticket;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.edu.utez.ultima.model.usuario.BeanUsuario;

@Entity
@Table (name = "Tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanTicket {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private boolean activo;
        private String descripcion;
        private EstadoTicket estado;
    @ManyToOne
    @JoinColumn(name = "usuario_Id")
    private BeanUsuario usuario;
}
