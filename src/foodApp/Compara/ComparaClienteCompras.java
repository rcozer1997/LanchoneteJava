package foodApp.Compara;

import java.util.Comparator;
import foodApp.Usuarios.Cliente;

public class ComparaClienteCompras implements Comparator<Cliente> {
	public int compare(Cliente a, Cliente b) {
		return a.comparaCompras(b);
	}
}

