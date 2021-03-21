package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class PrepararHamburguesa extends Event<Cocinero> {

	public PrepararHamburguesa(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Cocinero coc) throws SuspendExecution {
		// TODO Auto-generated method stub
		
	}

}
