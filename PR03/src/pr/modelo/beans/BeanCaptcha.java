package pr.modelo.beans;

import java.io.Serializable;

public class BeanCaptcha implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String archivo;
	private String nombre;
	
	
	public BeanCaptcha() {
		

	}

	
	public BeanCaptcha(String archivo, String nombre) {
		
		this.archivo = archivo;
		this.nombre = nombre;
	}


	public String getArchivo() {
		return archivo;
	}


	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
