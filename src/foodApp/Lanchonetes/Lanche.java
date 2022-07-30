package foodApp.Lanchonetes;

public class Lanche {
	int codigo;
	String descricao;
	float preco;
	
	
	
	public Lanche(int codigo, String descricao, float preco) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
}
