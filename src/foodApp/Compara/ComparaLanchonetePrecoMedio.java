package foodApp.Compara;

import java.util.Comparator;

import foodApp.Lanchonetes.Lanchonete;

public class ComparaLanchonetePrecoMedio implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
		return a.compareTo(b);
	}
}
