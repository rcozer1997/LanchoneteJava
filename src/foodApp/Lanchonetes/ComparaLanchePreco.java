package foodApp.Lanchonetes;

import java.util.Comparator;

public class ComparaLanchePreco implements Comparator<Lanche>{
	public int compare(Lanche a, Lanche b) {
		return a.compareTo(b);
	}
}
