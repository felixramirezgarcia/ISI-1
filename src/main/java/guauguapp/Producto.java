package guauguapp;

import java.text.Normalizer;
import java.util.Objects;

public class Producto {

    public String nombre = new String();
    public String descripcion = new String();
    public String precio = new String();
    public String enlace = new String();
    public String imagen = new String();
    public int tienda ;
	
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
		this.tienda = 2;
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
        	g = nombre + descripcion +  enlace + precio + imagen + " la tienda es " +tienda;
        	return g;       
	}
	
	public void setTienda(int numtiemda) {
		this.tienda = numtiemda;
	}
	
	 public static String acortar(String cadena, int n) {
	        Objects.requireNonNull(cadena, "La cadena no puede ser nula.");
	        int indice = n > cadena.length() ? 0 : cadena.length() - n;
	        return cadena.substring(0, indice);
	 }
	 
	 public static String limpiarAcentos(String cadena) {
		    String limpio =null;
		    if (cadena !=null) {
		        String valor = cadena;
		        valor = valor.toUpperCase();
		        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
		        limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
		        limpio = limpio.replaceAll("ñ", "n");
		        limpio = limpio.replaceAll("Ñ", "N");
		        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
		    }
		    return limpio;
	 }
	

}