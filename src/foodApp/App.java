package foodApp;

import java.util.Scanner;

import foodApp.Exceptions.EmailInvalidoException;
import foodApp.Usuarios.Administrador;
import foodApp.Usuarios.Cliente;
import foodApp.Usuarios.Proprietario;

public class App {

	public static void main(String[] args) {
		//new GUI();
		
		Scanner s = new Scanner(System.in);
		Arquivos arq = new Arquivos();
		Sistema sistema = new Sistema();
		Menus menus = new Menus();
		
			arq.lerUsuariosArq(sistema.getListaUsuarios());
			arq.lerLanchonetesArq(sistema.getTodasLanchonetes(), sistema); 
			arq.lerLanchesArq(sistema.getTodosLanches(), sistema.getTodasLanchonetes()); //O arquivo dos lanches sera lido e o codigo verificara o nome da lanchonete que ele esta cadastrado, para inseri-lo na lista da lanchonete respectiva
			arq.lerPedidosArq(sistema.getTodosPedidos(), sistema.getTodasLanchonetes(), sistema.getListaUsuarios(), sistema); //O mesmo ocorre com os pedidos, que serão inseridos nas lanchonetes respectivas							
			int opcao;
			do {
				menus.menuPrincipal();
				opcao = s.nextInt();
				
				switch(opcao) {
				
				case 1:
					System.out.println("Qual perfil deseja cadastrar?");
					menus.printaPerfisUsuarios();
					int opcaoCadastro = s.nextInt();
					switch(opcaoCadastro) {
					case 1:
						Administrador a = new Administrador(sistema);
						try {
							if(!sistema.verificaCadastro(a, sistema.getListaUsuarios())) break;
							sistema.getListaUsuarios().add(a);
							arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
						}catch(EmailInvalidoException e) {
							System.out.println(e.getMessage());
						}				
						break;
					case 2:
						try {
							Proprietario p = new Proprietario(sistema);
							if(!sistema.verificaCadastro(p, sistema.getListaUsuarios())) break;
							sistema.getListaUsuarios().add(p);
							arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
							sistema.getListaUsuarios().isEmpty();
						}catch(EmailInvalidoException e) {
							System.out.println(e.getMessage());
						}					
						break;
					case 3:
						try {
							Cliente c = new Cliente(sistema);
							if(!sistema.verificaCadastro(c, sistema.getListaUsuarios())) break;
							sistema.getListaUsuarios().add(c);
							arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
						}catch(EmailInvalidoException e) {
							System.out.println(e.getMessage());
						}					
						break;
					case 0:
						break;
					default:
						System.out.println("Opcao invalida! Tente novamente.");
					}
					break;
				case 2:
					try{
					sistema.login(sistema.getListaUsuarios(), sistema);
					
					}catch(EmailInvalidoException e) {
					System.out.println(e.getMessage());
					}
					break;
				case 0:
					System.out.println("Volte sempre!");
					break;
				default:
					System.out.println("Opcao invalida! Tente novamente.");
					break;
			}	
		}while (opcao!= 0);
	}
}