package com.coraldevil.comicbooksreader.contentprovider;

import com.coraldevil.comicbooksreader.model.ComicBook;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ComicBookContentProvider extends ContentProvider{

	private static final String TAG = "ComicBooksContentProvider";
	public final static String _ID = ComicBooksDatabase._ID;
	//private int comicBookNumber;
	public static final String PROVIDER_NAME = "com.coraldevil.comicbooksreader.contentprovider";
	public static final Uri COMICBOOKS_URI = Uri.parse("content://" + PROVIDER_NAME + "/comicbooks");
	
	public static final int COMICBOOKS = 1;
	public static final int COMICBOOKS_ID = 2;
	
	public static final UriMatcher uriMatcher;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "comicbooks", COMICBOOKS);
		uriMatcher.addURI(PROVIDER_NAME, "comicbooks/#", COMICBOOKS_ID);
	}
	
	public static final String URL = "url";
	public static final String BOOK_NAME = "bookName";
	public static final String THUMBNAIL_URL = "thumbnailUrl";
	public static final String PAGE_NUMBER = "pageNumber";
	public static final String CURRENT_PAGE = "currentPage";
	
	
	public static ContentValues toContentValues(ComicBook comicbook){
		ContentValues cv = new ContentValues();
		cv.put(URL, comicbook.getUrl());
		cv.put(BOOK_NAME, comicbook.getBookName());
		cv.put(THUMBNAIL_URL, comicbook.getThumbnailUrl());
		cv.put(PAGE_NUMBER, comicbook.getPageNumber());
		cv.put(CURRENT_PAGE, comicbook.getCurrentPage());
		
		return cv;
	}

	private SQLiteDatabase database;
	private ComicBooksDatabase DBHelper;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
        return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = database.insert(ComicBooksDatabase.TABLE_COMICBOOKS, "", values);
		if(rowID > 0)
		{
			Uri mUri = ContentUris.withAppendedId(COMICBOOKS_URI, rowID);
			getContext().getContentResolver().notifyChange(mUri, null);
			return mUri;
			
		}
		throw new SQLException("Failed to insert new row into " + uri);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
		DBHelper = new ComicBooksDatabase(context);
		database = DBHelper.getReadableDatabase();
        return (context == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(ComicBooksDatabase.TABLE_COMICBOOKS);
		sqlBuilder.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
		if(sortOrder == null || sortOrder == ""){
			sortOrder = BOOK_NAME;
		}
		Cursor c = sqlBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}

