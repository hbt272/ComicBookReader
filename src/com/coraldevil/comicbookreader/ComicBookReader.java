package com.coraldevil.comicbookreader;

import com.craldevil.comicbookreader.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ComicBookReader extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_book_reader);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_comic_book_reader, menu);
		return true;
	}

}
