package com.coraldevil.comicbookreader.contentprovider;

import com.coraldevil.comicbookreader.model.ComicBook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ComicBookContentProvider extends ContentProvider{

	private static final String TAG = "ComicBooksContentProvider";
	public final static String ID = ComicBooksDatabase.ID;
	//private int comicBookNumber;
	public static final String PROVIDER_NAME = "com.coraldevil.commicbookreader.contentprovider";
	public static final Uri COMICBOOKS_URI = Uri.parse("content://" + PROVIDER_NAME + "/comicbooks");

	public static class ComicBooksTable {
			public static final String URL = "url";
			public static final String BOOK_NAME = "bookName";
			public static final String THUMBNAIL_URL = "thumbnailUrl";
			
			public static final String[] COLUMNS = new String[] { 
				ComicBooksDatabase.ID, URL, BOOK_NAME, THUMBNAIL_URL };
			
			public static ContentValues toContentValues(ComicBook comicbook){
				ContentValues cv = new ContentValues();
				cv.put(URL, comicbook.getUrl());
				cv.put(BOOK_NAME, comicbook.getBookName());
				cv.put(THUMBNAIL_URL, comicbook.getThumbnailUrl());
				return cv;
			}
			
			public static Uri createUri(String id){
				return Uri.withAppendedPath(ComicBookContentProvider.COMICBOOKS_URI, id);
			}
	}
	
	public static final int COMICBOOKS = 1;
	public static final int COMICBOOKS_ID = 2;
	
	public static final UriMatcher URI_MATCHER;

    static {
            URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
            URI_MATCHER.addURI(PROVIDER_NAME, "comicbooks", COMICBOOKS);
            URI_MATCHER.addURI(PROVIDER_NAME, "comicbooks/*", COMICBOOKS_ID);
    }
	
	private ComicBooksDatabase database;
	

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDB = database.getWritableDatabase();
        int deletedRows = 0;
        deletedRows = sqlDB.delete(ComicBooksDatabase.TABLE_COMICBOOKS, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null, false);
        return deletedRows;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        id = sqlDB.insert(ComicBooksDatabase.TABLE_COMICBOOKS, null, values);
        getContext().getContentResolver().notifyChange(uri, null, false);
        return Uri.parse(uri + "/" + id);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
        database = new ComicBooksDatabase(context);
        return (context == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		queryBuilder.setTables(ComicBooksDatabase.TABLE_COMICBOOKS);
        queryBuilder.appendWhere(ComicBooksDatabase.ID + "=\"" + uri.getLastPathSegment() + "\"");
		
        Cursor cursor = queryBuilder.query(database.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int updatedComicBooks = 0;
		updatedComicBooks = sqlDB.update(ComicBooksDatabase.TABLE_COMICBOOKS, values, selection,
                new String[] { uri.getLastPathSegment() });
		
		getContext().getContentResolver().notifyChange(uri, null, false);
		return updatedComicBooks;
	}
}
