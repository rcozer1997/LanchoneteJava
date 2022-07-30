package foodApp;

import java.util.ArrayList;
import java.util.Scanner;
import foodApp.Usuarios.Administrador;
import foodApp.Usuarios.Cliente;
import foodApp.Usuarios.Proprietario;
import foodApp.Usuarios.Usuario;

public class Acesso {
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	
	public boolean verificaCadastro(Usuario u, ArrayList<Usuario> listaUsuarios) {
		for(int i = 0; i<listaUsuarios.size();i++){
			Usuario usu = listaUsuarios.get(i);
			if(usu.getEmail().equals(u.getEmail()))
			{
				System.out.println("Email ja cadastrado!");
				return false;
			}
			
		}
		return true;
	}

	public void login(ArrayList<Usuario>listaUsuarios){	
		System.out.println(listaUsuarios.toString());
		System.out.print("Email:");
		String email = s.nextLine();
		System.out.print("Senha:");
		String senha = s.nextLine();

		Usuario usu = null;
		for(int i = 0; i<listaUsuarios.size();i++){
			Usuario u = listaUsuarios.get(i);
			if(u.getEmail().equals(email)){
				usu = u;
				validaSenha(usu, senha);
				break;
			}
		}	
		if(usu == null){
			System.out.println("Usuario nao existe!");
		}
		
	}
	

	
	public void validaSenha(Usuario u, String senha){
		if(u.getSenha().equals(senha)){
			if(u.getIdent() == 1) {
				System.out.println("Logado com sucesso!");
				Administrador a = (Administrador) u; 
				a.menu();
			}
			else if(u.getIdent() == 2) {
				System.out.println("Logado com sucesso!");
				Proprietario p = (Proprietario) u;
				p.menu();
			}
			else if(u.getIdent() == 3) {
				System.out.println("Logado com sucesso!");
				Cliente c = (Cliente) u;
				c.menu();
			}	 
		}
		else {
			System.out.println("Senha incorreta!");
			}
	}

}
