package com.coraldevil.comicbooksreader.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.coraldevil.comicbooksreader.model.ComicBook;

public class CbzInfoReader {
	private ComicBook comicbook;
	private ZipFile comicZip;
	private ZipEntry bookEntry;
	
	public CbzInfoReader(File cbzFile) {
		try{
			comicZip = new ZipFile(cbzFile,ZipFile.OPEN_READ);
			Enumeration<?> e = comicZip.entries();
			ZipEntry entry = null;
			
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				if (entry.isDirectory()) {
					continue;
				}
				String name = entry.toString();
				if (name.endsWith(".bmp") || name.endsWith(".BMP")
						|| name.endsWith(".gif") || name.endsWith(".GIF")
						|| name.endsWith(".jpg") || name.endsWith(".JPG")
						|| name.endsWith(".png") || name.endsWith(".PNG")) {
					bookEntry = entry;
					comicbook = new ComicBook(cbzFile.getName(),
							bookEntry.getName(), cbzFile.getPath());
				}
				break;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
