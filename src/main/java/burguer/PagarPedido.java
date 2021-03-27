package burguer;

import desmoj.core.simulator.Event;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

import java.util.concurrent.TimeUnit;

public class PagarPedido extends Event< Dependiente> {

	private Modelo myModel;

	public PagarPedido(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		myModel = (Modelo)arg0;

	}

	@Override
	public void eventRoutine(Dependiente dep) {
				if (!myModel.colaClientes.isEmpty())
				{		
					Cliente nextCliente = myModel.colaClientes.first();
					myModel.colaClientes.remove(nextCliente);
					RealizarPedido event = new RealizarPedido(myModel, "RealizarPedido", true);
					event.schedule(dep, nextCliente, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
				}
				else {
					myModel.colaDependientes.insert(dep);
				}
		


		
	}

}
