package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

import java.util.concurrent.TimeUnit;

public class PrepararHamburguesa extends Event<Cocinero> {
	private Modelo myModel;

	public PrepararHamburguesa(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		myModel = (Modelo)arg0;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Cocinero coc) throws SuspendExecution {
		// TODO Auto-generated method stub
		//if(!myModel.colaCocineros.isEmpty()){
		if(!myModel.colaClientesEsperandoComida.isEmpty()){
			Cocinero coci = myModel.colaCocineros.first();
			myModel.colaCocineros.remove(coci);
			myModel.colaCocineroComidaLista.insert(coci);
			EntregaComida evento = new EntregaComida(myModel,"entrega comida",true);
			evento.schedule(coci,new TimeSpan(myModel.getTiempoServicioCocineros(), TimeUnit.MINUTES));

		}

	}

}
