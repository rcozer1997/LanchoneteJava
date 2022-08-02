package foodApp.Usuarios;
import java.util.ArrayList;
import java.util.Scanner;
import foodApp.Arquivos;
import foodApp.Lanchonetes.Lanchonete;

public class Sistema {
	Scanner s = new Scanner(System.in);
	Arquivos arq = new Arquivos();
	public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	public ArrayList<Lanchonete> todasLanchonetes = new ArrayList<>();
	
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
	
	
	public ArrayList<Lanchonete>getTodasLanchonetes(){
		return this.todasLanchonetes;
	}
	
	public ArrayList<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}
	
	public boolean verificaExistenciaLanchonete(int codigo) {
		for(int i = 0; i<this.todasLanchonetes.size();i++){
			Lanchonete l = this.todasLanchonetes.get(i);
			if(l.getCodigo() == codigo){
				System.out.println("Lanchonete com este codigo ja cadastrada!");
				return false;
				}
			
			}
		return true;
		}
}
