package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

import java.util.concurrent.TimeUnit;

public class PrepararHamburguesa extends EventOf2Entities<Cocinero, Dependiente> {
	private Modelo myModel;

	public PrepararHamburguesa(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		myModel = (Modelo)arg0;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Cocinero coc, Dependiente dep) {

		PagarPedido event = new PagarPedido(myModel, "PagarPedido", true);
		event.schedule( dep, new TimeSpan(myModel.getTiempoPagoClientes()));

		if (!myModel.colaDependientes.isEmpty()) {
			Dependiente nextDependiente = myModel.colaDependientes.first();
			myModel.colaDependientes.remove(nextDependiente);
			PrepararHamburguesa nextEvent = new PrepararHamburguesa(myModel, "PrepararHamburguesa", true);
			nextEvent.schedule(coc, nextDependiente, new TimeSpan(myModel.getTiempoServicioCocineros(), TimeUnit.MINUTES));
		}else {
			myModel.colaCocineros.insert(coc);
		}
	}


	

}
