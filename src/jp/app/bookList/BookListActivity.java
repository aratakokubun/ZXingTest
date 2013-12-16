package jp.app.bookList;

import jp.app.fileio.FileBookData;
import jp.app.zxing.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

public class BookListActivity extends Activity
{
	public static final int BOOK_LIST = 0;
	public static final int BOOK_DETAIL = 1;
	public static final int VIEW_LENGTH = 2;
	
	private static final LayoutView[] lv = new LayoutView[VIEW_LENGTH];
	private static int viewId = BOOK_LIST;
	private static int launchViewId;

	private static LinearLayout	title;
	private static LinearLayout content;
	
	public FileBookData fileBookData = new FileBookData(this);
	
	/*---------------------------------------------------------------------------------------*/
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list_main);
		
		title = (LinearLayout) findViewById(R.id.title);
		content = (LinearLayout) findViewById(R.id.content);
		initAllView();
		
		this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		try{
			changeView(launchViewId);
		} catch (Exception e) {
			launchViewId = BOOK_LIST;
			changeView(launchViewId);
		}
	}

	@Override
	protected void onDestroy() {
		//TODO
		super.onDestroy();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

	/*---------------------------------------------------------------------------------------*/
	/**
	 * 戻るボタンを押したときの挙動をビューごとに設定しましょう。
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch(viewId) {
			case BOOK_LIST:
				break;
			case BOOK_DETAIL:
				changeView(BOOK_LIST);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/*---------------------------------------------------------------------------------------*/
	private void initAllView() {
		lv[BOOK_LIST] = new BookList(this);
		lv[BOOK_LIST].initView(R.layout.book_list);
		lv[BOOK_DETAIL] = new BookDetail(this);
		lv[BOOK_DETAIL].initView(R.layout.book_detail);
	}
	
	/*---------------------------------------------------------------------------------------*/
	public void changeView(int id) {
		if(lv[id].prepareView()) {
			lv[viewId].endView();
			content.removeAllViews();
			content.addView(lv[id].view);
			content.setVisibility(View.VISIBLE);
			title.setVisibility(View.INVISIBLE);
			lv[id].startView();
			viewId = id;
		}
	}
	
	/*---------------------------------------------------------------------------------------*/
	/**
	 * 既に準備されたビューに、再び準備することを要求します。
	 */
	public void requestPrepare(int id) {
		lv[id].prepared = false;
	}
	
	/*---------------------------------------------------------------------------------------*/
	/**
	 * キーボードを非表示にする
	 */
	public void hideKeyboard(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	/*---------------------------------------------------------------------------------------*/
	public static void setLaunchActivity(int id)
	{
		viewId = id;
	}
}