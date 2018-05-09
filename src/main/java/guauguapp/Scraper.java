package guauguapp;

import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//SCRAPER PARA LA PAGINA DE MASCOTEROS
public class Scraper {
	
    /**
     * Con esta m�todo compruebo el Status code de la respuesta que recibo al hacer la petici�n
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
            System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }
	
	
    /**
     * Con este m�todo devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitir� parsearlo con los m�todos de la librelia JSoup
     * @param url
     * @return Documento con el HTML
     */
    public static Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
        }
        return doc;
    }
       
}
