package mx.com.edu.utez.ultima.model.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<BeanTicket, Long> {
}
