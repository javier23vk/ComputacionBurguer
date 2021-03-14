package burguer;

import java.util.concurrent.TimeUnit;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class EventoLlegadaCliente  extends Event<Cliente>{

	private Modelo myModel;
	
	 public EventoLlegadaCliente(Model owner, String name, boolean showInTrace) {
	      super(owner, name, showInTrace);
	      // store a reference to the model this event is associated with
	      myModel = (Modelo)owner;
	   }
	 
	public void eventRoutine(Cliente cli) throws SuspendExecution {
		myModel.colaClientes.insert(cli);
	    sendTraceNote("ClientQueueLength: "+ myModel.colaClientes.length());
	    if(!myModel.colaDependientes.isEmpty())
	    {

	         Dependiente dep = myModel.colaDependientes.first();

	         myModel.colaDependientes.remove(dep);

	         myModel.colaDependientes.remove(cli);

	         // create a service end event
	         ServiceEndEvent serviceEnd = new ServiceEndEvent (myModel,
	                                                       "ServiceEndEvent", true);

	         // and place it on the event list
	         serviceEnd.schedule(dep, cli, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
	    }
	}

}
