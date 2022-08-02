package foodApp;

import java.util.Scanner;

import foodApp.Usuarios.Administrador;
import foodApp.Usuarios.Cliente;
import foodApp.Usuarios.Proprietario;
import foodApp.Usuarios.Sistema;

public class App {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Arquivos arq = new Arquivos();
		Sistema sistema = new Sistema();
		Menus menus = new Menus();

			arq.lerUsuariosArq(sistema.getListaUsuarios());
			arq.lerLanchonetesArq(sistema.getTodasLanchonetes());
			int opcao;
			do {
				menus.menuPrincipal();
			opcao = s.nextInt();
			
			if(opcao == 1) {
				
				System.out.println("Qual perfil deseja cadastrar?");
				menus.printaPerfisUsuarios();

				int opcaoCadastro = s.nextInt();
				switch(opcaoCadastro) {
				case 1:
					Administrador a = new Administrador(sistema);
					if(!sistema.verificaCadastro(a, sistema.getListaUsuarios())) break;
					sistema.getListaUsuarios().add(a);
					arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
					break;
				case 2:
					Proprietario p = new Proprietario(sistema);
					if(!sistema.verificaCadastro(p, sistema.getListaUsuarios())) break;
					sistema.getListaUsuarios().add(p);
					arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
					sistema.getListaUsuarios().isEmpty();
					break;
				case 3:
					Cliente c = new Cliente(sistema);
					if(!sistema.verificaCadastro(c, sistema.getListaUsuarios())) break;
					sistema.getListaUsuarios().add(c);
					arq.salvaUsuariosArq(sistema.getListaUsuarios(), "Usuarios.csv");
					break;
				
				} 
			}
			else if(opcao == 2) {
				sistema.login(sistema.getListaUsuarios());
			}
			else
				System.out.println("Opcao invalida!!!");
		
		}while (opcao!= 0);
		

}
}