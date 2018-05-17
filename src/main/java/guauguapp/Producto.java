package guauguapp;

import java.util.Objects;

public class Producto {

    public String nombre = new String();
    public String descripcion = new String();
    public String precio = new String();
    public String enlace = new String();
    public String imagen = new String();
	
	public Producto(String nombre, String descripcion, String precio , String enlace){
		this.nombre= nombre;
		this.descripcion = descripcion;	    
		this.precio = precio;
		this.enlace = enlace;
	}
	
	public Producto(String nombre, String descripcion, String precio , String enlace,String imagen){
		this.nombre= nombre;
		this.descripcion = descripcion;	    
		this.precio = precio;
		this.enlace = enlace;
		this.imagen = imagen;
	}
	
	public Producto(String nombre, String descripcion, String precio){
		this.nombre= nombre;
		this.descripcion = descripcion;
		
	    String pre = acortar(precio , 1);    
		this.precio = pre;
	}
	
	public void setEnlace(String enlace) {
		this.enlace = enlace ;
	}
	
	public void setImagen(String imag) {
		this.imagen = imag ;
	}
	
	public String imprimirPorPantalla() {
        	String g = new String();
        	g = nombre + descripcion +  enlace + precio ;
        	return g;       
	}
	
	 public static String acortar(String cadena, int n) {
	        Objects.requireNonNull(cadena, "La cadena no puede ser nula.");
	        int indice = n > cadena.length() ? 0 : cadena.length() - n;
	        return cadena.substring(0, indice);
	    }
	

}