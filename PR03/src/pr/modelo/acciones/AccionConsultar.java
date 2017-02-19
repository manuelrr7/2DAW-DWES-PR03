package pr.modelo.acciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import pr.controlador.Accion;
import pr.modelo.beans.BeanError;
import pr.modelo.beans.BeanUsuario;


public class AccionConsultar  implements Accion {
 

	private String vista;
	private final String vistaOK = "WEB-INF/consultar.jsp";
	private final String vistaError = "gesError.jsp";

	private BeanUsuario modelo = new BeanUsuario();
	

	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private pr.modelo.beans.BeanError error;
	

	public AccionConsultar()
	{

	}

	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		String login, nombre, pass;

		try {
			conexion = DS.getConnection();
			st = conexion.createStatement();

			rs = st.executeQuery("select * from usuarios");
			if (rs.next()) {
				if (rs.getString("login").equals(" ")) {
					error = new BeanError(2,"usuario nulo");
					resultado = false;
				}else{

					login=rs.getString("login");
					nombre=rs.getString("nombre");
					pass=rs.getString("clave");

					BeanUsuario beanUsu = new BeanUsuario(login, nombre,pass);

					
					PrintWriter out = response.getWriter();
					out.write("<html>");
					out.write("<head><title>Editar</title></head>");
					out.write("<body>");
					out.write("<h4>Datos usuario</h4>");
					out.write("<p>login: "+beanUsu.getLogin()+"</p>");
					out.write("<p>nombre: "+beanUsu.getNombre()+"</p>");
					out.write("<p>clave: "+beanUsu.getClave()+"</p>");
					out.write("<form action='controlador' method='post'>"
							+ "<input type='hidden' name='accion' value='menu'>"
							+ "<input name='volver' value='volver'"
							+ "type='submit' style='width: 84px; height: 33px'></form>");
					out.write("</body>");
					out.write("</html>");		
					
					out.close();
					
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

