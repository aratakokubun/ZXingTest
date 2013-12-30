package jp.app.zxing;

import jp.app.bookList.BookDetail;
import jp.app.bookList.BookListActivity;
import jp.app.bookList.BookRow;
import jp.app.controller.LaunchDialogHandler;
import jp.app.fileio.FileBookData;
import jp.app.httppost.ManageRequestCode;
import jp.app.httppost.NetworkConnection;
import jp.app.httppost.OPERATION_CODE;
import jp.app.httppost.RESPONSE_CODE;
import jp.app.httppost.RESPONSE_GROUP_CODE;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchBookWeb extends ZXingTestActivity
{
	private static boolean D = false;
	private static final String TAG = "SearchBookWeb";
	
	private static final int JAN_ISBN = 978;
	private static final int JAN_STEP2_OLD = 191;
	private static final int JAN_STEP2_NEW = 192;
	private static final int ISBN_10 = 10;
	private static final int ISBN_13 = 13;
	
	private static final String CheckDigits = new String("0123456789X0");
	
	private FileBookData fileBookData;
	
	private String temp_isbn = null;
	private String temp_step2 = null;
	
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
			if(D) Log.e(TAG, "isbn");
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
			if(D) Log.e(TAG, "isbn");
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
	private void decodeJanStep2(String step2)
	{
		temp_step2 = step2;
		searchIsbnWeb();
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void decodeErr(String err)
	{
		Toast.makeText(this, "code " + err + " is not the code of book barcode.", Toast.LENGTH_SHORT).show();
	}
	
	/*---------------------------------------------------------------------------------------*/
	private boolean searchIsbnWeb()
	{
		if(temp_isbn!=null && temp_step2!=null){
			int jsonIndex = fileBookData.searchBookJsonIndex(temp_isbn);
			if(jsonIndex < 0){
				// Check if Network(ex. wifi) is enabled
				if(!NetworkConnection.isWifiConnected(this)){
					Toast.makeText(this, R.string.access_error, Toast.LENGTH_SHORT).show();
				} else {
					int response = ManageRequestCode.throwHttpRequestCode
							(temp_isbn, temp_step2, OPERATION_CODE.ITEM_LOOKUP, RESPONSE_GROUP_CODE.ITEMATTRIBUTES);
					switch(response){
					case RESPONSE_CODE.WIFI_ERROR:
						Toast.makeText(this, R.string.access_error, Toast.LENGTH_SHORT).show();
						break;
					case RESPONSE_CODE.NO_DATA:
						Toast.makeText(this, R.string.get_error, Toast.LENGTH_SHORT).show();
						break;
					case RESPONSE_CODE.DECODE_ERROR:
						Toast.makeText(this, R.string.decode_error, Toast.LENGTH_SHORT).show();
						break;
					case RESPONSE_CODE.CORRECTLY_DECODED:
						fileBookData.putBookArray(ManageRequestCode.getBookRow());
						BookDetail.setBookDetailInfo(ManageRequestCode.getBookRow());
						moveToList(BookListActivity.BOOK_DETAIL);
						break;
					}
				}
				releaseTempCode();
				return true;
			} else {
				//duplicated Isbn code
				BookRow book = fileBookData.recallBookArray(jsonIndex);
				if(book != null){
					BookDetail.setBookDetailInfo(book);
		        	BookListActivity.setLaunchActivity(BookListActivity.BOOK_DETAIL);
					this.finish(); // Finish this activity
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
		temp_step2 = null;
	}
	
	/*---------------------------------------------------------------------------------------*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showFinishAlert();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showFinishAlert(){
		final LaunchDialogHandler ldh = LaunchDialogHandler.getInstance();
		ldh.showDialog(getLayoutInflater().inflate(R.layout.finish_alert, null));
		
		final ImageView yes = (ImageView) ldh.findViewById(R.id.button_yes);
		yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ldh.dismissDialog();
				BookListActivity.requestFinishActivity();
				finish();
			}
		});
		
		final ImageView no = (ImageView) ldh.findViewById(R.id.button_no);
		no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ldh.dismissDialog();
			}
		});
	}
}