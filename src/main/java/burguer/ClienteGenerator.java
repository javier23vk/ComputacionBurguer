package burguer;


import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.*;

public class ClienteGenerator extends ExternalEvent {

	public ClienteGenerator(Model owner, String name, boolean showInTrace) {
		super( owner,  name,  showInTrace);
		// TODO Auto-generated constructor stub
	}

	public void eventRoutine() {

		Modelo model = (Modelo) this.getModel();
		Cliente cli = new Cliente(model, "Cliente",true);
		ClientArrivalEvent clientArrival= new ClientArrivalEvent(model,"ClientArrivalEvent",true);
		clientArrival.schedule(cli, new TimeSpan(0, TimeUnit.MINUTES));
	    schedule(new TimeSpan(model.getTiempoLlegadaClientes(), TimeUnit.MINUTES));
	}
}