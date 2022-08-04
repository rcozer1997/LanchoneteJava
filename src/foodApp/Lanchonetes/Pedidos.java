package foodApp.Lanchonetes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import foodApp.Usuarios.Cliente;

public class Pedidos {
	int codigo;
	String nomeCliente;
	double valorTotal;
	int qntItens;
	String nomeLanchonete;
	Date data;
	Lanchonete lanchonete;
	ArrayList<Lanche> produtos = new ArrayList<>();

	
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliente=" + nomeCliente + ", valorTotal=" + valorTotal + ", qntItens="
				+ qntItens + ", data=" + data + "]";
	}
	

	public Pedidos(int codigo, String nomeCliente, double valorTotal, int qntItens, String nomeLanchonete) {
		super();
		this.codigo = codigo;
		this.nomeCliente = nomeCliente;
		this.valorTotal = valorTotal;
		this.qntItens = qntItens;
		this.data = data;
		this.nomeLanchonete = nomeLanchonete;
	}
	
	public Pedidos(ArrayList<String> list) {
		
		this.codigo = Integer.parseInt(list.get(0));
		this.nomeCliente = list.get(1);
		this.valorTotal = Double.parseDouble(list.get(2));
		this.qntItens = Integer.parseInt(list.get(3));
		this.data = list.get(4);
		this.nomeLanchonete = list.get(5);
	}
	
	public void gravaPedido(BufferedWriter b) throws IOException {
		
		b.write(this.codigo + "\n");
		b.write(this.nomeCliente + "\n");
		b.write(this.valorTotal + "\n");
		b.write(this.qntItens + "\n");
		b.write(this.data + "\n");
		
}


	public int getCodigo() {
		return codigo;
	}
	
	public ArrayList<Lanche> getProdutos(){
		return produtos;
	}
	
	public String getData() {
		return data;
	}
	
	

}

