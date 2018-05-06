package guauguapp;

import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//SCRAPER PARA LA PAGINA DE MASCOTEROS
public class Scraper {
    
 
		
   /* public static void main (String args[]) {
      	int maxPages = 3;
    	//-------------------------------------------------------MASCOTEROS-----------------------------------------------------------------
    	Mascoteros ob = new Mascoteros();
        for (int k = 0 ; k < ob.animals.size() ; k++){  
        	for (int h = 0 ; h < ob.categorias.get(k).size() ; h++){   		
        		System.out.println("-------------------------------------------------------------------------");
        		System.out.println("Animal :" + ob.animals.get(k) + " Categoria :" + ob.categorias.get(k).get(h));
        		System.out.println("-------------------------------------------------------------------------");
        		
	        	for(int i=1; i<maxPages; i++) {		//Paginas de cada seccion que se quieren explorar						
	        		
		            String urlPage = String.format(ob.url,ob.animals.get(k),ob.categorias.get(k).get(h),i);		//url que cambia en cada iteracion
		            System.out.println("Comprobando entradas de: "+urlPage);
					
		            if (getStatusConnectionCode(urlPage) == 200) {		// Compruebo si me da un 200 al hacer la petición
								                
		                Document document = getHtmlDocument(urlPage);		// Obtengo el HTML de la web en un objeto Document
		                		                 
		                Elements entradas = document.select("div.product-card-content");		// Busco todos los productos de esa pagina que estan dentro del div con esa clase
		                Elements links = document.select("a.add_click");		//.not("div.col-md-offset-2.col-md-4.col-xs-12");
		                		                		                
		                for (Element elem : entradas) {		// Parseo cada uno de los productos y relleno los vectores
		                	String nombre = elem.getElementsByClass("list_name_prod_box").text();
		                    String descripcion = elem.getElementsByClass("product_short_description").text();
		                    String precio = elem.getElementsByClass("product-pricing-now").text();

	                  		ob.nombres.add(nombre);
		                    ob.descripciones.add(descripcion);
		                    ob.precios.add(precio);   			                    
		                }
		                
		                for (Element el : links) {		// Parseo cada uno de los links de los productos y relleno el vector
		                    String enlace = (el.attr("href"));               
		                    ob.enlaces.add(enlace);  
		                }
		              
		                ob.imprimirPorPantalla();
		                ob.comprobacionNumero();
		                	            
		            }else{
		                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
		            }
	        	}
        	}
        }
        //----------------------------------------------------------FIN MASCOTEROS---------------------------------------------------------------------------
        
    	//----------------------------------------------------------------INICIO TIENDANIMAL-----------------------------------------------------------------
        TiendAnimal ti = new TiendAnimal();
        		
		String urlPage = String.format(ti.url);		//url que AUN NO cambia en cada iteracion
		System.out.println("Comprobando entradas de: "+urlPage);
					
		if (getStatusConnectionCode(urlPage) == 200) {		// Compruebo si me da un 200 al hacer la petición
						
			Document document = getHtmlDocument(urlPage);		// Obtengo el HTML de la web en un objeto Document
			                			 
			Elements entradas = document.select("div.wrap");		// Busco todas las historias de esa pagina que estan dentro del div con esa clase
			                      
			for (Element elem : entradas) {		//Parseo cada uno o de los productos y relleno los vectores
			      String descripcion = elem.getElementsByClass("desc").text();
			      String nombre = elem.getElementsByClass("js-enhanced_product").text();
                  String precio = elem.getElementsByClass("price_val").text();
                  String enlace = elem.getElementsByClass("js-enhanced_product").attr("href");

                  if(descripcion.length()>1 && nombre.length()>1 && precio.length()>1 ){
	                  ti.nombres.add(nombre);
	                  ti.precios.add(precio);	                    
	                  ti.descripciones.add(descripcion);
	                  ti.enlaces.add(enlace);
				   }
			}
			                	              
			ti.imprimirPorPantalla();
			ti.comprobacionNumero();
			
 
		}else{
		  System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(urlPage));	                
		}
	        	    
      //----------------------------------------------------------------FIN TIENDANIMAL-----------------------------------------------------------------
      
    }
	*/
	
    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK					300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request			403 Forbidden
     * 		404 Not Found			500 Internal Server Error
     * 		502 Bad Gateway			503 Service Unavailable
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
