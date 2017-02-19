package pr.controlador;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import pr.modelo.beans.BeanError;


public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";


  private DataSource dsBdpr03;

	private ServletContext sc; 



	public void init(ServletConfig config) throws ServletException {

	    super.init(config);

	    try {
	    	InitialContext initCtx = new InitialContext();
	    	setDsBdpr03((DataSource) initCtx.lookup("java:jboss/datasources/dspr03"));
	    	if (getDsBdpr03()==null)
	    		System.out.println("dsBdpr03 es null.");
	    	sc = config.getServletContext();

	    	sc.setAttribute("dsBdpr03", getDsBdpr03());
	    } 
	    catch(NamingException ne)
	    {

	        System.out.println("Error: Método init(). tipo NamingException.");
	    } 
	}


	public void destroy() {

      sc.removeAttribute("dsBdpr03");

      sc = null;
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    doPost(request,response);		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession();

	    AyudaSolicitud ayudaSol = new AyudaSolicitud(request);

	    Accion accion = ayudaSol.getAccion();
	
	    accion.setSc(sc);
	  
	    accion.setDS (dsBdpr03);
	   
	    accion.setSesion(sesion);
	   
	    if (accion.ejecutar(request,response))
	    {
	   
	      String vista = accion.getVista();
	      
	     
	      request.setAttribute("modelo",accion.getModelo());
	      
	      
	      RequestDispatcher rd = request.getRequestDispatcher(vista);
	      rd.forward(request,response);
	    }
	    else
	    {
	    
	      gesError(accion.getVista(),accion.getError(),request,response);
	    }
	    
	}
	

  private void gesError(String vistaError, BeanError excepcion, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher(vistaError);
    request.setAttribute("error",excepcion);
    rd.forward(request,response);
  }


	public void setDsBdpr03(DataSource dsBdpr03) {
		this.dsBdpr03 = dsBdpr03;
	}


	public DataSource getDsBdpr03() {
		return dsBdpr03;
	}

}
