package guauguapp;

import java.util.ArrayList;

public class Mascoteros {
	public static final String url = "https://www.mascoteros.com/%s/%s?page=%s";
	public ArrayList<String> animals = new ArrayList<String>();
	public ArrayList<ArrayList<String>> categorias = new ArrayList<ArrayList<String>>();
	
	public Mascoteros(){
		animals = inicializarAnimales();
    	categorias = inicializarCategorias();
		
	}
	
	public ArrayList<String> inicializarAnimales() {
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
    
    public ArrayList<ArrayList<String>> inicializarCategorias() {
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
