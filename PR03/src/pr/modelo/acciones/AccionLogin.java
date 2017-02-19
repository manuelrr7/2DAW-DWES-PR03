
package pr.modelo.acciones;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import pr.controlador.Accion;
import pr.modelo.beans.*;



public class AccionLogin implements Accion {
 

	private String vista;
	private final String vistaOK = "WEB-INF/home.jsp";
	private final String vistaError = "WEB-INF/home.jsp";

	private BeanUsuario modelo = new BeanUsuario();
	

	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private pr.modelo.beans.BeanError error;
	

	public AccionLogin()
	{

	}

	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		String login, clave, codigo;
		
//		if (request.getParameter("accion").equals("volver")) {
//			request.getRequestDispatcher(vistaOK).forward(request, response); 	
//		} else {
			login = request.getParameter("login");
			clave = request.getParameter("clave");
			codigo = request.getParameter("capt");
			try {
				conexion = DS.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("select login, clave, codigo from usuarios, captcha where login = '"+login+"' and codigo ='"+codigo+"' ");
				if (rs.next()) {
					System.out.println("Compara "+rs.getString("clave")+" con "+clave);
					if (!rs.getString("clave").equals(clave)) {
						error = new BeanError(2,"La clave no coincide.");
						resultado = false;
					}
					System.out.println("Compara "+rs.getString("codigo")+" con "+codigo);
					if (!rs.getString("codigo").equals(codigo)) {
						error = new BeanError(2,"El captcha no coincide.");
						resultado = false;
					}
					
				}
				else
				{
					error = new BeanError(3,"El login no existe.");
					resultado = false;
				}
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos",e);
				resultado = false;
			}
	//	}

		
		if (resultado==true)
			vista = vistaOK;
		else
			vista = vistaError;
		return resultado;
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
