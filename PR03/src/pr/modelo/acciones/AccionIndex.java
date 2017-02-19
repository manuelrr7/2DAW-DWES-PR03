package pr.modelo.acciones;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import pr.controlador.Accion;
import pr.modelo.beans.*;



public class AccionIndex implements Accion {
 

	private String vista;
	private final String vistaOK = "login.jsp";
	private final String vistaError = "gesError.jsp";
	private final String vistaForm= "login.jsp";
	private BeanUsuario modelo;
	

	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;
	private pr.modelo.beans.BeanError error;
	

	public AccionIndex()
	{
		
	}

	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		setVista(vistaForm);
		setModelo(new BeanUsuario());
		return false;
	}


	public String getVista() 
	{
		return vista;
	}

	public void setVista(String vista)
	{
		this.vista = vista;
	}

	public Object getModelo() 
	{
		return modelo;
	}

	public void setModelo(BeanUsuario modelo)
	{
	    this.modelo = modelo;
	}

	public void setSc(ServletContext sc) 
	{
		this.sc = sc;
	}

	public ServletContext getSc()
	{
	    return sc;
	}

	public void setError(pr.modelo.beans.BeanError error)
	{
	    this.error = error;
	}

	public BeanError getError() {
		return error;
	}


	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}

	public HttpSession getSesion() {
		return sesion;
	}
	
	public void setDS(DataSource ds)
	{
		DS = ds;
	}

}
