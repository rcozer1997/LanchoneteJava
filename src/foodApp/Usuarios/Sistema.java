package foodApp.Usuarios;
import java.util.ArrayList;
import java.util.Scanner;
import foodApp.Acesso;
import foodApp.Arquivos;
import foodApp.Menus;
import foodApp.Lanchonetes.Lanchonete;

public class Sistema {
	public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	//Proprietario p;
	//ArrayList<Lanchonete> todasLanchonetes = new ArrayList<>();
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	Acesso acesso = new Acesso();
	Menus menus = new Menus();
	
	public void menuPrincipal() {
		arq.lerUsuariosArq(listaUsuarios);
		//arq.lerLanchonetesArq(p.getLanchonetes());
		System.out.println(listaUsuarios.isEmpty());
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
				Administrador a = new Administrador();
				if(!acesso.verificaCadastro(a, listaUsuarios)) break;
				listaUsuarios.add(a);
				arq.salvaUsuariosArq(listaUsuarios, "Usuarios.csv");
				break;
			case 2:
				Proprietario p = new Proprietario();
				if(!acesso.verificaCadastro(p, listaUsuarios)) break;
				listaUsuarios.add(p);
				arq.salvaUsuariosArq(listaUsuarios, "Usuarios.csv");
				listaUsuarios.isEmpty();
				break;
			case 3:
				Cliente c = new Cliente();
				if(!acesso.verificaCadastro(c, listaUsuarios)) break;
				listaUsuarios.add(c);
				arq.salvaUsuariosArq(listaUsuarios, "Usuarios.csv");
				break;
			
			} 
		}
		else if(opcao == 2) {
			acesso.login(listaUsuarios);
		}
		else
			System.out.println("Opcao invalida!!!");
	
	}while (opcao!= 0);
}	
	public void removerCadastro(String email, ArrayList<Usuario>listaUsuarios) {
		System.out.println(listaUsuarios.toString());
		for(int i = 0; i<listaUsuarios.size();i++){
			Usuario u = listaUsuarios.get(i);
			if(u.getEmail().equals(email)){
				System.out.println("Tem certeza que deseja remover o cadastro?");
				System.out.println("1 - Sim\n2 - Nao");
				int opcao = s.nextInt();
				
				if(opcao == 1) {
					listaUsuarios.remove(u);
			        System.out.println("Cliente removido!");
					arq.salvaUsuariosArq(listaUsuarios, "Usuarios.csv");
					break;
				}
				else if(opcao == 2) {
					break;
				}			
			}
			else System.out.println("aqui");
		}	
	}
	/*
	public ArrayList<Lanchonete>getTodasLanchonetes(){
		return this.todasLanchonetes;
	}
	*/
	public ArrayList<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}
	/*
	public void verificaExistenciaLanchonete(int codigo) {
		for(int i = 0; i<todasLanchonetes.size();i++){
			Lanchonete l = todasLanchonetes.get(i);
			if(l.getCodigo() == codigo){
				System.out.println("Lanchonete com este codigo ja cadastrada!");
				break;
				}
			
			}
		}*/
}
