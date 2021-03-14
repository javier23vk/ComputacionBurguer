package burguer;
import desmoj.core.simulator.*;
import desmoj.core.dist.*;
public class Modelo extends Model {

	protected static int NUM_DEPENDIENTES = 5;
	protected static int NUM_COCINEROS = 1;
	private ContDistExponential TiempoLlegadaClientes;
	private ContDistExponential TiempoServicioDependientes;
	private ContDistExponential TiempoServicioCocineros;
	protected Queue<Cliente> colaClientes;
	protected Queue<Dependiente> colaDependientes;
	protected Queue<Cocinero> colaCocineros;
	
	public Modelo(Model arg0, String arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	@Override
	public String description() {
		return "Modelo de la hamburgueseria";
	}

	@Override
	public void doInitialSchedules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		TiempoLlegadaClientes= new ContDistExponential(this, "TiempoLlegadaClientesStream",
                   3.0, true, false);
		TiempoServicioDependientes= new ContDistExponential(this, "TiempoServicioDependientessStream",
                3.0, true, false);
		TiempoServicioCocineros= new ContDistExponential(this, "TiempoServicioCocinerosStream",
                3.0, true, false);
		
		TiempoLlegadaClientes.setNonNegative(true);
		TiempoServicioDependientes.setNonNegative(true);
		TiempoServicioCocineros.setNonNegative(true);
		
		
		colaClientes = new Queue<Cliente>(this, "Clientes Queue", true, true);
		colaDependientes = new Queue<Dependiente>(this, "Dependientes Queue", true, true);
		colaCocineros = new Queue<Cocinero>(this, "Cocineros Queue", true, true);
		
		Dependiente dep;
		   for (int i = 0; i < NUM_DEPENDIENTES ; i++)
		   {
			   dep = new VanCarrier(this, "VanCarrier", true);
		      // put it on his parking spot
		      idleVCQueue.insert(VC);
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
	
	
}
