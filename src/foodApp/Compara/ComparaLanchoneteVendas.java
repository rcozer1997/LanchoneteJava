package foodApp.Compara;
import java.util.Comparator;

import foodApp.Lanchonetes.Lanchonete;

public class ComparaLanchoneteVendas implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
			return a.comparaVendas(b);
		}
}
