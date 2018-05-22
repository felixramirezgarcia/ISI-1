package guauguapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





@WebServlet("/busqueda")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        
    }
    
    public void escrapear(HttpServletRequest request, HttpServletResponse response , String accion)  throws ServletException, IOException, ParserConfigurationException, InterruptedException, SAXException {
    	
    	ArrayList<Producto> resultado = new ArrayList<Producto>();
    	ArrayList<Producto> aux = new ArrayList<Producto>();
    	ArrayList<Producto> aux1 = new ArrayList<Producto>();
    	
    	//-----------------------------------------------------------------SCRAPING A MASCOTEROS--------------------------------------------------------------------
    	Mascoteros ob = new Mascoteros();			   		        		
    	String urlMasco = String.format(ob.mapa.get(accion));	
    	//System.out.println("Comprobando entradas de: "+urlMasco);
    						
    	if (Scraper.getStatusConnectionCode(urlMasco) == 200) {		// Compruebo si me da un 200 al hacer la petición
    									                
    		Document document = Scraper.getHtmlDocument(urlMasco);		// Obtengo el HTML de la web en un objeto Document
    		Elements entradas = document.select("div.product-card-content");		// Busco todos los productos de esa pagina que estan dentro del div con esa clase
    		Elements links = document.select("a.add_click");		//.not("div.col-md-offset-2.col-md-4.col-xs-12");
    		Elements imagenes = document.getElementsByTag("img");
    			                		                		                
    		for (Element elem : entradas) {		// Parseo cada uno de los productos y relleno los vectores
    			 String nombre = elem.getElementsByClass("list_name_prod_box").text();
    			 String descripcion = elem.getElementsByClass("product_short_description").text();
    			 String precio = elem.getElementsByClass("product-pricing-now").text();
    			 
    			 nombre = Producto.limpiarAcentos(nombre);
    			 descripcion = Producto.limpiarAcentos(descripcion);
    			 descripcion = descripcion.toLowerCase();
    			                    
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
    		    	//System.out.println("La url de la imagen es : " + imagen);
    		    }		  		    					
    		}
    			                
    		int g = 0;
    		for (int j = 0; j < aux.size()-2; j=j+2) {
    			Producto p = new Producto(aux.get(j).nombre,aux.get(j).descripcion,aux.get(j+1).precio,aux.get(j+1).enlace,auxiliar.get(g));
    			p.setTienda(0);
    			resultado.add(p);
    			g++;
			}
 		                	            
    	}else{
    		System.out.println("El Status Code no es OK es: "+Scraper.getStatusConnectionCode(urlMasco));
    	}
    		            		
    	//-----------------------------------------------------------------SCRAPING A TIENDANIMAL--------------------------------------------------------------------
    	TiendAnimal ti = new TiendAnimal();  			
    	String urlTienda = String.format(ti.mapa.get(accion));		
    	//System.out.println("Comprobando entradas de: "+urlTienda);
    						
    	if (Scraper.getStatusConnectionCode(urlTienda) == 200) {		// Compruebo si me da un 200 al hacer la petición
    							
    		Document document = Scraper.getHtmlDocument(urlTienda);		// Obtengo el HTML de la web en un objeto Document
    		Elements entradas = document.select("div.wrap");		// Busco todas las historias de esa pagina que estan dentro del div con esa clase
    		Elements imagenes = document.getElementsByTag("img"); 
    				
    		for (Element elem : entradas) {		//Parseo cada uno o de los productos y relleno los vectores
    			String descripcion = elem.getElementsByClass("desc").text();
    			String nombre = elem.getElementsByClass("js-enhanced_product").text();
    	        String precio = elem.getElementsByClass("price_val").text();
    	        String enlace = elem.getElementsByClass("js-enhanced_product").attr("href");
    	        
    	        nombre = Producto.limpiarAcentos(nombre);
   			 	descripcion = Producto.limpiarAcentos(descripcion);
   			 	descripcion = descripcion.toLowerCase();
    	                  
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
        			aux1.get(i).setTienda(1);
        			i++;
    			}
    		}
    		
    		i = 0;
    				
    		for (int z = 0; z < aux1.size(); z++) {    						
			     resultado.add(aux1.get(z));		               
			}
    				
    				
    	}else{
    		System.out.println("El Status Code no es OK es: " + Scraper.getStatusConnectionCode(urlTienda));	                
    	}
    	
    	//-----------------------------------------------------------------SCRAPING A AMAZON--------------------------------------------------------------------
    	ArrayList<String> preciosAmazon=new ArrayList<String>();
		ArrayList<String> NombresAmazon= new ArrayList<String>();
		ArrayList<String> urlsAmazon= new ArrayList<String>();
		ArrayList<String> imagenesAmazon= new ArrayList<String>();
		ArrayList<String> descripcionAmazon= new ArrayList<String>();
 
    	Map<String, String> params = new HashMap<String, String>();
    	SignedRequestsHelper helper;
    	
    	//--------------------------------PEGAR AQUI------------------------------------------------------------------------------------------------------------
    	

        try {
            helper = new SignedRequestsHelper(ENDPOINT,ACCESS_KEY_ID,SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl = null;
        
        Amazon ama = new Amazon();
        String busca = ama.mapa.get(accion);	

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", "AKIAIX7MDP45KFBLMFVQ");
        params.put("AssociateTag",AMAZON_ASSOCIATE_TAG);
        params.put("SearchIndex", "All");
        params.put("ResponseGroup", "Images,ItemAttributes");
        params.put("Keywords", busca);

        requestUrl = helper.sign(params);
    	
		//System.out.println("desde el servlet :" + requestUrl);
        
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = db.parse(requestUrl);
		
		NodeList nameOfModelname= doc.getElementsByTagName("Title"); 
		for(int i=0;i<nameOfModelname.getLength();i++){			
			org.w3c.dom.Element el = (org.w3c.dom.Element)nameOfModelname.item(i);
			NombresAmazon.add(el.getFirstChild().getNodeValue());			
		}
		
		NodeList nameOfModeldescripcion= doc.getElementsByTagName("ItemAttributes");
		for(int i=0; i<nameOfModeldescripcion.getLength(); i++) {   
	        Node nodo = nameOfModeldescripcion.item(i);
	        NodeList datosNodo = nodo.getChildNodes();
	        for(int j=0; j<datosNodo.getLength(); j++) {
	            Node dato = datosNodo.item(j);
	            if(dato.getNodeType()==Node.ELEMENT_NODE) {
	                if(dato.getNodeName()=="Feature") {
	                	Node datoContenido = dato.getFirstChild();
	                	descripcionAmazon.add(datoContenido.getNodeValue());
	                }
	            }
	        }
		}
		
		NodeList nameOfModelprecio= doc.getElementsByTagName("ItemAttributes");
	    for(int i=0; i<nameOfModelprecio.getLength(); i++) {   
	        Node nodo0 = nameOfModelprecio.item(i);
	        NodeList datosNodo0 = nodo0.getChildNodes();
	        for(int j=0; j<datosNodo0.getLength(); j++) {
	        	Node nodo1 = datosNodo0.item(j);
		        NodeList datosNodo1 = nodo1.getChildNodes();
		        for (int l = 0; l < datosNodo1.getLength(); l++) {
		        	Node dato = datosNodo1.item(l);
		            if(dato.getNodeType()==Node.ELEMENT_NODE) {   
		                if(dato.getNodeName()=="FormattedPrice") {
		                	Node datoContenido = dato.getFirstChild();
		                	preciosAmazon.add(datoContenido.getNodeValue());
		                }
		               
		            }
				}
	        }	        
	    }
	    
	    NodeList nameOfModelenlace= doc.getElementsByTagName("DetailPageURL"); 
		for(int i=0;i<nameOfModelenlace.getLength();i++){			
			org.w3c.dom.Element el = (org.w3c.dom.Element)nameOfModelenlace.item(i);
			urlsAmazon.add(el.getFirstChild().getNodeValue());			
		}
		
		NodeList nameOfModelimagen= doc.getElementsByTagName("MediumImage");
		for(int i=0; i<nameOfModelimagen.getLength(); i++) {   
	        Node nodo = nameOfModelimagen.item(i);
	        NodeList datosNodo = nodo.getChildNodes();
	        for(int j=0; j<datosNodo.getLength(); j++) {
	            Node dato = datosNodo.item(j);
	            if(dato.getNodeType()==Node.ELEMENT_NODE) {
	                if(dato.getNodeName()=="URL") {
	                	Node datoContenido = dato.getFirstChild();
	                	imagenesAmazon.add(datoContenido.getNodeValue());
	                }
	            }
	        }
		}
		
		
		for (int k = 0; k < NombresAmazon.size(); k++) {
			Producto p = new Producto(Producto.limpiarAcentos(NombresAmazon.get(k)),Producto.limpiarAcentos(descripcionAmazon.get(k)),preciosAmazon.get(k),urlsAmazon.get(k));
			p.setImagen(imagenesAmazon.get(k));
			p.setTienda(2);
			resultado.add(p);			
		}
		//-------------------------------------------------------BARAJAR--------------------------------------------------------------------
		Collections.shuffle(resultado);
		//-------------------------------------------------------ENVIAR LA RESPUESTA A LA PAGINA JSP--------------------------------------------------------------------
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
		
		
		try {
			this.escrapear(request,response,accion);
		} catch (ParserConfigurationException | InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("algo va mal");
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("algo va mal");
			e.printStackTrace();
		}
	
	}
	
	private String getElementValue (org.w3c.dom.Document doc, String tag){
		NodeList nodelist = doc.getElementsByTagName(tag);
		return ( (nodelist.getLength()>0) ?
		nodelist.item(0).getTextContent() : null );
	}

	

	


}
