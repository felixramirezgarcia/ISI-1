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
	
    public static final String url = "https://www.mascoteros.com/%s/%s?page=%s";
    
    public static final int maxPages = 9;
	
	
    public static void main (String args[]) {
    	
    	ArrayList<String> animals = inicializarAnimales();
    	ArrayList<ArrayList<String>> categorias = inicializarCategorias();
    	
        for (int k = 0 ; k < animals.size() ; k++){  
        	for (int h = 0 ; h < categorias.get(k).size() ; h++){
        		
        		System.out.println("-------------------------------------------------------------------------");
        		System.out.println("Animal :" + animals.get(k) + " categoria :" + categorias.get(k).get(h));
        		System.out.println("-------------------------------------------------------------------------");
        		
	        	for(int i=1; i<maxPages; i++) {
	        		
	        		//url que cambia en cada iteracion
		            String urlPage = String.format(url,animals.get(k),categorias.get(k).get(h),i);
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
		                
		                
		                // Parseo cada una de las entradas y relleno los vectores
		                for (Element elem : entradas) {
		                	String nombre = elem.getElementsByClass("list_name_prod_box").text();
		                    String descripcion = elem.getElementsByClass("product_short_description").text();
		                    String precio = elem.getElementsByClass("product-pricing-now").text();
		
		                    nombres.add(nombre);
		                    descripciones.add(descripcion);
		                    precios.add(precio);
		                    
		                }
		                
		                // Parseo cada una de las entradas y relleno los vectores
		                for (Element el : links) {
		                    String enlace = (el.attr("href"));               
		                    enlaces.add(enlace);   
		                }
		              
		                //imprimir por pantalla
		                for(int j = 0 ; j < nombres.size() ; j++) {
		                	System.out.println(enlaces.get(j));
		                	System.out.println(precios.get(j)+nombres.get(j)+"\n"+descripciones.get(j));          	              	
		                }
		                
		                System.out.println("enlaces: "+ enlaces.size() + " nombres :" + nombres.size() + " descripcion :" + descripciones.size()+ " precios : "+ precios.size());
		
		            
		            }else{
		                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
		                break;
		            }
	        	}
        	}
        }
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
    
    public static ArrayList<String> inicializarAnimales() {
    	ArrayList<String> animals = new ArrayList<String>();
    	animals.add("perros");
    	animals.add("gatos");
    	animals.add("roedores");
    	animals.add("peces");
    	animals.add("reptiles");
    	animals.add("caballos");
    	animals.add("pajaros");
    	return animals;
    }
    
    public static ArrayList<ArrayList<String>> inicializarCategorias() {
    	ArrayList<ArrayList<String>> categorias = new ArrayList<ArrayList<String>>();
    	
    	ArrayList<String> perros = new ArrayList<String>();
    	perros.add("alimentacion");
    	perros.add("juguetes");
    	perros.add("en-casa");
    	perros.add("entrenamiento");
    	perros.add("peluqueria-e-higiene");
    	perros.add("snacks-y-huesos");
    	perros.add("paseo");
    	perros.add("transporte-y-viaje");
    	perros.add("antiparasitarios");
    	perros.add("vitaminas-y-suplementos");
    	
    	ArrayList<String> gatos = new ArrayList<String>();
    	gatos.add("alimentacion");
    	gatos.add("arenas-higienicas");
    	gatos.add("collares-arneses-y-correas");
    	gatos.add("transporte-y-viaje");
    	gatos.add("antiparasitarios");
    	gatos.add("vitaminas-y-suplementos");
    	gatos.add("snacks-y-complementos");
    	gatos.add("juguetes");
    	gatos.add("en-casa");
    	gatos.add("comportamiento");
    	gatos.add("peluqueria-e-higiene");
    	
    	ArrayList<String> roedores = new ArrayList<String>();
    	roedores.add("alimentacion");
    	roedores.add("correas-y-arneses-roedores");
    	roedores.add("transporte");
    	roedores.add("henos-y-snaks");
    	roedores.add("jaulas");
    	roedores.add("salud-e-higiene");
    	roedores.add("accesorios-para-jaula");
    	
    	ArrayList<String> peces = new ArrayList<String>();
    	peces.add("alimentacion");
    	peces.add("acuarios");
    	peces.add("estanques");
    	peces.add("iluminacion");
    	peces.add("accesorios-de-acuario");
    	peces.add("decoracion");
    	peces.add("filtros-y-bombas");
    	peces.add("mantenimiento-y-tratamiento");
    	
    	ArrayList<String> reptiles = new ArrayList<String>();
    	reptiles.add("alimentacion");
    	reptiles.add("luz-y calefaccion");
    	reptiles.add("salud-y-higiene");
    	reptiles.add("tortugueras");
    	reptiles.add("humedad");
    	reptiles.add("medicion-y-regulacion");
    	reptiles.add("terrarios-y-accesorios");
    	
    	ArrayList<String> caballos = new ArrayList<String>();
    	caballos.add("alimentacion");
    	caballos.add("higiene-y-cuidados");
    	caballos.add("sillas");
    	caballos.add("jinete");
    	
    	ArrayList<String> pajaros = new ArrayList<String>();
    	pajaros.add("alimentacion");
    	pajaros.add("jaula");
    	pajaros.add("salud-e-higiene");
    	pajaros.add("snacks");
    	pajaros.add("accesorios-para-jaula");
    	pajaros.add("otros");	
    	
    	categorias.add(perros);
    	categorias.add(gatos);
    	categorias.add(roedores);
    	categorias.add(peces);
    	categorias.add(reptiles);
    	categorias.add(caballos);
    	categorias.add(pajaros);
    	
    	return categorias;
    }
}
