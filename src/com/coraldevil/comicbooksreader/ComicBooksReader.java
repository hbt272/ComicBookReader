package com.coraldevil.comicbooksreader;

import java.io.File;
import java.util.List;

import com.coraldevil.comicbooksreader.contentprovider.ComicBookContentProvider;
import com.coraldevil.comicbooksreader.contentprovider.ComicBooksDatabase;
import com.coraldevil.comicbooksreader.model.ComicBook;
import com.coraldevil.comicbooksreader.util.CollectDataBase;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class ComicBooksReader extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setAllBooksToDatabase();
		
		Cursor cursor = getContentResolver().query(ComicBookContentProvider.COMICBOOKS_URI, null, null, null, null);
		String[] from = { ComicBookContentProvider.BOOK_NAME, ComicBookContentProvider.PAGE_NUMBER };
	    int[] to = { R.id.txt_bookname, R.id.txt_bookpage };
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_rows, cursor, from, to);
		getListView().setAdapter(adapter);
	}
	
	public void setAllBooksToDatabase(){
		List<ComicBook> files = CollectDataBase.getListFiles(new File("/sdcard/ComicBooks"));
		
		for (ComicBook comicbook : files){
			System.out.println(comicbook.toString());
			ContentValues values = ComicBookContentProvider.toContentValues(comicbook);
			Uri uriInsert = getContentResolver().insert(ComicBookContentProvider.COMICBOOKS_URI, values);
			Log.d(getClass().getSimpleName(),uriInsert.toString());
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_comic_books_reader, menu);
		return true;
	}

}
