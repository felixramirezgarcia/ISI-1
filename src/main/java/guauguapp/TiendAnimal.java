package guauguapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TiendAnimal {
	
		public Map<String, String> mapa = new HashMap<String,String>();
		
		public TiendAnimal(){
			//perros
			mapa.put("Alimentacion_Canina","https://www.tiendanimal.es/pienso-breedup-c-1_25_523.html");
			mapa.put("Juguetes_Perros","https://www.tiendanimal.es/juguetes-kong-para-perros-c-1_59_878.html");
			
			//gatos
			mapa.put("Alimentacion_Felina","https://www.tiendanimal.es/pienso-royal-canin-gatos-c-2_100_104.html");
			mapa.put("Arenas_Higiernicas","https://www.tiendanimal.es/arena-aglomerante-para-gatos-c-2_397_796.html");
			
			//aves
			mapa.put("Alimentacion_Avicola","https://www.tiendanimal.es/semillas-para-pajaros-c-3_145.html");
			mapa.put("Snacks_avicola","https://www.tiendanimal.es/snacks-para-periquitos-c-3_729_166.html");
		}

	    
}

	
