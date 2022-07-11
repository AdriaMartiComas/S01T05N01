package exercici3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

public class OrdenarAlfabeticament3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> output = new ArrayList<String>();
		System.out.println("Escriu la ruta de la carpeta que vols mostrar l'arbre:");

		String path = sc.nextLine();
		sc.close();

		arbreDirectorisToTxt(path, output);

	}

	public static void arbreDirectorisToTxt(String path, ArrayList<String> output) {
		File file = new File(path);

		mostrarArxiusTxt(path, output);
		String parentFolder = file.getParent();

		if (parentFolder.equals("/")) {
			output.add("\nROOT");

			try {
				BufferedWriter sortida = new BufferedWriter(new FileWriter("arbre_directoris.txt"));

				for (String o : output) {
					sortida.write(o);
				}
				sortida.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			arbreDirectorisToTxt(parentFolder, output);

		}

	}

	public static void mostrarArxiusTxt(String path, ArrayList<String> output) {
		File file = new File(path);
		File folder[] = file.listFiles();
		FileTime fileTime = null;
		int nivell = path.length() - path.replace("/", "").length();
		String espaiEsquerra = new String(new char[nivell * 2]).replace('\0', ' ');
		Path path2 = Paths.get(path);
		String print = "";

		Arrays.sort(folder);
		print = print + "\n" + espaiEsquerra + file.getName().toUpperCase() + "\n";

		for (File f : folder) {
			try {
				fileTime = Files.getLastModifiedTime(path2);

			} catch (IOException e) {
				System.err.println("Cannot get the last modified time - " + e);
			}
			if (f.isFile()) {

				print = print + espaiEsquerra + "File: " + f.getName() + " - " + fileTime + "\n";
			} else if (f.isDirectory()) {
				print = print + espaiEsquerra + "Directory: " + f.getName() + " - " + fileTime + "\n";
			} else {
				print = print + espaiEsquerra + "Not Know:" + f.getName() + " - " + fileTime + "\n";
			}
		}
		output.add(print);

	}

	public static void printFileTime(FileTime fileTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  -  hh:mm:ss");
		System.out.println(dateFormat.format(fileTime.toMillis()));
	}

	public static void importText(ArrayList<String> llista) {

		try {
			BufferedReader inputText = new BufferedReader(
					new FileReader("/users/adriamarticomas/git/S01T05N01/S01T05N01/src/dir.txt"));
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
