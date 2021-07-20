package br.edu.ifpb.dac.livrariaParaiba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.model.GenerosTipos;
import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;
import br.edu.ifpb.dac.livrariaParaiba.service.EnderecoService;
import br.edu.ifpb.dac.livrariaParaiba.service.ItemCarrinhoService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@SpringBootApplication
public class LivrariaParaibaApplication implements CommandLineRunner {

	private ClienteService clienteService;
	private EnderecoService enderecoService;
	private ItemCarrinhoService carrinhoService;
	private LivroService livroService;
	private AutorService autorService;

	public LivrariaParaibaApplication(ClienteService clienteService, EnderecoService enderecoService,
			ItemCarrinhoService carrinhoService, LivroService livroService, AutorService autorService) {
		this.clienteService = clienteService;
		this.enderecoService = enderecoService;
		this.carrinhoService = carrinhoService;
		this.livroService = livroService;
		this.autorService = autorService;

	}

	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean aux = true;
		Scanner leitor = new Scanner(System.in);
		while (aux) {
			System.out.println("Escolha uma opção" + "\n1 - Adicionar Usuário" + "\n2 - Consultar Usuário por Email"
					+ "\n3 - Cadastrar Autor" + "\n4 - Editar Autor" + "\n5 - Cadastar Livro" + "\n6 - Editar Livro"
					+ "\n7 - Excluir Livro" + "\n8 - Cadastrar livro no Estoque"
					+ "\n9 - Cosnultar os 5 Livros mais Baratos" + "\n10 - Consultar todos os livros"
					+ "\n11 - Adicionar Livro ao Carrinho");
			int n = leitor.nextInt();
			Cliente c = new Cliente();

			switch (n) {
			case 0:

		

				break;
			case 1:
				leitor = new Scanner(System.in);
				Endereco endereco = new Endereco();
				System.out.println("Digite seus dados");
				String cond = leitor.nextLine();
				System.out.println("Digite seu CPF: ");
				String cpf = leitor.nextLine();
				System.out.println("Digite seu nome: ");
				String nome = leitor.nextLine();
				System.out.println("Digite o dia, mês e ano em que nasceu (dd/mm/aaaa): ");
				String nascimento = leitor.nextLine();
				System.out.println("Digite seu email: ");
				String email = leitor.nextLine();
				System.out.println("Digite sua senha: ");
				String senha = leitor.nextLine();
				System.out.println("Digite o estado onde reside: ");
				String estado = leitor.nextLine();
				System.out.println("Digite a cidade: ");
				String cidade = leitor.nextLine();
				System.out.println("Digite seu bairro: ");
				String bairro = leitor.nextLine();
				System.out.println("Digite sua rua: ");
				String rua = leitor.nextLine();
				System.out.println("Digite o numero da residência: ");
				int numero = leitor.nextInt();

				// set dos atributos
				c.setCpf(cpf);
				c.setNome(nome);
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(nascimento);
				c.setNascimento(data);
				c.setUsername(email);
				c.setSenha(senha);
				endereco.setBairro(bairro);
				endereco.setCidade(cidade);
				endereco.setEstado(estado);
				endereco.setNumero(numero);
				endereco.setRua(rua);
				c.setEndereco(endereco);
				endereco.setCliente(c);
				clienteService.salvarCliente(c);
				enderecoService.salvarEndereco(endereco);
				break;
			case 2:
				leitor = new Scanner(System.in);
				System.out.println("Digite seus dados");
				cond = leitor.nextLine();
				System.out.println("Digite o email: ");
				email = leitor.nextLine();
				c = clienteService.pesquisarPorEmail(email);
				System.out.println(c.getNome());

				break;

			case 3:
				leitor = new Scanner(System.in);
				Autor autorNovo = new Autor();
				System.out.println("Digite seus dados");
				cond = leitor.nextLine();
				System.out.print("Nome do autor: ");
				String nomeAutor = leitor.nextLine();
				autorNovo.setNome(nomeAutor);
				autorService.salvar(autorNovo);
				System.out.println("Autor cadastrado");
				break;

			case 4:
				leitor = new Scanner(System.in);
				System.out.println(autorService.retornarListaDeAutores().toString());
				System.out.println("Qual o ID do autor deseja editar? ");
				long idAutor = Long.parseLong(leitor.nextLine());
				System.out.println("Digite o novo nome para esse autor: ");
				String novoNomeAutor = leitor.nextLine();
				Autor novoAutor = autorService.pesquisarAutorPorID(idAutor);
				novoAutor.setNome(novoNomeAutor);
				autorService.editarAutor(novoAutor, idAutor);

				break;
			case 5:
				leitor = new Scanner(System.in);
				System.out.println("Título do livro: ");
				String títuloLivro = leitor.nextLine();

				System.out.println("Insira o ID('s) dos autores desse livro, dentre: ");
				System.out.println(autorService.retornarListaDeAutores().toString());
				System.out.println("Separe por ',' em mais de um ID");
				String autoresCadastrados = leitor.nextLine();
				autoresCadastrados.trim();
				List<String> autoresFornecidos = Arrays.asList(autoresCadastrados.split(","));
				List<Autor> autoresLista = new ArrayList<>();
				for (int i = 0; i < autoresFornecidos.size(); i++) {
					Autor autorNumeravel = autorService.pesquisarAutorPorID(Long.parseLong(autoresFornecidos.get(i)));
					if (autorNumeravel != null) {
						autoresLista.add(autorNumeravel);
					}
				}

				System.out.println("Dentre os generos disponíveis: ");
				for (int i = 0; i < GenerosTipos.values().length; i++) {
					System.out.println(GenerosTipos.values()[i].toString());
				}
				System.out.println("Insira o genero correspondente: ");
				String generoLivro = leitor.nextLine();
				GenerosTipos generoSelecionadoLivro = GenerosTipos.valueOf(generoLivro);
				System.out.println("Edição do Livro: ");
				Integer edicaoLivro = Integer.parseInt(leitor.nextLine());
				System.out.println("Descrição do Livro: ");
				String descricaoLivro = leitor.nextLine();
				System.out.println("ISBN: ");
				String isbn = leitor.nextLine();
				System.out.println("Numero de paginas: ");
				Integer nPaginasLivro = Integer.parseInt(leitor.nextLine());
				System.out.println("Valor unitário do livro: ");
				BigDecimal valorLivro = leitor.nextBigDecimal();
				Livro novoLivro = new Livro(autoresLista, edicaoLivro, generoSelecionadoLivro, títuloLivro, valorLivro,
						descricaoLivro, isbn, nPaginasLivro);
				if (livroService.cadastrarLivro(novoLivro)) {
					System.out.println("Livro cadastrado");
				} else {
					System.out.println("Livro não cadastrado");
				}
				break;
			case 6:
				leitor = new Scanner(System.in);
				System.out.println("ID do livro que deseja editar: ");
				System.out.println(livroService.recuperarTodosOsLivros().toString());
				long idRecuperadoLivro = Long.parseLong(leitor.nextLine());
				System.out.println("Novo titulo do livro");
				String tituloEditado = leitor.nextLine();
				System.out.println("Insira o ID('s) dos autores desse livro, dentre: ");
				System.out.println(autorService.retornarListaDeAutores().toString());
				System.out.println("Separe por ',' em mais de um ID");
				String idAutoresEditados = leitor.nextLine();
				idAutoresEditados.trim();
				List<String> autoresEditados = Arrays.asList(idAutoresEditados.split(","));
				List<Autor> autoresListaEditado = new ArrayList<>();
				for (int i = 0; i < autoresEditados.size(); i++) {
					Autor autorSelecEditado = autorService.pesquisarAutorPorID(Long.parseLong(autoresEditados.get(i)));
					if (autorSelecEditado != null) {
						autoresListaEditado.add(autorSelecEditado);
					}
				}

				System.out.println("Dentre os generos disponíveis: ");
				for (int i = 0; i < GenerosTipos.values().length; i++) {
					System.out.println(GenerosTipos.values()[i].toString());
				}
				System.out.println("Insira o genero correspondente: ");
				String generoLivroEditado = leitor.nextLine();
				GenerosTipos generoSelecionadoLivroEditado = GenerosTipos.valueOf(generoLivroEditado);
				System.out.println("Edição do Livro: ");
				Integer edicaoLivroEditado = Integer.parseInt(leitor.nextLine());
				System.out.println("Descrição do Livro: ");
				String descricaoLivroEditado = leitor.nextLine();
				System.out.println("ISBN: ");
				String isbnEditado = leitor.nextLine();
				System.out.println("Numero de paginas: ");
				Integer nPaginasLivroEditado = Integer.parseInt(leitor.nextLine());
				System.out.println("Valor unitário do livro: ");
				BigDecimal valorLivroEditado = leitor.nextBigDecimal();
				Livro livroEditado = new Livro(autoresListaEditado, edicaoLivroEditado, generoSelecionadoLivroEditado,
						tituloEditado, valorLivroEditado, descricaoLivroEditado, isbnEditado, nPaginasLivroEditado);
				livroEditado.setId(idRecuperadoLivro);
				if (livroService.editarLivro(idRecuperadoLivro, livroEditado)) {
					System.out.println("Livro editado");
				} else {
					System.out.println("Livro não editado");
				}
				break;
			case 7:
				leitor = new Scanner(System.in);
				System.out.println(livroService.recuperarTodosOsLivros().toString());
				System.out.println("ID do livro a ser excluido: ");
				long idLivroRemover = Long.parseLong(leitor.nextLine());
				try {
					livroService.removePorId(idLivroRemover);
					System.out.println("livro deletado");
				} catch (Exception e) {
					System.out.println("livro não deletado");
				}
				break;
			case 8:
				leitor = new Scanner(System.in);
				System.out.println(livroService.recuperarTodosOsLivros().toString());
				System.out.println("ID do livro a ser adicionado ao estoque: ");
				long idLivroAddEstoque = Long.parseLong(leitor.nextLine());
				System.out.println("Quantidade a ser adicionada");
				Integer qtdEstoqueAtualizar = Integer.parseInt(leitor.nextLine());
				Livro livroAddEstoque = livroService.recuperarLivro(idLivroAddEstoque).get();
				if (livroAddEstoque != null) {
					System.out.println(livroAddEstoque.getId());
					livroAddEstoque.setQuantidadeEstoque(qtdEstoqueAtualizar);
					if (livroService.editarLivro(idLivroAddEstoque, livroAddEstoque)) {
						System.out.println("Estoque adicionado! ");
					}
				} else {
					System.out.println("Livro não encontrado! ");
				}
				break;
			case 9:
				Page<Livro> lista5Baratos = livroService.cincoLivrosMaisBaratos();
				for (Livro l : lista5Baratos) {
					System.out.println(l.toString());
				}
				break;
			case 10:
				Scanner input = new Scanner(System.in);
				System.out.println("Lista de todos os produtos: \n");
				String campoOrdenar = "nome";
				Sort.Direction direcaoOrdenar = Sort.Direction.ASC;
				System.out.println("Qual página? \n");
				Integer numPagina = Integer.parseInt(input.nextLine());
				int numeroPorPaginas = 3;
				direcaoOrdenar = Sort.Direction.ASC;
				livroService.retornarListaDeLivrosPaginada(campoOrdenar, direcaoOrdenar, numPagina, numeroPorPaginas);

				break;
			case 11:
				c = clienteService.pesquisarPorId(1);

				List<Livro> catalogo = livroService.recuperarTodosOsLivros();
				for (Livro livro : catalogo) {
					if (livro.getQuantidade() > 0) {
						System.out.println("ID: " + livro.getId() + "\nNome: " + livro.getNome() + "\nPreço: "
								+ livro.getValor() + "\nQuantidade disponível: " + livro.getQuantidade()
								+ "\n---------------------------------------------");
					}
				}
				System.out.println("Digite o id do livro: ");
				long id = leitor.nextLong();
				System.out.println("Digite a quantidade: ");
				int qtd = leitor.nextInt();
				Optional<Livro> livro2 = livroService.recuperarLivro(id);
				if (livro2.get().getQuantidade() >= qtd) {
					ItemCarrinho item = new ItemCarrinho();
					item.setCliente(c);
					item.setLivro(livro2.get());
					item.setQtd(qtd);
					item.setStatus("Pendente");
					carrinhoService.salvarItem(item);
				} else {
					System.out.println("Quantidade indisponível");
				}
				break;
			default:
				leitor.close();
				aux = false;
				break;
			}
		}
	}

}
