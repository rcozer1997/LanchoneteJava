package foodApp.Lanchonetes;
import java.util.Comparator;

public class ComparaLanchoneteVendas implements Comparator<Lanchonete>{
	public int compare(Lanchonete a, Lanchonete b) {
			return a.comparaVendas(b);
		}
}
