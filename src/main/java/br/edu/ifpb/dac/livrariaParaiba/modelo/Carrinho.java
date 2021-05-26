package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.util.ArrayList;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
/*
 * @author André Felipe
 */
@Embeddable
public class Carrinho {

	@ManyToMany (mappedBy = "carrinho")
	private ArrayList<Livro> lista;
	
	/*
	 * Adiciona um livro ao carrinho
	 */
	public void adicionarLivroAoCarrinho(Livro livro) {
		lista.add(livro);
	}
	
	/*
	 * remove um livro do carrinho pelo id
	 */
	public boolean removerLivroDoCarrinho(long id) {
		for(Livro l: lista) {
			if(l.getId()==id) {
				lista.remove(l);
				return true;
			}
		}
		return false;
	}
	/*
	 * Esvazeia o carrinho do usuario cliente
	 */
	public void esvaziarCarrinho() {
		lista.clear();
	}
}
