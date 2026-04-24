package mx.com.edu.utez.ultima.service;

import mx.com.edu.utez.ultima.model.ticket.BeanTicket;
import mx.com.edu.utez.ultima.model.ticket.EstadoTicket;
import mx.com.edu.utez.ultima.model.ticket.TicketRepository;
import mx.com.edu.utez.ultima.model.usuario.BeanUsuario;
import mx.com.edu.utez.ultima.model.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GeneraleService {
    private TicketRepository ticketRepository;
    private UsuarioRepository usuarioRepository;

    public GeneraleService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public BeanTicket crearTicket(Long usuarioId,  String descripcion, String priodad){
        BeanUsuario usuario = usuarioRepository.findById(usuarioId).get();

        if(!usuario.isActivo())
            throw new RuntimeException("El usuario no está activo");

        if (descripcion == null )
            throw new RuntimeException("La descripción es obligatoria");

        BeanTicket ticket = new BeanTicket();
        ticket.setId(usuarioId);
        ticket.setDescripcion(descripcion);
        ticket.setUsuario(usuario);
        ticket.setEstado(EstadoTicket.ABIERTO);

        return ticketRepository.save(ticket);
    }
        public BeanTicket actualizarEstadoTicket(Long ticketId){
            BeanTicket ticket = ticketRepository.findById(ticketId).get();

            if (ticket.getEstado() == EstadoTicket.ABIERTO) {
                ticket.setEstado(EstadoTicket.EN_PROCESO);
            } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
                ticket.setEstado(EstadoTicket.CERRADO);
            } else {
                throw new RuntimeException("El ticket no se puede modificar");
            }
        return ticketRepository.save(ticket);
}
    public List<BeanTicket> obtenerPorUsuario(Long usuarioId) {
        return ticketRepository.findAllById(Collections.singleton(usuarioId));

    }
    public BeanTicket reasignarPrioridad(Long ticketId, String nueva) {
        BeanTicket ticket = ticketRepository.findById(ticketId).orElseThrow();

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new RuntimeException("No se puede cambiar prioridad a un ticket CERRADO");
        }

    }
}
