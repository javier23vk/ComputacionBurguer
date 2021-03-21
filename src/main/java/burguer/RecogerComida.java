package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class RecogerComida extends Event<Dependiente> {
    public RecogerComida(Model model, String s, boolean b) {
        super(model, s, b);
    }

    public void eventRoutine(Dependiente dependiente) throws SuspendExecution {

    }
}
