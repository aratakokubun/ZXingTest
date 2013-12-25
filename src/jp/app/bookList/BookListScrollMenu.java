package jp.app.bookList;

import grimbo.android.demo.slidingmenu.ViewUtils;

import java.util.ArrayList;

import jp.app.fileio.J;
import jp.app.zxing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class BookListScrollMenu extends ScrollLayoutView {
	private static final boolean IS_MENU_LEFT = true;
	
	private ImageView camera;
	private ImageView setting;
	private ListView book;
	private ArrayList<BookRow> list;
	private BookListRowAdapter adapter;
	
	public BookListScrollMenu(BookListActivity bookListActivity) {
		super(bookListActivity);
	}
	
	@Override
	public void initScrollView(int scrollViewId, int menuId, int appId){
		super.initScrollView(scrollViewId, menuId, appId);
		prepared = false;
		
		// Camera button
		camera  = (ImageView) app.findViewById(R.id.button_camera);
		camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Finish this activity and move to camera mode
				activity.moveToCamera();
			}
		});

		// Setting button (scroll menu)
		setting = (ImageView) app.findViewById(R.id.button_setting);
		setting.setOnClickListener(new ClickListenerForScrolling(scrollView, menu));

		// Book List
		list = new ArrayList<BookRow>();
		book = (ListView) app.findViewById(R.id.list_book);
		adapter = new BookListRowAdapter(activity, R.layout.book_list_row, list, BookListRowAdapter.NORMAL);
		book.setAdapter(adapter);
		book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView parentList = (ListView) parent;
				BookRow item = (BookRow) parentList.getItemAtPosition(position);
				BookDetail.setBookDetailInfo(item);
				activity.requestPrepare(BookListActivity.BOOK_DETAIL);
				activity.changeView(BookListActivity.BOOK_DETAIL);
			}
		});
		
		// TODO
		// Menu List
		ListView listView = (ListView) menu.findViewById(R.id.list);
		ViewUtils.initListView(activity, listView, "Menu", 30, android.R.layout.simple_list_item_1);
		
		final View[] children = new View[] {app, menu};
		
		// Scroll to app (view[0]) when layout finished.
		int scrollToViewIdx = 0;
		scrollView.initViews(children, scrollToViewIdx, new SizeCallbackForMenu(setting));
	}
	
	@Override
	public void startView(){}
	
	@Override
	public boolean prepareView(){
		if(!prepared) {
			list.clear();
			setBookList();
		}
		return prepared;
	}
	
	//--------------------------------------------------------------------------------------------
	private void setBookList(){
		int index = 0;
		try{
			JSONArray ja = activity.fileBookData.getBookJsonArray();
			while(true){
				if(ja.opt(index) != null){
					JSONObject next = ja.getJSONObject(index);
					list.add(new BookRow(
							next.getString(J.ISBN),
							next.getString(J.STEP_2),
							next.getString(J.TITLE),
							next.getString(J.AUTHOR),
							next.getString(J.LABEL),
							next.getString(J.BINDING),
							next.getString(J.PRICE),
							next.getString(J.NOTE),
							next.getString(J.FIRST),
							next.getString(J.LATEST),
							next.getString(J.REPETITION))
					);
					index++;
				} else {
					break;
				}
			}
			book.setScrollingCacheEnabled(false); 
			prepared = true;
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		adapter.notifyDataSetChanged();
	}
	
}