package servicios_eafit.ayre;

public class Estudiante {

	private String nombres;
    	private String apellidos;
    	private String tipoDocumento;
    	private String numDocumento;
    	private String email;
    	private String telefono;
    	private String codigo;

        public Estudiante (String nombres, String apellidos, String tipoDocumento, String numDocumento, String email, String telefono, String codigo) {

            this.nombres = nombres;
            this.apellidos = apellidos;
            this.tipoDocumento = tipoDocumento;
            this.numDocumento = numDocumento;
            this.email = email;
            this.telefono = telefono;
            this.codigo = codigo;    

        }


    	public String getNombres() {
        	return nombres;
    	}

    	public void setNombres(String nombres) {
        	this.nombres = nombres;
    	}

   	    public String getApellidos() {
        	return apellidos;
    	}

    	public void setApellidos(String apellidos) {
        	this.apellidos = apellidos;
    	}

    	public String getTipoDocumento() {
        	return tipoDocumento;
    	}

    	public void setTipoDocumento(String tipoDocumento) {
        	this.tipoDocumento = tipoDocumento;
    	}

    	public String getNumDocumento() {
        	return numDocumento;
    	}

    	public void setNumDocumento(String numDocumento) {
        	this.numDocumento = numDocumento;
    	}

    	public String getEmail() {
        	return email;
    	}

    	public void setEmail(String email) {
        	this.email = email;
    	}

    	public String getTelefono() {
        	return telefono;
    	}

    	public void setTelefono(String telefono) {
        	this.telefono = telefono;
    	}

    	public String getCodigo() {
        	return codigo;
    	}

    	public void setCodigo(String codigo) {
        	this.codigo = codigo;
    	}
	
}
