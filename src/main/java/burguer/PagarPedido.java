package burguer;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

import java.util.concurrent.TimeUnit;

public class PagarPedido extends EventOf2Entities<Cliente, Dependiente> {

	private Modelo myModel;

	public PagarPedido(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		myModel = (Modelo)arg0;

	}

	@Override
	public void eventRoutine(Cliente cli, Dependiente dep) {

		if(!myModel.colaDependienteEsperandoPago.isEmpty() && !myModel.colaClientesEsperandoComida.isEmpty()){
			Dependiente depend = myModel.colaDependienteEsperandoPago.first();
			myModel.colaDependienteEsperandoPago.remove(depend);
			Cliente client = myModel.colaClientesEsperandoComida.first();
			myModel.colaClientesEsperandoComida.remove(client);

			myModel.colaDependientes.insert(depend);
			RealizarPedido pg = new RealizarPedido(myModel,"Vuelta normal",true);
			pg.schedule(dep,cli,new TimeSpan(myModel.getTiempoServicioDependientes(), TimeUnit.MINUTES));
		}
	}

}
