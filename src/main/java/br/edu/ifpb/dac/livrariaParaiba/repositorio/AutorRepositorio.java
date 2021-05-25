package br.edu.ifpb.dac.livrariaParaiba.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long>{
	
	public Autor findUniqueByNome(String nome);
	
	public List<Autor> findByNome(String nome);
	
	public Autor findByID(long ID);

}
