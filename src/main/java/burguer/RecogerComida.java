package burguer;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

import java.util.concurrent.TimeUnit;

public class RecogerComida extends Event<Dependiente> {
    private Modelo myModel;

    public RecogerComida(Model model, String s, boolean b) {
        super(model, s, b);
        myModel = (Modelo)model;

    }

    public void eventRoutine(Dependiente dependiente) throws SuspendExecution {
    if(!myModel.colaDependientesEsperandoComida.isEmpty() && !myModel.colaCocineroComidaLista.isEmpty())
    {
        Cocinero coci = myModel.colaCocineroComidaLista.first();
        myModel.colaCocineroComidaLista.remove(coci);
        myModel.colaCocineros.insert(coci);

        Dependiente dep = myModel.colaDependientesEsperandoComida.first();
        myModel.colaDependientesEsperandoComida.remove(dep);

        myModel.colaDependienteEsperandoPago.insert(dep);

        PagarPedido pg = new PagarPedido(myModel,"Esperando pago",true);
        pg.schedule(myModel.colaClientesEsperandoComida.first(),dep,new TimeSpan(myModel.getTiempoPagoClientes(), TimeUnit.MINUTES));


    }
    }
}
