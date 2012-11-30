package com.coraldevil.comicbooksreader.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ComicBooksDatabase extends SQLiteOpenHelper{

	private static final String TAG = ComicBooksDatabase.class.getName();
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "ComicBooksDatabase";
	public static final String TABLE_COMICBOOKS = "ComicBooks";
	public static final String _ID = "_id";
	
	private static final String CREATE_COMICBOOKS = "create table "
			+ TABLE_COMICBOOKS + " (" + _ID
			+ " integer primary key autoincrement, "
			+ ComicBookContentProvider.URL + " TEXT, "
			+ ComicBookContentProvider.BOOK_NAME
			+ " TEXT NOT NULL, "
			+ ComicBookContentProvider.THUMBNAIL_URL + "TEXT, "
			+ ComicBookContentProvider.PAGE_NUMBER + "integer, "
			+ ComicBookContentProvider.CURRENT_PAGE + " integer);";
	
	public ComicBooksDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_COMICBOOKS);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(TAG, "Upgrading database. Existing contents will be lost. ["
				+ oldVersion + "]->[" + newVersion + "]");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMICBOOKS);
		onCreate(db);
	}

}
