package burguer;
import desmoj.core.simulator.*;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.*;
public class Modelo extends Model {

	protected static int NUM_DEPENDIENTES = 5;
	protected static int NUM_COCINEROS = 1;
	private ContDistExponential TiempoLlegadaClientes;
	private ContDistExponential TiempoServicioDependientes;
	private ContDistExponential TiempoServicioCocineros;
	private ContDistExponential TiempoPagoClientes;
	
	protected Queue<Cliente> colaClientes;
	protected Queue<Dependiente> colaDependientes;
	protected Queue<Cocinero> colaCocineros;




	public Modelo(Model model, String modelName , boolean showInReport, boolean showInTrace) {
		super(model, modelName, showInReport, showInTrace);
	}

	@Override
	public String description() {
		return "Modelo de la hamburgueseria";
	}

	@Override
	public void doInitialSchedules() {
		ClienteGenerator clienteGenerator =
	            new ClienteGenerator(this, "Cliente Generator", true);
	   
		clienteGenerator.schedule(new TimeSpan(0));
	}

	@Override
	public void init() {
		TiempoLlegadaClientes= new ContDistExponential(this, "TiempoLlegadaClientesStream",
                   5.0, true, false);
		TiempoServicioDependientes= new ContDistExponential(this, "TiempoServicioDependientessStream",
                4.0, true, false);
		TiempoServicioCocineros= new ContDistExponential(this, "TiempoServicioCocinerosStream",
                9.0, true, false);
		TiempoPagoClientes= new ContDistExponential(this, "TiempoPagoClientesStream",
                2.0, true, false);
		
		
		TiempoLlegadaClientes.setNonNegative(true);
		TiempoServicioDependientes.setNonNegative(true);
		TiempoServicioCocineros.setNonNegative(true);
		TiempoPagoClientes.setNonNegative(true);
		
		
		colaClientes = new Queue<Cliente>(this, "Clientes Queue", true, true);
		colaDependientes = new Queue<Dependiente>(this, "Dependientes Queue", true, true);
		colaCocineros = new Queue<Cocinero>(this, "Cocineros Queue", true, true);

		Dependiente dep;
		for (int i = 0; i < NUM_DEPENDIENTES ; i++)
		{
			   dep = new Dependiente(this, "Dependiente", true);
		       colaDependientes.insert(dep);
		 }
		
		Cocinero coc;
		for (int i = 0; i < NUM_COCINEROS ; i++)
		{
			   coc = new Cocinero(this, "Cocinero", true);
		       colaCocineros.insert(coc);
		 }
	}

	
	public double getTiempoLlegadaClientes() 
	{
		return TiempoLlegadaClientes.sample();
	}
	
	public double getTiempoServicioDependientes() 
	{
		return TiempoServicioDependientes.sample();
	}
	
	public double getTiempoServicioCocineros() 
	{
		return TiempoServicioCocineros.sample();
	}
	
	public double getTiempoPagoClientes() 
	{
		return TiempoPagoClientes.sample();
	}
	
	public static void main(java.lang.String[] args) {

		
		Modelo model = new Modelo(null, "Burguer Model", true, true);
		Experiment exp = new Experiment("BurguerExperiment");
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true); 
		exp.stop(new TimeInstant(1500, TimeUnit.MINUTES));   
		exp.tracePeriod(new TimeInstant(0), new TimeInstant(100, TimeUnit.MINUTES));
		exp.debugPeriod(new TimeInstant(0), new TimeInstant(50, TimeUnit.MINUTES));
		exp.start();
		exp.report();
		exp.finish();
	}
}
