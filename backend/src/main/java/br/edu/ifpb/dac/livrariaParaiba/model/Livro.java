package br.edu.ifpb.dac.livrariaParaiba.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	private List<ItemCarrinho> itemCarrinho = new ArrayList<>();

	@NotNull
	@Min(0)
	private Integer quantidade;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Autor> autores = new ArrayList<>();

	@NotNull
	@Min(1)
	private Integer edicao;

	@Enumerated(EnumType.STRING)
	private GenerosTipos genero;

	@NotNull
	@Size(min = 1, max = 5633)
	private String nome;

	@NotNull
	private BigDecimal valor;

	@NotBlank
	@Size(max = 1500)
	private String descricao;

	@NotNull
	@Size(min = 10, max = 13)
	private String isbn;

	@NotNull
	@Min(1)
	private Integer nPaginas;

	public Livro() {
	}

	public Livro(List<Autor> autores, Integer edicao, GenerosTipos genero, String nome, BigDecimal valor,
			String descricao, String isbn, Integer nPaginas) {
		super();
		this.edicao = edicao;
		this.genero = genero;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = 0;
		this.descricao = descricao;
		this.isbn = isbn;
		this.autores = autores;
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

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String toString() {
		return "\n" + "ID [" + id + "]" + "\n" + "Titulo: " + nome + "\n" + autores.toString() + "\n" + "Descrição: "
				+ descricao + "\n" + "Edição: " + edicao + "\n" + "ISBN " + isbn + "\n" + "Quantidade de Paginas "
				+ nPaginas + "\n" + "R$" + valor + "\n" + "Estoque: " + quantidade + "\n";
	}

	public void addAutor(Autor a) {
		a.addLivro(this);
		autores.add(a);
	}

	public void removerAutor(Integer indice) {
		autores.remove(indice.intValue());
	}
}
