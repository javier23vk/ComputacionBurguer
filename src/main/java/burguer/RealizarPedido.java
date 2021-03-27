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

		myModel.colaPedidos.insert(dep);
		if (!myModel.colaCocineros.isEmpty())
		{
			Cocinero coc = myModel.colaCocineros.first();
			myModel.colaCocineros.remove(coc);
			myModel.colaPedidos.remove(dep);
			PrepararHamburguesa event = new PrepararHamburguesa(myModel, "PrepararHamburguesa", true);
			event.schedule(coc, dep, new TimeSpan(myModel.getTiempoServicioCocineros(), TimeUnit.MINUTES));
		}

	}

}
