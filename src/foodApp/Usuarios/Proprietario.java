package foodApp.Usuarios;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

import foodApp.Arquivos;
import foodApp.Lanchonetes.Lanchonete;

public class Proprietario extends Usuario {
	ArrayList<Lanchonete> lanchonetes = new ArrayList <>();
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
		Scanner s = new Scanner (System.in);
		
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
		
		int opcao = s.nextInt();
		switch(opcao) {
		case 1:
			cadastraLanchonete();
			arq.salvaLanchoneteArq(lanchonetes);
			this.menu();
			break;
		case 2:
			removeLanchonete();
			arq.salvaLanchoneteArq(lanchonetes);
			break;
		case 3:
			cadastraLancheLanchonete();
			break;
		case 7:
			this.removerCadastro(this.email, sistema.getListaUsuarios());
			break;
		default:
			break;
		}
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
		
		sistema.verificaExistenciaLanchonete(codigo);  //verifica existencia da lanchonete na lista onde tem todas as lanchonetes do sistema
			Lanchonete l = new Lanchonete(codigo,nome,endereco,categoria);			
			lanchonetes.add(l); //adiciona lanchonete no arraylist do proprietario
			sistema.getTodasLanchonetes().add(l); //adiciona na lista, em Sistema, de todas as lanchonetes
		
		
	
	}

	public void removeLanchonete() {
		this.exibirLanchonetes(lanchonetes);
		System.out.print("Codigo da lanchonete a ser removida: ");
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else
		lanchonetes.remove(l);
	}
	
	public void exibirLanchonetes(ArrayList<Lanchonete> lanchonetes) {	
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s", "CODIGO", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Lanchonete l : lanchonetes) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%15s",l.codigo, l.nome, l.endereco, l.categoria, l.pontos);
			System.out.println();
			System.out.println("---------------------------------------------------------");
		}
			
	}
	
	public void cadastraLancheLanchonete() {
		exibirLanchonetes(this.lanchonetes);
		System.out.print("Codigo da lanchonete a cadastrar o lanche: ");
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else
		l.cadastraLanche(l);
	}
	
	public Lanchonete buscarLanchonete(int codigo) {
		for(Lanchonete l : lanchonetes)
			if(l.codigo == codigo)
				return l;
		return null;
	}
	
	public ArrayList<Lanchonete> getLanchonetes(){
		return this.lanchonetes;
	}
	


	
	}
	
	
	
	
	
	
