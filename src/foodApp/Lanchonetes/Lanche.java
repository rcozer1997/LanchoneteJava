package foodApp.Lanchonetes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lanche {
	int codigo;
	String descricao;
	float preco;
	String nomeLanchonete;
	int qntVendas = 0;
	
	public Lanche(int codigo, String descricao, float preco, String nomeLanchonete) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.nomeLanchonete = nomeLanchonete;
}
	
	public Lanche(ArrayList<String> list) {	
		this.codigo = Integer.parseInt(list.get(0));
		this.descricao = list.get(1);
		this.preco = Float.parseFloat(list.get(2));
		this.nomeLanchonete = list.get(3);
		this.qntVendas = Integer.parseInt(list.get(4));
}
	
	public void gravaLanche(BufferedWriter b) throws IOException {	
		b.write(this.codigo + "\n");
		b.write(this.descricao + "\n");
		b.write(this.preco + "\n");
		b.write(this.nomeLanchonete + "\n");
		b.write(this.qntVendas + "\n");
	}

	public String getNome() {
		return this.descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public String getNomeLanchonete() {
		return nomeLanchonete;
	}
	
	public void atualizaqntVendas(int quant) {
		this.qntVendas += quant;
	}
	
	public int getQntVendas() {
		return this.qntVendas;
	}
	
	public String toString() {
		return "Lanche [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	public int comparaPreco(Lanche l) {
		if(this.preco < l.preco) return 1;
		if(this.preco > l.preco) return -1;
		return 0;
	}
	
	public int comparaVendas(Lanche l) {
		if(this.qntVendas < l.qntVendas) return 1;
		if(this.qntVendas > l.qntVendas) return -1;
		return 0;
	}
}
