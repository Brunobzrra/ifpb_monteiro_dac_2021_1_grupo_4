package br.edu.ifpb.dac.livrariaParaiba.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

/**
 * Classe que representa objetos do tipo livro
 * 
 * @author bruno
 * 
 */

@Entity
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy = "livro")
	private List<ItemCarrinho> itemCarrinho;

	private Integer quantidade;

	@ManyToMany
	private List<Autor> autores;

	private Integer edicao;

	@Enumerated(EnumType.STRING)
	private GenerosTipos genero;

	@NotNull
	private String nome;

	@NotNull
	private BigDecimal valor;

	private String descricao;

	@NotNull
	private String isbn;

	private Integer nPaginas;
	
	public Livro() {
		
	}

	public Livro(Integer quantidade, List<Autor> autores, Integer edicao, GenerosTipos genero, String nome,
			BigDecimal valor, String descricao, String isbn, Integer nPaginas) {
		super();
		this.quantidade = quantidade;
		this.edicao = edicao;
		this.genero = genero;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.isbn = isbn;
		this.nPaginas = nPaginas;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public GenerosTipos getGenero() {
		return genero;
	}

	public void setGenero(GenerosTipos genero) {
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getnPaginas() {
		return nPaginas;
	}

	public void setnPaginas(Integer nPaginas) {
		this.nPaginas = nPaginas;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidadeEstoque(Integer quantidade) {
		this.quantidade += quantidade;
	}

	public void removerDoEstoque() {
		quantidade--;
	}

}