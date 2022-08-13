package foodApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import foodApp.Lanchonetes.Lanche;
import foodApp.Lanchonetes.Lanchonete;
import foodApp.Lanchonetes.Pedidos;
import foodApp.Usuarios.Administrador;
import foodApp.Usuarios.Cliente;
import foodApp.Usuarios.Proprietario;
import foodApp.Usuarios.Usuario;

public class Arquivos {
	
	public void salvaUsuariosArq(ArrayList<Usuario>listaUsuarios, String fileName) {
		try {
			FileWriter f = new FileWriter(fileName);
			BufferedWriter b = new BufferedWriter(f);
			
			b.write(listaUsuarios.size() + "\n");
			for(Usuario u : listaUsuarios) {
				u.gravaUsuariosArq(b);
			}
			b.close();
		}catch(IOException e) {
			System.out.println("Erro ao salvar o arquivo!");
	}
}
	
	public void lerUsuariosArq(ArrayList<Usuario> listaUsuarios) {
		try {
			FileReader f = new FileReader("Usuarios.csv");
			BufferedReader b = new BufferedReader(f);
			int t = Integer.parseInt(b.readLine());
            ArrayList<String> d = new ArrayList<String>();
			
			for(int i = 0; i<t; i++) {
				for(int j = 0; j<4; j++) {
					String data = b.readLine();
					d.add(data);
				}
				int id = Integer.parseInt(d.get(3));
                if(id == 1) {
					listaUsuarios.add(new Administrador(d));
				}
				else if(id == 2) {
					listaUsuarios.add(new Proprietario(d));
				}
				else if(id == 3) {
					listaUsuarios.add(new Cliente(d));
				}
                d.clear();
			}		
			b.close();
			f.close();
		}catch(IOException e) {
			System.out.println("Nenhum usuario carregado.");
	}
}
	
	public void lerLanchesArq(ArrayList<Lanche> todosLanches, ArrayList<Lanchonete> lanchonetes) {
		try {
			FileReader f = new FileReader("Lanches.csv");
			BufferedReader b = new BufferedReader(f);
			ArrayList<String> temp = new ArrayList<String>();
			int t = Integer.parseInt(b.readLine());
			for(int i = 0; i<t; i++) {
				for(int j = 0; j<5; j++) {
					String dados = b.readLine();
					temp.add(dados);
				}	
					int codigoLanchonete = Integer.parseInt(temp.get(3));				
					for(Lanchonete l : lanchonetes) {
						if(codigoLanchonete == l.getCodigo()) {
							l.getListaLanches().add(new Lanche(temp));
					}
				}
					todosLanches.add(new Lanche(temp));
					temp.clear();
			}		
			f.close();
			b.close();			
		}catch(IOException e) {
			System.out.println("Nenhum lanche carregado.");
	}
}
	
	public void lerPedidosArq(ArrayList<Pedidos> pedidos, ArrayList<Lanchonete> lanchonetes, ArrayList<Usuario> usuarios, Sistema sistema) {		
		ArrayList<Cliente> clientes = new ArrayList<>(); //Pega somente os clientes da lista de usuarios, para que eles possam ser repopulados com seus respectivos pedidos
			for(Usuario u : usuarios) {
				if(u.getIdent() == 3) {
					clientes.add((Cliente) u);
				}
			}
			try {
			FileReader f = new FileReader("Pedidos.csv");
			BufferedReader b = new BufferedReader(f);
			ArrayList<String> temp = new ArrayList<>();
			int t = Integer.parseInt(b.readLine());			
			for(int i = 0; i<t; i++) {
				for(int j = 0; j<8; j++) {
					String dados = b.readLine();
					temp.add(dados);
				}
				String emailCliente = temp.get(2);
				int codigoLanchonete = Integer.parseInt(temp.get(6));
				for(Lanchonete l : lanchonetes) {
					if(codigoLanchonete == l.getCodigo()) {     //Repopula as lanchonetes com os seus respectivos pedidos
						l.getListaPedidos().add(new Pedidos(temp, sistema));
					}
				}
				for(Cliente c : clientes) {
					if(emailCliente.equals(c.getEmail())) {
						c.getClientePedidos().add(new Pedidos(temp, sistema)); //Repopula os usuarios com seus respectivos pedidos
					}
				}	
				pedidos.add(new Pedidos(temp, sistema));
				temp.clear();
			}
			f.close();
			b.close();
		}catch(IOException e) {
			System.out.println("Nenhum pedido carregado.");
	}
}	
	
	public void salvaLanchoneteArq(ArrayList<Lanchonete> lanchonetes) {
		try {
			FileWriter f = new FileWriter("Lanchonetes.csv");
			BufferedWriter b = new BufferedWriter(f);			
			b.write(lanchonetes.size() + "\n");
			for(Lanchonete l : lanchonetes) {				
				l.gravaLanchonete(b);
			}
			b.close();			
		}catch(IOException e) {
			System.out.println("Erro ao salvar o arquivo.");
	}
}

	public void lerLanchonetesArq(ArrayList<Lanchonete> lanchonetes, Sistema sistema) {
		try {
			FileReader f = new FileReader("Lanchonetes.csv");
			BufferedReader b = new BufferedReader(f);			
			int t = Integer.parseInt(b.readLine());			
			for(int i = 0; i<t; i++) {
				lanchonetes.add(new Lanchonete(b, sistema));
			}
			f.close();
			b.close();
		}catch(IOException e) {
			System.out.println("Nenhuma lanchonete carregada.");
	}
}
	
	public void salvaPedidosArq(ArrayList<Pedidos> pedidos) {
		try {
			FileWriter f = new FileWriter("Pedidos.csv");
			BufferedWriter b = new BufferedWriter(f);
			
			b.write(pedidos.size() + "\n");
			for(Pedidos p : pedidos) {			
				p.gravaPedido(b);
			}
			b.close();
			f.close();
		}catch(IOException e) {
			System.out.println("Erro ao salvar o arquivo.");
	}
}

	
	public void salvaLanchesArq(ArrayList<Lanche> lanches) {
		try {
			FileWriter f = new FileWriter("Lanches.csv");
			BufferedWriter b = new BufferedWriter(f);
			
			b.write(lanches.size() + "\n");
			for(Lanche l: lanches) {				
				l.gravaLanche(b);
			}
			b.close();
			f.close();
			
		}catch(IOException e) {
			System.out.println("Erro ao salvar o arquivo.");
		}
	}
}