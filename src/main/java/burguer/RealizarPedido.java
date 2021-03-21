package burguer;



import java.util.concurrent.TimeUnit;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class RealizarPedido extends EventOf2Entities<Dependiente, Cliente> {

	private Modelo myModel;

	public RealizarPedido(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		myModel = (Modelo)owner;
	}

	@Override
	public void eventRoutine(Dependiente dep, Cliente cli) {
	      sendTraceNote(cli + " leaves the terminal");
	      if (!myModel.colaClientes.isEmpty() && !myModel.colaDependientes.isEmpty()) {
	    	  // Coges el cliente
	          Cliente client = myModel.colaClientes.first();
	          myModel.colaClientes.remove(client);

	          //meto el cliente a espera de comida
	          myModel.colaClientesEsperandoComida.insert(client);
	          //coges el dependiente
	          Dependiente depend = myModel.colaDependientes.first();
			  myModel.colaDependientes.remove(depend);

			  //metes el dependiente en la cola de esperar comida
			  myModel.colaDependientesEsperandoComida.insert(depend);



			  // Creas el evento de pagar y preparar comida
	          PagarPedido event =  new PagarPedido(myModel, "PagarPedidoEvent", true);
	          PrepararHamburguesa prepaEvent= new PrepararHamburguesa(myModel,"PrepararPedidoEvent",true);
	          RecogerComida recogerEvento = new RecogerComida(myModel,"recoger Comida",true);
	          // lanzar eventos
	          event.schedule(client, depend, new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
			  Cocinero coci = myModel.colaCocineros.first();
			  myModel.colaCocineros.remove(coci);
			  prepaEvent.schedule(coci,new TimeSpan(myModel.getTiempoServicioCocineros(), TimeUnit.MINUTES));
	      }
	      else {
	          // NO, there are no clientes waiting

	          // --> the van carrier is placed on its parking spot
	          myModel.colaDependientes.insert(dep);

	          // the VC is now waiting for a new customer to arrive
	       }

	}

}
