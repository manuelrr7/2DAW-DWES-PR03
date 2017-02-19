package pr.modelo.beans;

import java.io.*;



@SuppressWarnings("serial")
public class BeanUsuario implements Serializable {

	private String login;
	private String clave;
	private String nombre;
	public BeanUsuario()
	{
		
	}
	

	public BeanUsuario(String login, String clave, String nombre)
	{
		this.login = login;
		this.clave = clave;
		this.nombre = nombre;
	}


	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
}
