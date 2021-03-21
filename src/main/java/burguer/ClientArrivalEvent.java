package burguer;

import java.util.concurrent.TimeUnit;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class ClientArrivalEvent extends Event<Cliente> {

	private Modelo myModel;

	public ClientArrivalEvent(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		myModel = (Modelo)owner;
	}

	@Override
	public void eventRoutine(Cliente cli) throws SuspendExecution {
	myModel.colaClientes.insert(cli);
    sendTraceNote("ClientQueueLength: "+ myModel.colaClientes.length());
    if(!myModel.colaDependientes.isEmpty())
    {
    	Dependiente dep= myModel.colaDependientes.first();
    	myModel.colaDependientes.remove(dep);
    	
    	myModel.colaClientes.remove(cli);
    	RealizarPedido serviceEnd = new RealizarPedido(myModel,
                "ServiceEndEvent", true);
        serviceEnd.schedule(dep, cli, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));

    }


	}

}
