package foodApp.Usuarios;

import java.util.ArrayList;
import java.util.Scanner;

import foodApp.App;
import foodApp.Arquivos;
import foodApp.Sistema;
import foodApp.Exceptions.CodigoNaoEncontradoException;
import foodApp.Exceptions.CodigoReplicadoException;
import foodApp.Lanchonetes.Lanchonete;
import foodApp.Lanchonetes.Pedidos;

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
		lerLanchonetesProp(); //Esse metodo le todas as lanchonetes do sistema e insere as lanchonetes do proprietario em sua lista, checando o email do proprietario
		int opcao;
		do{
		System.out.println("------------------------------------------------");
		System.out.println("              MENU PROPRIETARIO                 ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Cadastrar Lanchonete");
		System.out.println("2) Remover Lanchonete");
		System.out.println("3) Cadastrar Lanche");
		System.out.println("4) Visualizar lista de pedidos de uma lanchonete");
		System.out.println("5) Visualizar um pedido de uma lanchonete - nao funciona");
		System.out.println("6) Remover pedido");
		System.out.println("7) Remover cadastro");
		System.out.println("0) Sair");

		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			try {
				cadastraLanchonete();
				arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
			}catch(CodigoReplicadoException e) {
				System.out.println(e.getMessage());
			}	
			break;
		case 2:
			removeLanchonete();
			arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
			break;
		case 3:
			cadastraLancheLanchonete();
			arq.salvaLanchesArq(sistema.getTodosLanches());
			break;
		case 4:
			try {
			visualizarPedidosLanchonete();
			}catch(CodigoNaoEncontradoException e) {
				System.out.println(e.getMessage());
			}
			break;
		/*case 5:
			break;*/
		case 6:
			try {
			removePedido();
			arq.salvaPedidosArq(sistema.getTodosPedidos());
			}catch(CodigoNaoEncontradoException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 7:
			this.removerCadastro(this.getEmail(), sistema.getListaUsuarios()); //Ao remover o cadastro, todas as lanchonetes do proprietario, bem como os pedidos e lanches, sao removidos
			App.main(null);
			break;
		default:
			if(opcao == 0) {
				System.out.println("");
			}
			else System.out.println("Opcao invalida! Tente novamente.");
			break;
		}
		
		}while(opcao != 0);
	}

	public void cadastraLanchonete() throws CodigoReplicadoException {
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
		
		if(codigo == 0) {
			System.out.println("Zero nao eh um codigo valido!");
		}
		if(sistema.verificaExistenciaLanchonete(codigo)) {
			//verifica existencia da lanchonete na lista onde tem todas as lanchonetes do sistema
			Lanchonete l = new Lanchonete(codigo,nome,endereco,categoria, this.getNome(), this.getEmail());			
			lanchonetes.add(l); //adiciona lanchonete no arraylist do proprietario
			sistema.getTodasLanchonetes().add(l); //adiciona na lista, em Sistema, de todas as lanchonetes
		}
		else if(!sistema.verificaExistenciaLanchonete(codigo)){
			throw new CodigoReplicadoException("Codigo ja cadastrado!");
		}
	}

	public void removeLanchonete() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
			l.removeTodosLanches();
			l.removeTodosPedidos();
			sistema.getTodasLanchonetes().remove(l);
			}
		arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
		arq.salvaLanchesArq(sistema.getTodosLanches());
		arq.salvaPedidosArq(sistema.getTodosPedidos());
	}
	
	public void lerLanchonetesProp() {
		for(Lanchonete l : sistema.getTodasLanchonetes()) {
			if(l.getProprietarioEmail().equals(this.getEmail())) {
				this.lanchonetes.add(l);
			}
		}
	}
	
	public void removerLanchonetesProprietario() {		
		 	for(Lanchonete doProprietario : this.lanchonetes){
			 	for(int i = 0; i < sistema.getTodasLanchonetes().size(); i++) {
			 		Lanchonete l = sistema.getTodasLanchonetes().get(i);
			 		if(l.getCodigo() == doProprietario.getCodigo()) {
			 			l.removeTodosLanches();
			 			l.removeTodosPedidos();
			 			sistema.getTodasLanchonetes().remove(l);
				}
			}
		}
		arq.salvaLanchoneteArq(sistema.getTodasLanchonetes());
		arq.salvaLanchesArq(sistema.getTodosLanches());
		arq.salvaPedidosArq(sistema.getTodosPedidos());
	}
	
	public void exibirLanchonetes(ArrayList<Lanchonete> lanchonetes) {	
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s", "CODIGO", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		for(Lanchonete l : lanchonetes) {
			if(l.getProprietarioNome().equals(this.nome)) {
				System.out.println("---------------------------------------------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%15s",l.codigo, l.nome, l.endereco, l.categoria, l.pontos);
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------");
			}
		}		
	}
	
	public void cadastraLancheLanchonete() {
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		if(codigo == 0) {
			System.out.println("Zero nao eh um codigo valido!");
		}
		else {
			Lanchonete l = buscarLanchonete(codigo);
			if(l == null) {
				System.out.println("Codigo invalido!");
			}
			else if(verificaProprietario(l)) {
				try {
					l.cadastraLanche(l);
				}catch(CodigoReplicadoException e) {
					System.out.println(e.getMessage());
			}
		}

	}

}
	
	public Lanchonete buscarLanchonete(int codigo) {
		for(Lanchonete l : sistema.getTodasLanchonetes())
			if(l.codigo == codigo) {
				return l;
			}
		return null;
	}
	
	public void visualizarPedidosLanchonete() throws CodigoNaoEncontradoException{
		listaLanchonetesProprietario();
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			throw new CodigoNaoEncontradoException("Codigo invalido!");
		}
		else if(verificaProprietario(l)) {
			l.visualizarPedidos();
		}
	}
	
	public void removePedido() throws CodigoNaoEncontradoException{
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
				throw new CodigoNaoEncontradoException("Codigo invalido!");
			}
			else {
				l.removePedido(p);
				//sistema.getTodosPedidos().remove(p);
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
	
	public ArrayList<Lanchonete> getLanchonetes() {
		return this.lanchonetes;
	}
	
	public void removerCadastro(String email, ArrayList<Usuario>listaUsuarios) {
		for(int i = 0; i<listaUsuarios.size();i++){
			Usuario u = listaUsuarios.get(i);
			if(u.getEmail().equals(email)){
				System.out.println("Tem certeza que deseja remover o cadastro?");
				System.out.println("1 - Sim\n2 - Nao");
				int opcao = s.nextInt();
				
				if(opcao == 1) {
					removerLanchonetesProprietario();
					listaUsuarios.remove(u);
			        System.out.println("Cliente removido!");
					arq.salvaUsuariosArq(listaUsuarios, "Usuarios.csv");
					break;
				}
				else if(opcao == 2) {
					break;
				}			
			}
		}	
	}		
}
	
	
	
	
	
	
