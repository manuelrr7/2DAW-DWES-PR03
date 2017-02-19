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


public class AccionRegistro implements Accion {
 

	private String vista;
	private final String vistaOK = "login.jsp";
	private final String vistaError = "gesError.jsp";

	private BeanUsuario modelo = new BeanUsuario();
	

	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private pr.modelo.beans.BeanError error;
	

	public AccionRegistro()
	{

	}

	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		BeanCaptcha beanCaptcha = new BeanCaptcha();
		ListaCaptcha listaCaptcha= new ListaCaptcha();
		try {
			conexion = DS.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select * from captcha");
			while (rs.next()) {
				beanCaptcha.setArchivo(rs.getString("archivo"));
				beanCaptcha.setNombre(rs.getString("codigo"));
				System.out.println(rs.getString("codigo"));
				listaCaptcha.add(beanCaptcha);
			}

			 int alea = (int) (Math.random() * listaCaptcha.size()) + 1;
			String img=listaCaptcha.get(alea).getArchivo();
			System.out.println("prue"+alea+" tam"+listaCaptcha.size());
			request.getSession().setAttribute("Msg", img); 
			System.out.println("prueba");

			request.getRequestDispatcher("login.jsp").forward(request, response); 
			System.out.println("pruefin");

		} catch (SQLException e) {
			error = new BeanError(1,"Error en conexión a base de datos",e);
			resultado = false;
		}
		
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