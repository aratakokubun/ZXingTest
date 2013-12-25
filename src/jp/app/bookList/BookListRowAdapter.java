package jp.app.bookList;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import jp.app.zxing.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookListRowAdapter extends ArrayAdapter<BookRow> {
	private ArrayList<BookRow> items;
	private LayoutInflater inflater;
	
	public static final int NORMAL = 0;
	public static final int DELETE = 1;
	private int mode;
	private BookListActivity activity;

	public BookListRowAdapter(BookListActivity activity, int textViewResourceId, ArrayList<BookRow> items, int mode) {
		super(activity.getApplicationContext(), textViewResourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mode = mode;
		this.activity = activity;
	}
	
	public void changeMode(){
		switch(this.mode){
		case NORMAL:
			this.mode = DELETE;
			break;
		case DELETE:
			this.mode = NORMAL;
			break;
		}
	}
	
	public void changeMode(int mode){
		this.mode = mode;
	}
	
	public int getMode(){
		return mode;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		switch(mode){
		case NORMAL:
		{
			view = inflater.inflate(R.layout.book_list_row, null);
			final BookRow item = items.get(position);
			if (item != null) {
				TextView timeText 	= (TextView) view.findViewById(R.id.register_time);
				TextView titleText 	= (TextView) view.findViewById(R.id.book_title);
				if (timeText != null) {
					timeText.setText(item.getLatest());
				}
				if (titleText != null) {
					titleText.setText(item.getTitle());
				}
			}
			break;
		}
		case DELETE:
		{
			view = inflater.inflate(R.layout.book_list_row_delete, null);
			final BookRow item = items.get(position);
			if (item != null) {
				TextView timeText 	= (TextView) view.findViewById(R.id.register_time);
				TextView titleText 	= (TextView) view.findViewById(R.id.book_title);
				TextView deleteButton = (TextView) view.findViewById(R.id.button_delete);
				if (timeText != null) {
					timeText.setText(item.getLatest());
				}
				if (titleText != null) {
					titleText.setText(item.getTitle());
				}
				if (deleteButton != null) {
					deleteButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							deleteItem(item);
						}
					});
				}
			}
			break;
		}
		}
		
		return view;
	}
	
	private void deleteItem(BookRow bookRow){
		try {
			items.remove(bookRow);
			activity.fileBookData.deleteBookArray(bookRow.getIsbn());
			changeMode();
			notifyDataSetChanged();
		} catch(NoSuchElementException e){
			e.printStackTrace();
			changeMode();
		} catch(Exception e){
			e.printStackTrace();
			changeMode();
		}
	}
}