package exercici5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OrdenarAlfabeticament5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<String> input = new ArrayList<String>();
		Arxiu arxiu1 = new Arxiu();
		Arxiu arxiu2 = new Arxiu();

		System.out.println("Escriu la ruta de la carpeta que vols mostrar l'arbre:");

		String path = sc.nextLine();

		arbreDirectorisToTxt(path, output);
		importText(input, path);

		for (String i : input) {
			arxiu1.text = arxiu1.text + i + "\n";
		}

		serArxiu(arxiu1);
		arxiu2 = deSerArxiu(arxiu2);

		System.out.println(arxiu2);

		sc.close();

	}

	public static Arxiu deSerArxiu(Arxiu arxiu) {
		try {
			FileInputStream fileIn = new FileInputStream("arxiu.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			arxiu = (Arxiu) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Arxiu class not found");
			c.printStackTrace();
		}
		return arxiu;
	}

	public static void serArxiu(Arxiu arxiu) {
		try {
			FileOutputStream fileOut = new FileOutputStream("arxiu.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(arxiu);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
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

	public static void importText(ArrayList<String> input, String path) {
		Scanner sc = new Scanner(System.in);

		System.out.println("escriu el nom del fitxer que vols mostrar dins del directori sel.leccionat previament");
		String fitxerTxt = sc.nextLine();
		sc.close();

		try {
			BufferedReader inputText = new BufferedReader(new FileReader(path + "/" + fitxerTxt));
			String linea;

			while ((linea = inputText.readLine()) != null) {

				input.add(linea.toString());

			}
			inputText.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}

/*
 * Ara el programa ha de serialitzar un Objecte Java a un fitxer .ser i després
 * l’ha de desserialitzar.
 * 
 */
