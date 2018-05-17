package guauguapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mascoteros {
	
		public Map<String, String> mapa = new HashMap<String,String>();
		
		public Mascoteros(){
			//perros
			mapa.put("Alimentacion_Canina","https://www.mascoteros.com/perros/alimentacion/marca_royal-canin?category=903&slug=alimentacion&categoryId=903&page=1");	
			mapa.put("Juguetes_Perros","https://www.mascoteros.com/perros/juguetes");
			
			//gatos
			mapa.put("Alimentacion_Felina","https://www.mascoteros.com/gatos/alimentacion");
			mapa.put("Arenas_Higiernicas","https://www.mascoteros.com/gatos/arenas-higienicas");
			
			//aves
			mapa.put("Alimentacion_Avicola","https://www.mascoteros.com/pajaros/alimentacion");
			mapa.put("Snacks_avicola","https://www.mascoteros.com/pajaros/snacks");
		}

	    
}
