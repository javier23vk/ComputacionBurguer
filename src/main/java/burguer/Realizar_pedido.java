package burguer;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class Realizar_pedido extends EventOf2Entities<Dependiente, Cliente> {

	private Modelo myModel;

	public Realizar_pedido(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		myModel = (Modelo)owner;
	}

	@Override
	public void eventRoutine(Dependiente dep, Cliente cli) {
	      sendTraceNote(cli + " leaves the terminal");
	      if (!myModel.colaClientes.isEmpty()) {
	    	  // remove the first waiting truck from the queue
	          Cliente nextClient = myModel.colaClientes.first();
	          myModel.colaClientes.remove(nextClient);
	          
	          //Realizar_pedido event = new Realizar_pedido(myModel, "ServiceEndEvent", true);
	          PagarPedido event =  new PagarPedido(myModel, "PagarPedidoEvent", true);
	          // and schedule it for at the appropriate time
	          event.schedule(dep, nextClient, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
	      }
	      else {
	          // NO, there are no clientes waiting

	          // --> the van carrier is placed on its parking spot
	          myModel.colaDependientes.insert(dep);

	          // the VC is now waiting for a new customer to arrive
	       }

	}

}
