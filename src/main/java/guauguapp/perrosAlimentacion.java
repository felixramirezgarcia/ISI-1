package guauguapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class lista
 */
@WebServlet("/perrosAlimentacion")
public class perrosAlimentacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public perrosAlimentacion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void escrapear(HttpServletRequest request, HttpServletResponse response , String accion)  throws ServletException, IOException {
    	ArrayList<Producto> resultado = new ArrayList<Producto>();
    	ArrayList<Producto> aux = new ArrayList<Producto>();
    	ArrayList<Producto> aux1 = new ArrayList<Producto>();
    	System.out.println(accion);
    	
    	//-----------------------------------------------------------------SCRAPING A MASCOTEROS--------------------------------------------------------------------
    			Mascoteros ob = new Mascoteros();
    			int profundidad = 2;
    	       		
    		    for(int i=1; i<profundidad;  i++) {		//Paginas que se quieren explorar						
    		        		
    		    	String urlPage = String.format(ob.url,ob.animals.get(0),ob.categorias.get(0).get(0),i);		//url que cambia en cada iteracion
    			            System.out.println("Comprobando entradas de: "+urlPage);
    						
    			            if (Scraper.getStatusConnectionCode(urlPage) == 200) {		// Compruebo si me da un 200 al hacer la petición
    									                
    			                Document document = Scraper.getHtmlDocument(urlPage);		// Obtengo el HTML de la web en un objeto Document
    			                		                 
    			                Elements entradas = document.select("div.product-card-content");		// Busco todos los productos de esa pagina que estan dentro del div con esa clase
    			                Elements links = document.select("a.add_click");		//.not("div.col-md-offset-2.col-md-4.col-xs-12");
    			                Elements imagenes = document.getElementsByTag("img");
    			                		                		                
    			                for (Element elem : entradas) {		// Parseo cada uno de los productos y relleno los vectores
    			                	String nombre = elem.getElementsByClass("list_name_prod_box").text();
    			                    String descripcion = elem.getElementsByClass("product_short_description").text();
    			                    String precio = elem.getElementsByClass("product-pricing-now").text();
    			                    
    			                    Producto p = new Producto(nombre,descripcion,precio);
    				                aux.add(p);

    	   			                    
    			                }
    			                
    			                for (int j = 0; j < aux.size(); j++) {
    								Element el = links.get(j);
    								String en = (el.attr("href"));
    								String rem = en.replace("#", "");
    								aux.get(j).setEnlace(rem);
    							}
    			                
    			                ArrayList<String> auxiliar = new ArrayList<String>();
    			                for (Element ima : imagenes) {	
    		    					String imagen = ima.getElementsByTag("img").attr("abs:data-src");
    		    					if(imagen.indexOf(".jpg")!=-1) {
    		    						auxiliar.add(imagen);
    		    						System.out.println("La url de la imagen es : " + imagen);
    		    					}		  		    					
    		    				}
    			                
    			                int g = 0;
    			                for (int j = 0; j < aux.size()-2; j=j+2) {
    				                Producto p = new Producto(aux.get(j).nombre,aux.get(j).descripcion,aux.get(j+1).precio,aux.get(j+1).enlace,auxiliar.get(g));
    				                resultado.add(p);
    				                g++;
								}
 		                	            
    			            }else{
    			                System.out.println("El Status Code no es OK es: "+Scraper.getStatusConnectionCode(urlPage));
    			            }
    		    }        		
    	        //-----------------------------------------------------------------SCRAPING A TIENDANIMAL--------------------------------------------------------------------
    			TiendAnimal ti = new TiendAnimal();	
    			String urlPage = String.format(ti.url);		
    			System.out.println("Comprobando entradas de: "+urlPage);
    						
    			if (Scraper.getStatusConnectionCode(urlPage) == 200) {		// Compruebo si me da un 200 al hacer la petición
    							
    				Document document = Scraper.getHtmlDocument(urlPage);		// Obtengo el HTML de la web en un objeto Document
    				                			 
    				Elements entradas = document.select("div.wrap");		// Busco todas las historias de esa pagina que estan dentro del div con esa clase
    				Elements imagenes = document.getElementsByTag("img"); 
    				
    				for (Element elem : entradas) {		//Parseo cada uno o de los productos y relleno los vectores
    				      String descripcion = elem.getElementsByClass("desc").text();
    				      String nombre = elem.getElementsByClass("js-enhanced_product").text();
    	                  String precio = elem.getElementsByClass("price_val").text();
    	                  String enlace = elem.getElementsByClass("js-enhanced_product").attr("href");
    	                  

    	                  if(descripcion.length()>1 && nombre.length()>1 && precio.length()>1 ){
    	                	  Producto p = new Producto(nombre,descripcion,precio,enlace);
    		                  aux1.add(p);
    					   }
    				}
    				
    				int i = 0;
    				for (Element ima : imagenes) {	
    					String imagen = ima.getElementsByTag("img").attr("abs:data-original");
    					if(imagen.indexOf(".jpg")!=-1 && imagen.indexOf("banners")==-1 && imagen.indexOf("logo")==-1 && imagen.indexOf("login")==-1 && i < aux1.size()) {
        					//System.out.println("La url de la imagen es : " + imagen);
        					aux1.get(i).setImagen(imagen);
        					i++;
    					}
    				}
    				i = 0;
    				
    				for (int z = 0; z < aux1.size(); z++) {    						
			                resultado.add(aux1.get(z));		               
					}
    				
    			/*for (int j = 0; j < resultado.size(); j++) {
    				System.out.println("El nombre es : " + resultado.get(j).nombre);
	                System.out.println("El descripcion es : " + resultado.get(j).descripcion);
	                System.out.println("El precio es : " + resultado.get(j).precio);
	                System.out.println("El enlace es : " + resultado.get(j).enlace);
	                System.out.println("la imagen es : " + resultado.get(j).imagen);
				}*/
    				
    			}else{
    			  System.out.println("El Status Code no es OK es: " + Scraper.getStatusConnectionCode(urlPage));	                
    			}
    	
    			request.setAttribute("txtresultado", resultado);
    			RequestDispatcher dispatcher = request.getRequestDispatcher("resultado.jsp");
    			dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PrintWriter out = response.getWriter();
		
		String accion = request.getParameter("accion");
		if(accion.equals("perros_al")) {
			this.escrapear(request,response,accion);			
		}
	}

}
