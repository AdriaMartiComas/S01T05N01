package exercici1;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class OrdenarAlfabeticament {

	public static void main(String[] args) {
		ArrayList<String> llista = new ArrayList<String>();
		
		importText(llista);
		System.out.println(llista);
				
		Collections.sort(llista);
		System.out.println(llista);
	

	}
	

	
	public static void importText(ArrayList<String> llista) {

		try {
			BufferedReader inputText = new BufferedReader(new FileReader("dir.txt"));
			String linea;

			while ((linea = inputText.readLine()) != null) {

				llista.add(linea.toString());

			}	
			inputText.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

/*
 * Crea una classe que llisti alfabèticament el contingut d'un directori rebut
 * per paràmetre.
 */
