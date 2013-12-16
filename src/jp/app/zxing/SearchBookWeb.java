package jp.app.zxing;

import jp.app.barcode.Contents;
import jp.app.barcode.Market;
import jp.app.barcode.Sellput;
import jp.app.bookList.BookDetail;
import jp.app.bookList.BookListActivity;
import jp.app.bookList.BookRow;
import jp.app.fileio.FileBookData;
import jp.app.httppost.ManageRequestCode;
import jp.app.httppost.OPERATION_CODE;
import jp.app.httppost.RESPONSE_GROUP_CODE;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SearchBookWeb extends ZXingTestActivity
{
	private static final String TAG = "SearchBookWeb";
	
	private static final int JAN_ISBN = 978;
	private static final int JAN_STEP2_OLD = 191;
	private static final int JAN_STEP2_NEW = 192;
	private static final int ISBN_10 = 10;
	private static final int ISBN_13 = 13;
	
	private static final String CheckDigits = new String("0123456789X0");
	
	private FileBookData fileBookData;
	
	private String temp_isbn = null;
	private String temp_step_2 = null;
	
	/*---------------------------------------------------------------------------------------*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileBookData = new FileBookData(this);
    }
    
	/*---------------------------------------------------------------------------------------*/
	@Override
	public void searchResultWeb(String result)
	{
		int len = result.length();//コードの長さ
		int flag = Integer.parseInt(result.substring(0, 3));//上3ケタのフラグコード
		switch(flag) {
		case JAN_ISBN:
			Log.e(TAG, "isbn");
			switch(len) {
			case ISBN_10:
				decodeISBN_10(result);
				break;
			case ISBN_13:
				decodeISBN_13(result);
				break;
			}
			break;
			
		case JAN_STEP2_OLD:
		case JAN_STEP2_NEW:
			Log.e(TAG, "step2");
			decodeJanStep2(result);
			break;
			
		default:
			decodeErr(result);
			break;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void decodeISBN_10(String jan)
	{
		temp_isbn = jan;
		searchIsbnWeb();
	}
	
	private void decodeISBN_13(String jan)
	{
		temp_isbn = convertIsbn13to10(jan);
		searchIsbnWeb();
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void decodeJanStep2(String jan)
	{
		String category = jan.substring(3, 7);
		String cost = jan.substring(7, 12);
		temp_step_2 = jan;
		searchIsbnWeb();
	}
	
	private void getCategory(String category)
	{
		int marketCode = Integer.getInteger((String)category.subSequence(0, 1));
		int sellputCode = Integer.getInteger((String)category.subSequence(1, 2));
		int contentCode = Integer.getInteger((String)category.subSequence(2, 4));
		
		String marketName = Market.getMarketName(marketCode);
		String sellputName = Sellput.getSellputName(sellputCode);
		String contentName = Contents.getContentName(contentCode);
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void decodeErr(String err)
	{
		Toast.makeText(this, "code " + err + " is not the code of book barcode.", Toast.LENGTH_SHORT).show();
	}
	
	/*---------------------------------------------------------------------------------------*/
	private boolean searchIsbnWeb()
	{
		if(temp_isbn!=null && temp_step_2!=null){
			int jsonIndex = fileBookData.searchBookJsonIndex(temp_isbn);
			if(jsonIndex < 0){
				if(ManageRequestCode.throwHttpRequestCode
						(temp_isbn, OPERATION_CODE.ITEM_LOOKUP, RESPONSE_GROUP_CODE.ITEMATTRIBUTES)){
					//全ての本の詳細情報のデータをfileBookDataに渡し、BookListActivityを起動
					fileBookData.putBookArray(temp_isbn, temp_step_2, ManageRequestCode.bookRow);
					BookDetail.setBookDetailInfo(ManageRequestCode.bookRow);
		        	BookListActivity.setLaunchActivity(BookListActivity.BOOK_DETAIL);
					Intent i = new Intent(this, BookListActivity.class);
					startActivity(i);
				} else {
					//Toast.makeText(this, "Sorry. Can't get the return value. Please check the reception and retry later.", Toast.LENGTH_SHORT).show();
					Toast.makeText(this, R.string.access_error, Toast.LENGTH_SHORT).show();
				}
				releaseTempCode();
				return true;
			} else {
				//duplicated Isbn code
				BookRow book = fileBookData.recallBookArray(jsonIndex);
				if(book != null){
					BookDetail.setBookDetailInfo(book);
		        	BookListActivity.setLaunchActivity(BookListActivity.BOOK_DETAIL);
					Intent i = new Intent(this, BookListActivity.class);
					startActivity(i);
				} else {
					//Toast.makeText(this, "Sorry. Can't get the return value. Please check the reception and retry later.", Toast.LENGTH_SHORT).show();
					Toast.makeText(this, R.string.access_error, Toast.LENGTH_SHORT).show();
				}
				releaseTempCode();
				return false;
			}
		} else {
			return false;
		}
		/*
		Intent i = new Intent(Intent.ACTION_VIEW, SearchUrl.getUri(SearchUrl.AMAZON, temp_isbn));
		startActivity(i);//ブラウザの起動
		*/
	}
	
	/*---------------------------------------------------------------------------------------*/
	private static String convertIsbn13to10(String isbn13)
	{
		String s9 = isbn13.substring(3, 12);
	    int n = 0;
	    for (int i = 0; i < 9; i++) {
	    	int v = Integer.valueOf(String.valueOf(s9.charAt(i)));
	    	n += (10 - i) * v; 
	    }
	    n = 11 - (n % 11);
	    return s9 + CheckDigits.charAt(n); 
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void releaseTempCode()
	{
		temp_isbn = null;
		temp_step_2 = null;
	}
	
	/*---------------------------------------------------------------------------------------*/
	/**
	 * add menu to shift list or detail of the book
	 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.set_engin:
            return true;
        case R.id.archive:
        	BookListActivity.setLaunchActivity(BookListActivity.BOOK_LIST);
			Intent i = new Intent(this, BookListActivity.class);
			startActivity(i);
            return true;
        }
        return false;
    }

}