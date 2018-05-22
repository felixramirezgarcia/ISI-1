package guauguapp;

import java.util.HashMap;
import java.util.Map;

public class Amazon {
	public Map<String, String> mapa = new HashMap<String,String>();
	
	public Amazon(){
		//perros
		//perros
		mapa.put("Alimentacion_Canina","Royal Canin Perro");	
		mapa.put("Juguetes_Perros","perros");
		
		//gatos
		mapa.put("Alimentacion_Felina","gato comida");
		mapa.put("Arenas_Higiernicas","Arenas gatos");
		
		//aves
		mapa.put("Alimentacion_Avicola","alpiste");
		mapa.put("Snacks_avicola","comida pajaros");
	}
}
