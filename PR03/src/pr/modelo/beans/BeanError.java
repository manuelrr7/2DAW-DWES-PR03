package pr.modelo.beans;

import java.io.*;


@SuppressWarnings("serial")
public class BeanError extends Exception implements Serializable{
	private int codError;
	private String mensError;
	private Exception excepcion = null;
	  
	  public BeanError(int codError, String mensError)
	  {
	    super(mensError);
	    this.setCodError(codError);
	    this.setMensError(mensError);
	  }
	  
	  public BeanError(int codError, String mensError, Exception excepcion)
	  {
	    super(mensError);
	    this.setCodError(codError);
	    this.setMensError(mensError);
	    this.setExcepcion(excepcion);
	  }

	private void setCodError(int codError) {
		this.codError = codError;
	}

	@SuppressWarnings("unused")
	public int getCodError() {
		return codError;
	}


	private void setMensError(String mensError) {
		this.mensError = mensError;
	}


	@SuppressWarnings("unused")
	public String getMensError() {
		return mensError;
	}


	private void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}


	@SuppressWarnings("unused")
	public Exception getExcepcion() {
		return excepcion;
	}

}
