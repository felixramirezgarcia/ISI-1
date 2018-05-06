package guauguapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
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
		                		                		                
		                for (Element elem : entradas) {		// Parseo cada uno de los productos y relleno los vectores
		                	String nombre = elem.getElementsByClass("list_name_prod_box").text();
		                    String descripcion = elem.getElementsByClass("product_short_description").text();
		                    String precio = elem.getElementsByClass("product-pricing-now").text();
		                    
		                    Producto p = new Producto(nombre,descripcion,precio);
			                productos.add(p);
   			                    
		                }
		                
		                int k = 0;
		                for (Element el : links) {		// Parseo cada uno de los links de los productos y relleno el vector
		                    String en = (el.attr("href"));               
		                    productos.get(k).setEnlace(en); 
		                }
		                k = 0;

		                	            
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
			                      
			for (Element elem : entradas) {		//Parseo cada uno o de los productos y relleno los vectores
			      String descripcion = elem.getElementsByClass("desc").text();
			      String nombre = elem.getElementsByClass("js-enhanced_product").text();
                  String precio = elem.getElementsByClass("price_val").text();
                  String enlace = elem.getElementsByClass("js-enhanced_product").attr("href");

                  if(descripcion.length()>1 && nombre.length()>1 && precio.length()>1 ){
                	  Producto p = new Producto(nombre,descripcion,precio,enlace);
	                  productos.add(p);
				   }
			}
			                	              			 
		}else{
		  System.out.println("El Status Code no es OK es: " + Scraper.getStatusConnectionCode(urlPage));	                
		}		
		//-----------------------------------------------------------------IMPRIMIR LOS PRODUCTOS--------------------------------------------------------------------		
		out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				for (int i = 0; i < productos.size(); i++) {
					out.println("<div>");
						out.println("<p>");
						out.println(productos.get(i).nombre);
						out.println(productos.get(i).descripcion);
						out.println(productos.get(i).precio);
						out.println(productos.get(i).enlace);
						out.println("</p>");
					out.println("</div>");
				}
			out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
