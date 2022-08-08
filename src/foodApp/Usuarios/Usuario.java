package foodApp.Usuarios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import foodApp.Arquivos;
import foodApp.Sistema;

public abstract class Usuario {
	String nome;
	private String email;
	private String senha;
	int ident; // 1 - Administrador , 2 - Proprietario , 3 - Cliente	
	public Sistema sistema;
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", senha=" + senha + ", ident=" + ident + "]";
	}

	public Usuario(Sistema sistema) {
		System.out.print("Nome:");
		String nome = s.nextLine();
		System.out.print("Email:");
		String email = s.nextLine();
		System.out.print("Senha:");
		String senha = s.nextLine(); 		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.sistema = sistema;
	}
	
	public Usuario(ArrayList<String> list) {	
		this.nome = list.get(0);
		this.email = list.get(1);
		this.senha = list.get(2);
		this.ident = Integer.parseInt(list.get(3));		
	}
	
	public void gravaUsuariosArq(BufferedWriter b) throws IOException {
		b.write(this.nome + "\n");
		b.write(this.email + "\n");
		b.write(this.senha + "\n");
		b.write(this.ident + "\n");
	}

	public String getEmail(){
		return this.email;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getNome() {
		return this.nome;		
	}
	
	public int getIdent() {
		return this.ident;
	}
	
	public boolean isProp(){
		if(this.ident == 2) {
			return true;
		}
		else return false;
	}
	
	public abstract void menu();
	
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
		}	
	}	
	
}
