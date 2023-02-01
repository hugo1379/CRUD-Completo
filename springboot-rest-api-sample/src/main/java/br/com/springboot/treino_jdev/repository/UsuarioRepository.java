package br.com.springboot.treino_jdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.treino_jdev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value ="select u from Usuario u where upper (trim(u.nome)) like %?1%")/*O trim retira o espaço do nome e coloca em maiuscula quando for consultar no banco,vai ignorar se é maiusculo ou minuscol e se tem espaço ou não*/
	List<Usuario> buscarPorNome(String name);
}
