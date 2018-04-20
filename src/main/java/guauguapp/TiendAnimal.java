package guauguapp;
import java.util.ArrayList;

public class TiendAnimal {
	
		public String url = "https://www.tiendanimal.es/pienso-criadores-c-1_25_351.html";
		public ArrayList<String> nombres = new ArrayList<String>();
		public ArrayList<String> descripciones = new ArrayList<String>();
		public ArrayList<String> precios = new ArrayList<String>();
        public ArrayList<String> enlaces = new ArrayList<String>();
		
		public TiendAnimal(){
			
			
		}
		
		public void imprimirPorPantalla() {
			for(int j = 0 ; j < nombres.size() ; j++) {
				System.out.println(enlaces.get(j));
				System.out.println(nombres.get(j)); 
				System.out.println(descripciones.get(j));  
				System.out.println(precios.get(j)); 
				System.out.println("\n"); 
			 }
		}
		
		public void comprobacionNumero() {
			System.out.println("enlaces: "+ enlaces.size() + " nombres : " + nombres.size() + " descripciones :" + descripciones.size() + " precios : " + precios.size());
		}

	    
}

	
