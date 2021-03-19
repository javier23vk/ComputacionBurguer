package burguer;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class ServiceEndEvent extends EventOf2Entities<Dependiente, Cliente> {

	private Modelo myModel;

	public ServiceEndEvent(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Dependiente dep, Cliente cli) {
	      sendTraceNote(cli + " leaves the terminal");
	      if (!myModel.colaClientes.isEmpty()) {
	    	  // remove the first waiting truck from the queue
	          Cliente nextClient = myModel.colaClientes.first();
	          myModel.colaClientes.remove(nextClient);
	          ServiceEndEvent event = new ServiceEndEvent(myModel, "ServiceEndEvent", true);
	          // and schedule it for at the appropriate time
	          event.schedule(dep, nextClient, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
	      }
	      else {
	          // NO, there are no trucks waiting

	          // --> the van carrier is placed on its parking spot
	          myModel.colaDependientes.insert(dep);

	          // the VC is now waiting for a new customer to arrive
	       }

	}

}
