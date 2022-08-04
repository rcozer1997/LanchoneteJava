package foodApp.Lanchonetes;

import java.util.Comparator;

public class ComparaLanchonetePontos implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
		return a.comparaPontos(b);
	}

}
