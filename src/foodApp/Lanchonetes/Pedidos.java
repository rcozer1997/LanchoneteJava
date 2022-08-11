package foodApp.Lanchonetes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedidos {
	int codigo;
	String nomeCliente;
	String emailCliente;
	double valorTotal;
	int qntItens;
	String nomeLanchonete;
	LocalDateTime data;
	Lanchonete lanchonete;
	ArrayList<Lanche> produtos = new ArrayList<>();

	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliente=" + nomeCliente + ", valorTotal=" + valorTotal + ", qntItens="
				+ qntItens + ", data=" + data + "]";
	}
	
	public Pedidos(int codigo, ArrayList<Lanche> produtos, String nomeCliente, String emailCliente, double valorTotal, int qntItens, String nomeLanchonete, LocalDateTime data) {
		super();
		this.codigo = codigo;
		this.produtos = produtos;
		this.nomeCliente = nomeCliente;
		this.emailCliente = emailCliente;
		this.valorTotal = valorTotal;
		this.qntItens = qntItens;
		this.data = data;
		this.nomeLanchonete = nomeLanchonete;
	}
	
	public Pedidos(ArrayList<String> list) {		
		this.codigo = Integer.parseInt(list.get(0));
		this.nomeCliente = list.get(1);
		this.emailCliente = list.get(2);
		this.valorTotal = Double.parseDouble(list.get(3));
		this.qntItens = Integer.parseInt(list.get(4));
		this.data = LocalDateTime.parse(list.get(5));
		this.nomeLanchonete = list.get(6);
	}

	public void gravaPedido(BufferedWriter b) throws IOException {		
		b.write(this.codigo + "\n");
		b.write(this.nomeCliente + "\n");
		b.write(this.emailCliente + "\n");
		b.write(this.valorTotal + "\n");
		b.write(this.qntItens + "\n");
		b.write(this.data + "\n");
		b.write(this.nomeLanchonete + "\n");
}
	public int getCodigo() {
		return codigo;
	}
	
	public ArrayList<Lanche> getProdutos(){
		return produtos;
	}
	
	public int getQntItens() {
		return qntItens;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
}

