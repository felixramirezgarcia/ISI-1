package guauguapp;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//SCRAPER PARA LA PAGINA DE MASCOTEROS
public class Scraper {
    
    public static final int maxPages = 9;
		
    public static void main (String args[]) {
    	
    	//-------------------------------------------------------MASCOTEROS-----------------------------------------------------------------
    	Mascoteros ob = new Mascoteros();
        for (int k = 0 ; k < ob.animals.size() ; k++){  
        	for (int h = 0 ; h < ob.categorias.get(k).size() ; h++){   		
        		System.out.println("-------------------------------------------------------------------------");
        		System.out.println("Animal :" + ob.animals.get(k) + " Categoria :" + ob.categorias.get(k).get(h));
        		System.out.println("-------------------------------------------------------------------------");
        		//Paginas de cada seccion que se quieren explorar
	        	for(int i=1; i<maxPages; i++) {
	        		
	        		//url que cambia en cada iteracion
		            String urlPage = String.format(ob.url,ob.animals.get(k),ob.categorias.get(k).get(h),i);
		            System.out.println("Comprobando entradas de: "+urlPage);
					
		            // Compruebo si me da un 200 al hacer la petición
		            if (getStatusConnectionCode(urlPage) == 200) {
						
		                // Obtengo el HTML de la web en un objeto Document
		                Document document = getHtmlDocument(urlPage);
		                
		                // Busco todas las historias de esa pagina que estan dentro del div con esa clase 
		                Elements entradas = document.select("div.product-card-content"); //.not("div.col-md-offset-2.col-md-4.col-xs-12");
		                Elements links = document.select("a.add_click");
		                
		                //guardo los productos en las variables
		                ArrayList<String> nombres = new ArrayList<String>();
		                ArrayList<String> descripciones = new ArrayList<String>();
		                ArrayList<String> precios = new ArrayList<String>();
		                ArrayList<String> enlaces = new ArrayList<String>();
		                
		                
		                // Parseo cada una o de los productos y relleno los vectores
		                for (Element elem : entradas) {
		                	String nombre = elem.getElementsByClass("list_name_prod_box").text();
		                    String descripcion = elem.getElementsByClass("product_short_description").text();
		                    String precio = elem.getElementsByClass("product-pricing-now").text();
		
		                    nombres.add(nombre);
		                    descripciones.add(descripcion);
		                    precios.add(precio);
		                    
		                }
		                
		                // Parseo cada una de los links de los productos y relleno el vector
		                for (Element el : links) {
		                    String enlace = (el.attr("href"));               
		                    enlaces.add(enlace);   
		                }
		              
		                //imprimir por pantalla
		                for(int j = 0 ; j < nombres.size() ; j++) {
		                	System.out.println(enlaces.get(j));
		                	System.out.println(precios.get(j)+nombres.get(j)+"\n"+descripciones.get(j));          	              	
		                }
		                //comprobacion de numero de atributos esnifados en cada pagina
		                System.out.println("enlaces: "+ enlaces.size() + " nombres :" + nombres.size() + " descripcion :" + descripciones.size()+ " precios : "+ precios.size());
		
		            
		            }else{
		                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
		                break;
		            }
	        	}
        	}
        }
        //----------------------------------------------------------FIN MASCOTEROS---------------------------------------------------------------------------
    }
	
	
    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK			300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		403 Forbidden
     * 		404 Not Found		500 Internal Server Error
     * 		502 Bad Gateway		503 Service Unavailable
     * @param url
     * @return Status Code
     */
    public static int getStatusConnectionCode(String url) {
		
        Response response = null;
		
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }
	
	
    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     * @param url
     * @return Documento con el HTML
     */
    public static Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }

        return doc;

    }
    
    
}
