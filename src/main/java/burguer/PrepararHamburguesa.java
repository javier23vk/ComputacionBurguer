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
	public void eventRoutine(Cocinero arg0, Dependiente arg1) {

		// Entregar al dependiente que estaba esperandome
		// El dependiente cobra
		EventoPago event = new EventoPago(myModel, "Evento Pago", true);
		// lo programamos en la lista de eventos
		event.schedule(dependiente, new TimeSpan(myModel.getTiempoPago()));
		
		
		//Atender al siguiente
		if (!myModel.colaPedidosPendientes.isEmpty()) {
			Dependiente nextDependiente = myModel.colaPedidosPendientes.first();
			// Y eliminar al dependiente de la cola de espera
			myModel.colaPedidosPendientes.remove(nextDependiente);
			
			// creamos un nuevo evento para que cocine
			EventoCocina nextEvent = new EventoCocina(myModel, "Evento Cocina", true);
 			// se programa el tiempo necesario para que termine de cocinar
			nextEvent.schedule(cocinero, nextDependiente, new TimeSpan(myModel.getTiempoCocina(), TimeUnit.MINUTES));
		}else {
			// NO, no hay ningun pedido esperando

			// el dependiente se incluye en la cola de dependientes ociosos
			myModel.colaCocinerosOciosos.insert(cocinero);

			// ahora esta esperando a que llegue algun dependiente
		}
	}


	

}
