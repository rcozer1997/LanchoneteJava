package foodApp.Usuarios;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

import foodApp.Arquivos;
import foodApp.Lanchonetes.Lanchonete;
import foodApp.Lanchonetes.Pedidos;

public class Proprietario extends Usuario {
	//ArrayList<Lanchonete> lanchonetes = new ArrayList <>();
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	public Proprietario(Sistema sistema) {
		super(sistema);
		this.ident = 2;
	}

	public Proprietario(ArrayList<String> list) {
		super(list);
		
	}
	
	
	public void menu() {
		//arq.lerLanchonetesArq(lanchonetes);
		int opcao;
		do {
		System.out.println("------------------------------------------------");
		System.out.println("              MENU PROPRIETARIO                 ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Cadastrar Lanchonete");
		System.out.println("2) Remover Lanchonete");
		System.out.println("3) Cadastrar Lanche");
		System.out.println("4) Visualizar lista de pedidos de uma lanchonete");
		System.out.println("5) Visualizar um pedido de uma lanchonete");
		System.out.println("6) Remover pedido");
		System.out.println("7) Remover cadastro");
		System.out.println("0) Sair");

		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			cadastraLanchonete();
			arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
			this.menu();
			break;
		case 2:
			removeLanchonete();
			arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
			break;
		case 3:
			cadastraLancheLanchonete();
			break;
		case 4:
			visualizarPedidosLanchonete();
			break;
		case 5:
			break;
		case 6:
			removePedido();
			break;
		case 7:
			removerCadastro(this.getEmail(), sistema.getListaUsuarios());
			break;
		case 0:
			break;
		default:
			System.out.println("Opcao invalida! Tente novamente.");
			break;
		}
		
		}while(opcao != 0);
	}

	public void cadastraLanchonete() {
		Scanner s = new Scanner(System.in);

		System.out.println("Informe os dados da lanchonete a ser cadastrada: ");
		System.out.println("Codigo identificador: ");
		int codigo = s.nextInt();
		s.nextLine();
		System.out.println("Nome: ");
		String nome = s.nextLine();
		System.out.println("Endereço: ");
		String endereco = s.nextLine();
		System.out.println("Categoria de produtos: ");
		String categoria = s.nextLine();
		
		if(sistema.verificaExistenciaLanchonete(codigo)) {
			//verifica existencia da lanchonete na lista onde tem todas as lanchonetes do sistema
			Lanchonete l = new Lanchonete(codigo,nome,endereco,categoria, this.getNome());			
			//lanchonetes.add(l); //adiciona lanchonete no arraylist do proprietario
			sistema.getTodasLanchonetes().add(l); //adiciona na lista, em Sistema, de todas as lanchonetes
		};  	
	}

	public void removeLanchonete() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
			sistema.getTodasLanchonetes().remove(l);
			}
	}
	
	public void exibirLanchonetes(ArrayList<Lanchonete> lanchonetes) {	
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s", "CODIGO", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Lanchonete l : lanchonetes) {
			if(l.getProprietarioNome().equals(this.nome)) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%15s",l.codigo, l.nome, l.endereco, l.categoria, l.pontos);
			System.out.println();
			System.out.println("---------------------------------------------------------");
			}
		}
			
	}
	
	public void cadastraLancheLanchonete() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
		l.cadastraLanche(l);
		}
	}
	
	public Lanchonete buscarLanchonete(int codigo) {
		for(Lanchonete l : sistema.getTodasLanchonetes())
			if(l.codigo == codigo)
				return l;
		return null;
	}
	
	public void visualizarPedidosLanchonete() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
			l.visualizarPedidos();
		}
	}
	
	public void removePedido() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
			l.visualizarPedidos();
			System.out.println("Qual pedido deseja remover?");
			int codigoPedido = s.nextInt();
			Pedidos p = l.buscarPedido(codigoPedido);
			if(p == null) {
				System.out.println("Codigo invalido!");
			}
			else {
				l.removePedido(p);
			}
		}
	}
	
	public void listaLanchonetesProprietario() {
		exibirLanchonetes(sistema.getTodasLanchonetes());
		System.out.print("Codigo da lanchonete: ");
	}
	
	public boolean verificaProprietario(Lanchonete l) {
		
		if(l.getProprietarioNome().equals(this.nome)) {
			return true;
		}
		else return false;
	}
	}
	
	
	
	
	
	
