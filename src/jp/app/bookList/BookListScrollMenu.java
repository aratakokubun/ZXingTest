package jp.app.bookList;

import java.util.ArrayList;

import jp.app.fileio.J;
import jp.app.zxing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BookListScrollMenu extends ScrollLayoutView {
	private static final int MENU_IDX = 1;
	
	private TextView header;
	private ImageView camera;
	private ImageView setting;
	private ListView bookListView;
	private ListView settingsListView;
	private ArrayList<BookRow> bookList;
	private ArrayList<Settings> settingsList;
	private BookListRowAdapter adapter;
	private SettingsAdapter settingsAdapter;
	
	public BookListScrollMenu(BookListActivity bookListActivity) {
		super(bookListActivity);
	}
	
	@Override
	public void initScrollView(int scrollViewId, int menuId, int appId){
		super.initScrollView(scrollViewId, menuId, appId);
		prepared = false;
		
		// Header
		header = (TextView) app.findViewById(R.id.header);
		header.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				liftAdapterDeleteMode();
			}
		});
		
		// Camera button
		camera  = (ImageView) app.findViewById(R.id.button_camera);
		camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!liftAdapterDeleteMode()){
					// Finish this activity and move to camera mode
					activity.moveToCamera();
				}
			}
		});

		// Setting button (scroll menu)
		setting = (ImageView) app.findViewById(R.id.button_setting);
		setting.setOnClickListener(new ClickListenerForScrolling(scrollView, menu, MENU_IDX));

		// Book List
		bookList = new ArrayList<BookRow>();
		bookListView = (ListView) app.findViewById(R.id.list_book);
		adapter = new BookListRowAdapter(activity, R.layout.book_list_row, bookList, BookListRowAdapter.NORMAL);
		bookListView.setAdapter(adapter);
		bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(!liftAdapterDeleteMode()){
					ListView parentList = (ListView) parent;
					BookRow item = (BookRow) parentList.getItemAtPosition(position);
					BookDetail.setBookDetailInfo(item);
					activity.requestPrepare(BookListActivity.BOOK_DETAIL);
					activity.changeView(BookListActivity.BOOK_DETAIL);
				}
			}
		});
		
		// Menu List
		settingsList = new ArrayList<Settings>();
		settingsListView = (ListView) menu.findViewById(R.id.list_settings);
		settingsAdapter = new SettingsAdapter(activity, R.layout.book_list_menu_row, settingsList);
		settingsListView.setAdapter(settingsAdapter);
		settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView parentList = (ListView) parent;
				Settings item = (Settings) parentList.getItemAtPosition(position);
				onSettingsClicked(item.getMode());
			}
		});
		
		final View[] children = new View[] {app, menu};
		
		// Scroll to app (view[0]) when layout finished.
		int scrollToViewIdx = 0;
		scrollView.initViews(children, scrollToViewIdx, new SizeCallbackForMenu(setting, MENU_IDX));
	}
	
	@Override
	public void startView(){
		mHandler.sendEmptyMessageDelayed(REQUEST_INIT_SCROLL, REQUEST_SLEEP);
	}
	
	private static final int REQUEST_INIT_SCROLL = 0;
	private static final long REQUEST_SLEEP = 200;
	@SuppressLint("HandlerLeak")
	final Handler mHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg){
			if(msg.what == REQUEST_INIT_SCROLL){
				// startView の後にscrollするメソッドが呼ばれており，その場所が不明のため，ここで時間遅れで再度scroll位置を戻している
				scrollView.scrollTo(0, 0);
			}
		}
	};
	
	@Override
	public boolean prepareView(){
		if(!prepared) {
			setBookList();
			setMenuList();
		}
		return prepared;
	}
	
	//--------------------------------------------------------------------------------------------
	private boolean liftAdapterDeleteMode(){
		// If in delete mode, lift it
		int mode = adapter.getMode();
		if(mode == BookListRowAdapter.DELETE){
			adapter.changeMode();
			setBookList();
			return true;
		} else {
			return false;
		}
	}
	
	//--------------------------------------------------------------------------------------------
	private void setBookList(){
		bookList.clear();
		
		int index = 0;
		try{
			JSONArray ja = activity.fileBookData.getBookJsonArray();
			while(true){
				if(ja.opt(index) != null){
					JSONObject next = ja.getJSONObject(index);
					bookList.add(new BookRow(
							next.getString(J.ISBN),
							next.getString(J.STEP_2),
							next.getString(J.TITLE),
							next.getString(J.AUTHOR),
							next.getString(J.LABEL),
							next.getString(J.BINDING),
							next.getString(J.PRICE),
							next.getString(J.NOTE),
							next.getString(J.MARKET),
							next.getString(J.SELLPUT),
							next.getString(J.CONTENTS),
							next.getString(J.FIRST),
							next.getString(J.LATEST),
							next.getString(J.REPETITION))
					);
					index++;
				} else {
					break;
				}
			}
			bookListView.setScrollingCacheEnabled(false); 
			prepared = true;
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		adapter.notifyDataSetChanged();
	}
	
	private void setMenuList(){
		settingsList.clear();
		
		settingsList.add(new Settings(Settings.LAUNCH_CAMERA, 	activity.getString(R.string.settings_launch_camera), 	R.drawable.icon_camera));
		settingsList.add(new Settings(Settings.MANUAL_ADD, 	  	activity.getString(R.string.settings_manual_add), 		R.drawable.icon_note));
		settingsList.add(new Settings(Settings.DELETE, 			activity.getString(R.string.settings_delete), 			R.drawable.icon_trash));
		settingsList.add(new Settings(Settings.SEARCH, 			activity.getString(R.string.settings_search), 			R.drawable.icon_search));
		settingsListView.setScrollingCacheEnabled(false);
		settingsAdapter.notifyDataSetChanged();
	}
	
	//--------------------------------------------------------------------------------------------
	private void onSettingsClicked(int id){
		switch(id){
		case Settings.LAUNCH_CAMERA:
			activity.moveToCamera();
			break;
		case Settings.MANUAL_ADD:
			activity.requestPrepare(BookListActivity.BOOK_ADD);
			activity.changeView(BookListActivity.BOOK_ADD);
			break;
		case Settings.DELETE:
			adapter.changeMode();
			setBookList();
			setting.performClick();
			break;
		case Settings.SEARCH:
			// TODO
			break;
		}
	}
}