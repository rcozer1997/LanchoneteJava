package foodApp.Compara;

import java.util.Comparator;

import foodApp.Lanchonetes.Lanche;

public class ComparaLanchesVendidos implements Comparator<Lanche>{
	public int compare(Lanche a, Lanche b) {
		return a.comparaVendas(b);
	}
}
