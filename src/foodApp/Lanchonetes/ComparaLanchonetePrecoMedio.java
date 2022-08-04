package foodApp.Lanchonetes;

import java.util.Comparator;

public class ComparaLanchonetePrecoMedio implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
		return a.compareTo(b);
	}

}
