package burguer;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

public class PagarPedido extends EventOf2Entities<Cliente, Dependiente> {

	public PagarPedido(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);

	}

	@Override
	public void eventRoutine(Cliente cli, Dependiente dep) {

	}

}
