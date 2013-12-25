package jp.app.bookList;

import jp.app.fileio.FileBookData;
import jp.app.zxing.R;
import jp.app.zxing.SearchBookWeb;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class BookListActivity extends Activity
{
	public static final int BOOK_LIST = 0;
	public static final int BOOK_DETAIL = 1;
	public static final int BOOK_ADD_NOTE = 2;
	public static final int VIEW_LENGTH = 3;
	
	private static final LayoutView[] lv = new LayoutView[VIEW_LENGTH];
	private static int viewId;
	private static int launchViewId;

	private static LinearLayout	title;
	private static LinearLayout content;
	
	public FileBookData fileBookData;
	
	private Dialog dialog;
	
	/*---------------------------------------------------------------------------------------*/
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list_main);
		
		fileBookData = new FileBookData(this);
		
		title = (LinearLayout) findViewById(R.id.title);
		content = (LinearLayout) findViewById(R.id.content);
		initAllView();
		
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
				showFinishAlert();
				return true;
			case BOOK_DETAIL:
				changeView(BOOK_LIST);
				return false;
			case BOOK_ADD_NOTE:
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}


	private void showFinishAlert(){
		showDialog(getLayoutInflater().inflate(R.layout.finish_alert, null));
		
		final ImageView yes = (ImageView) dialog.findViewById(R.id.button_yes);
		yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
			}
		});
		
		final ImageView no = (ImageView) dialog.findViewById(R.id.button_no);
		no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
	private void showDialog(View content) {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(content);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.show();
	}

	/*---------------------------------------------------------------------------------------*/
	private void initAllView() {
		// lv[BOOK_LIST] = new BookList(this);
		// lv[BOOK_LIST].initView(R.layout.book_list);
		lv[BOOK_LIST] = new BookListScrollMenu(this);
		lv[BOOK_LIST].initScrollView(R.layout.horz_scroll_with_list_menu, R.layout.book_list_menu, R.layout.book_list);
		lv[BOOK_DETAIL] = new BookDetail(this);
		lv[BOOK_DETAIL].initView(R.layout.book_detail);
		lv[BOOK_ADD_NOTE] = new BookAddNote(this);
		lv[BOOK_ADD_NOTE].initView(R.layout.book_add_note);
	}
	
	/*---------------------------------------------------------------------------------------*/
	public void changeView(int id) {
		if(lv[id].prepareView()) {
			lv[viewId].endView();
			content.removeAllViews();
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
			        ViewGroup.LayoutParams.MATCH_PARENT,
			        ViewGroup.LayoutParams.MATCH_PARENT); 
			content.addView(lv[id].view, params); //TODO ここでScrollLayoutViewではエラーが出るかも
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
	
	/**
	 * キーボードを表示する(一旦非表示にした後，再度表示)
	 */
	public void showKeyboard(View v){
		InputMethodManager manager =
				(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		manager.showSoftInput(v, InputMethodManager.SHOW_FORCED);
	}
	
	/*---------------------------------------------------------------------------------------*/
	public static void setLaunchActivity(int id)
	{
		viewId = id;
	}

	/*---------------------------------------------------------------------------------------*/
	// Move to camera activity
	public void moveToCamera(){
		Intent i = new Intent(this, SearchBookWeb.class);
		startActivity(i);
		this.finish();
	}
}