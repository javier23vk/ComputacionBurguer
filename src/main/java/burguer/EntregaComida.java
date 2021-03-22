package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class EntregaComida extends Event<Cocinero> {
    private Modelo myModel;

    public EntregaComida(Model model, String s, boolean b) {
        super(model, s, b);
        myModel = (Modelo)model;

    }

    public void eventRoutine(Cocinero cocinero) throws SuspendExecution {
    if(!myModel.colaCocineroComidaLista.isEmpty()){


        RecogerComida event = new RecogerComida(myModel,"comida lista entrega",true);
        event.schedule(new Dependiente(myModel,"dep",true));
    }
    }
}
