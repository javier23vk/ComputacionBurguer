package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class ClientArrivalEvent extends Event<Cliente> {

	private Modelo myModel;

	public ClientArrivalEvent(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Cliente cli) throws SuspendExecution {
	myModel.colaClientes.insert(cli);
    sendTraceNote("ClientQueueLength: "+ myModel.colaClientes.length());
    if(!myModel.colaDependientes.isEmpty())
    {
    	
    }


	}

}
