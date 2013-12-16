package jp.app.fileio;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.app.bookList.BookRow;
import jp.app.zxing.SearchUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class FileBookData extends FileIO {
	private static final String FILE_NAME = "book.sys";
	
	/* 読み込んだデータ */
	private static boolean isDataLoaded = false;
	private static int site;
	private static JSONArray bookArray;
	
	/*---------------------------------------------------------------------------------------*/
	public FileBookData(Context context)
	{
		super(context);
		init();
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void init()
	{
		if(!isDataLoaded){
			site = SearchUrl.AMAZON;
			bookArray = new JSONArray();
			loadData();
			//TODO デバッグ用なので消すこと！！
			//deleteBookArray();
		}
	}

	/*---------------------------------------------------------------------------------------*/
	@Override
	public String getFileName()
	{
		return FILE_NAME;
	}

	/*---------------------------------------------------------------------------------------*/
	@Override
	public boolean loadData()
	{
		try {
			JSONObject json = new JSONObject(readFile());
			
			site = json.getInt(J.SITE);
			bookArray = json.getJSONArray(J.BOOK_ARRAY);
			isDataLoaded = true;
		} catch (JSONException e) {
			saveData();
			return false;
		}
		return true;
	}

	/*---------------------------------------------------------------------------------------*/
	@Override
	public boolean saveData()
	{
		try {
			JSONObject json = new JSONObject();
			json.put(J.SITE, site);
			json.put(J.BOOK_ARRAY, bookArray);
			return writeFile(json.toString());
		} catch (JSONException e) {
			return false;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	public boolean searchBookIsbn(String isbn)
	{
		int bookLength = bookArray.length();
		try {
			for (int i = 0; i < bookLength; i++) {
				JSONObject next = bookArray.getJSONObject(i);
				if(isbn.equals(next.getString(J.ISBN))){
					return true;
				}
			}
			return false;
		} catch (JSONException e) {
			return false;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	public int searchBookJsonIndex(String isbn)
	{
		int bookLength = bookArray.length();
		try {
			for (int i = 0; i < bookLength; i++) {
				JSONObject next = bookArray.getJSONObject(i);
				if(isbn.equals(next.getString(J.ISBN))){
					return i;
				}
			}
			return -1;
		} catch (JSONException e) {
			return -1;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	@Deprecated
	public boolean putBookArray(String isbn, String step_2)
	{
		try {
			if(searchBookIsbn(isbn)){
				return false;
			} else {
				JSONObject book = new JSONObject();
				book.put(J.ISBN, isbn);
				book.put(J.STEP_2, step_2);
				bookArray.put(bookArray.length(), book);
				saveData();
				return true;
			}
		} catch (JSONException e) {
			return false;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	public boolean putBookArray(String isbn, String step_2, BookRow bookRow)
	{
		try {
			if(searchBookIsbn(isbn)){
				return false;
			} else {
				JSONObject book = new JSONObject();
				book.put(J.ISBN, isbn);
				book.put(J.STEP_2, step_2);
				book.put(J.TITLE, bookRow.getTitle());
				book.put(J.AUTHOR, bookRow.getAuthor());
				book.put(J.LABEL, bookRow.getLabel());
				book.put(J.BINDING, bookRow.getBinding());
				book.put(J.PRICE, bookRow.getPrice());
				book.put(J.NOTE, bookRow.getNote());
				book.put(J.FIRST, bookRow.getFirst());
				book.put(J.LATEST, bookRow.getLatest());
				book.put(J.REPETITION, bookRow.getRepetition());
				bookArray.put(bookArray.length(), book);
				saveData();
				return true;
			}
		} catch (JSONException e) {
			return false;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	public BookRow recallBookArray(int index)
	{
		try {
			JSONObject json = bookArray.getJSONObject(index);
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy':'MM':'dd'-'hh':'mm':'ss");
			json.put(J.LATEST, sdf.format(cal.getTime()));
			json.put(J.REPETITION, json.getInt(J.REPETITION)+1);
			bookArray.put(index, json);
			return new BookRow(json.getString(J.TITLE), json.getString(J.AUTHOR), json.getString(J.LABEL), json.getString(J.BINDING), json.getString(J.PRICE)
					, json.getString(J.NOTE), json.getString(J.FIRST), json.getString(J.LATEST), json.getString(J.REPETITION));
		} catch (JSONException e) {
			return null;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	public boolean deleteBookArray()
	{
		bookArray = new JSONArray();
		saveData();
		return true;
	}
	
	/*---------------------------------------------------------------------------------------*/
	public JSONArray getBookJsonArray()
	{
		return isDataLoaded ? bookArray : null;
	}
}