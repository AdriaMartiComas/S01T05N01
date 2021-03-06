package exercici2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class OrdenarAlfabeticament {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriu la ruta de la carpeta que vols mostrar l'arbre:");

		String path = sc.nextLine();
		sc.close();

		arbreDirectoris(path);

		ArrayList<String> llista = new ArrayList<String>();

		importText(llista);
		System.out.println(llista);

		Collections.sort(llista);
		System.out.println(llista);

	}

	public static void mostrarArxius(String path) {
		File file = new File(path);
		File folder[] = file.listFiles();
		FileTime fileTime = null;
		int nivell = path.length() - path.replace("/", "").length();
		String espaiEsquerra = new String(new char[nivell * 2]).replace('\0', ' ');
		Path path2 = Paths.get(path);

		Arrays.sort(folder);
		System.out.println("\n" + espaiEsquerra + file.getName().toUpperCase() + "\n");

		for (File f : folder) {
			try {
				fileTime = Files.getLastModifiedTime(path2);

			} catch (IOException e) {
				System.err.println("Cannot get the last modified time - " + e);
			}
			if (f.isFile()) {
				
				System.out.println(espaiEsquerra + "File: " + f.getName() +" - "+ fileTime);
			} else if (f.isDirectory()) {
				System.out.println(espaiEsquerra + "Directory: " + f.getName()+" - "+ fileTime);
			} else {
				System.out.println(espaiEsquerra + "Not Know:" + f.getName()+" - "+ fileTime);
			}
		}
	}

	public static void printFileTime(FileTime fileTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  -  hh:mm:ss");
		System.out.println(dateFormat.format(fileTime.toMillis()));
	}

	public static void arbreDirectoris(String path) {
		File file = new File(path);

		mostrarArxius(path);
		String parentFolder = file.getParent();
		
		if (parentFolder.equals("/")) {
			System.out.println("\nROOT");
		} else {
			arbreDirectoris(parentFolder);

		}

	}

	public static void importText(ArrayList<String> llista) {

		try {
			BufferedReader inputText = new BufferedReader(new FileReader("/users/adriamarticomas/git/S01T05N01/S01T05N01/src/dir.txt"));
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
 * Afegeix a la classe de l???exercici anterior, la funcionalitat de llistar un
 * arbre de directoris amb el contingut de tots els seus nivells (recursivament)
 * de manera que s'imprimeixin en pantalla en ordre alfab??tic dins de cada
 * nivell, indicant a m??s si ??s un directori (D) o un fitxer (F), i la seva
 * ??ltima data de modificaci??.
 * 
 * 
 */
