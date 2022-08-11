package foodApp.Compara;

import java.util.Comparator;

import foodApp.Lanchonetes.Lanchonete;

public class ComparaLanchonetePontos implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
		return a.comparaPontos(b);
	}
}
