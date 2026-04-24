package mx.com.edu.utez.ultima.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<BeanUsuario, Long> {
}
