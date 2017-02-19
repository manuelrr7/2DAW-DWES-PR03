package pr.controlador;

import pr.modelo.acciones.*;


public abstract class FactoriaAcciones {
	
	public static Accion creaAccion(String accion)
	  {

		Accion accionF = new AccionIndex();

	    if (accion.equals("inicio"))
	      accionF = new AccionRegistro();
	    if (accion.equals("consultar"))
	      accionF = new AccionConsultar();
	    if (accion.equals("menu"))
	      accionF = new AccionLogin();
	    if (accion.equals("salir"))
	      accionF = new AccionIndex();

	    return accionF;
	  }

}
