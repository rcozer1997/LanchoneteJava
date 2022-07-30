package foodApp.Lanchonetes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import foodApp.Usuarios.Proprietario;

public class Lanchonete {
	public int codigo;
	public String nome;
	public String endereco;
	public String categoria;
	public int pontos;
	Proprietario proprietario;
	ArrayList<Lanche> lanches = new ArrayList<>();
	ArrayList<Pedidos> listaPedidos = new ArrayList<>();
	
	Scanner s = new Scanner(System.in);
	
	@Override
	public String toString() {
		return "Lanchonete [codigo=" + codigo + ", nome=" + nome + ", endereco=" + endereco + ", categoria=" + categoria
				+ ", pontos=" + pontos + "]";
	}

	public Lanchonete(BufferedReader b) {
		try {
		this.codigo = Integer.parseInt(b.readLine());
		this.nome = b.readLine();
		this.endereco = b.readLine();
		this.categoria = b.readLine();
		this.pontos = Integer.parseInt(b.readLine());
		}catch(IOException e) 
		{
			System.out.println("Erro");
		}
	}
	
	public Lanchonete(int codigo, String nome, String endereco, String categoria) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.categoria = categoria;
		this.pontos = 0;
	}
	
	public void gravaLanchonete(BufferedWriter b) throws IOException {
			
			b.write(this.codigo + "\n");
			b.write(this.nome + "\n");
			b.write(this.endereco + "\n");
			b.write(this.categoria + "\n");
			b.write(this.pontos + "\n");
			
	}
		
	public void cadastraLanche(Lanchonete l) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Informe os dados do lanche:");
		System.out.println("Codigo identificador: ");
		int codigo = s.nextInt();
		s.nextLine();
		System.out.println("Descricao: ");
		String descricao = s.nextLine();
		System.out.println("Preco: ");
		float preco = s.nextFloat();
		
		Lanche lanche = new Lanche(codigo,descricao, preco);
		lanches.add(lanche);
		}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void verificaExistenciaLanche(int codigo) {
		for(int i = 0; i<lanches.size();i++){
			Lanche l = lanches.get(i);
			if(l.getCodigo() == codigo){
				System.out.println("Lanche com este codigo ja esta cadastrado!");
				break;
				}
	}
}
}

	

