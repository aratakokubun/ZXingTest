package jp.app.bookList;

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

public class BookList extends LayoutView {
	private static ImageView add;
	private static ListView book;
	private static ArrayList<BookRow> list;
	private static BookListRowAdapter adapter;

	public BookList(BookListActivity bookListActivity) {
		super(bookListActivity);
	}

	@Override
	public void initView(int id) {
		super.initView(id);
		prepared = false;
		
		//add button
		add = (ImageView) view.findViewById(R.id.button_add);
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO
				//追加
			}
		});
		
		//list view
		list = new ArrayList<BookRow>();
		book = (ListView)view.findViewById(R.id.list_book);
		adapter = new BookListRowAdapter(activity, R.layout.book_list_row, list);
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
			prepared = true;
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		adapter.notifyDataSetChanged();
	}
	
}