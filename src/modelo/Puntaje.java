package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Puntaje implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ArrayLists
	private ArrayList<Integer> puntajes = new ArrayList<Integer>();
	
	//Constructs
	@SuppressWarnings("unchecked")
	public Puntaje(){
		int i;
		int j;
		int aux;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("puntos.txt"));
			Object obj = ois.readObject();
			
			if(obj != null) {
				
				if(obj instanceof ArrayList<?>) {
					
					if(((ArrayList<?>) obj).get(0) instanceof Integer) {
						
						this.puntajes = (ArrayList<Integer>)obj;
						
						for(i=0; i<this.puntajes.size(); i++) {
							for(j=0; j<this.puntajes.size(); j++) {
								if(this.puntajes.get(i) > this.puntajes.get(j)) {
									aux = this.puntajes.get(i);
									this.puntajes.set(i, this.puntajes.get(j));
									this.puntajes.set(j, aux);
								}
							}
						}
					}
				}
			}
			ois.close();
		} 
		catch (FileNotFoundException fnfe) {} 
		catch (IOException ioe) {}
		catch (ClassNotFoundException cnfe) {}
	}
	
	//Methods
	public void addPuntos(int puntos) {
		File file = new File("puntos.txt");
		
		puntajes.add(puntos);
		
		if(file.exists()) {
			file.delete();
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("puntos.txt"));
			oos.writeObject(this.puntajes);
			oos.close();
		} 
		catch (FileNotFoundException fnfe) {} 
		catch (IOException ioe) {}
	}

	//Getters
	public ArrayList<Integer> getPuntajes() {
		return puntajes;
	}
}
