package foodApp.Compara;

import java.util.Comparator;

import foodApp.Usuarios.Cliente;

public class ComparaClienteGasto implements Comparator<Cliente>{
	public int compare(Cliente a, Cliente b) {
		return a.comparaGasto(b);
	}
}
