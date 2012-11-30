package com.coraldevil.comicbooksreader.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.coraldevil.comicbooksreader.model.ComicBook;

public class CollectDataBase {
	
	public static List<ComicBook> getListFiles(File parentDir) {
		// TODO Auto-generated method stub
		ArrayList<ComicBook> books = new ArrayList<ComicBook>();
	
	    File[] files = parentDir.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            books.addAll(getListFiles(file));
	        } else {
	            if(file.getName().endsWith(".cbz")){
	            	ComicBook comic = new ComicBook();
	            	comic.setUrl(file.getPath());
	            	
	            	comic.setBookName(file.getName().substring(0, file.getName().length() - 4));
	            	
	            	books.add(comic);
	            }
	        }
	    }
	    return books;
	}
}
