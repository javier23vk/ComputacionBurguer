package burguer;

import java.util.concurrent.TimeUnit;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class GeneradorEventoClientes extends ExternalEvent{

	public GeneradorEventoClientes(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine() throws SuspendExecution {
		Modelo model = (Modelo)getModel();
		Cliente cliente = new Cliente(model, "Cliente", true);
		EventoLlegadaCliente eventoLlegadaCliente = new EventoLlegadaCliente(model,
                "eventoLlegadaCliente", true);
		eventoLlegadaCliente.schedule(cliente, new TimeSpan(0, TimeUnit.MINUTES));
		 schedule(new TimeSpan(model.getTiempoLlegadaClientes(), TimeUnit.MINUTES));
	}

}
