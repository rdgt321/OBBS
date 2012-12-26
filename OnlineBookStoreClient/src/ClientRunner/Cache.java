package ClientRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Book.BookPO;
import Book.DirectoryPO;

public class Cache {
	private static File cache = new File("cache/cache.dat");
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	public static ArrayList<DirectoryPO> directorys;
	public static ArrayList<BookPO> rankBooks;
	public static ArrayList<ArrayList<BookPO>> booksInDirectorys;

	@SuppressWarnings("unchecked")
	public static void loadFromCache() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cache));
			if (br.readLine() == null) {
				return;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ois = new ObjectInputStream(new FileInputStream(cache));
			directorys = (ArrayList<DirectoryPO>) ois.readObject();
			booksInDirectorys = (ArrayList<ArrayList<BookPO>>) ois.readObject();
			rankBooks = (ArrayList<BookPO>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void saveToCache() {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(cache));
			oos.writeObject(directorys);
			oos.writeObject(booksInDirectorys);
			oos.writeObject(rankBooks);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clear() {
		directorys = new ArrayList<DirectoryPO>();
		booksInDirectorys = new ArrayList<ArrayList<BookPO>>();
		rankBooks = new ArrayList<BookPO>();
	}
}
