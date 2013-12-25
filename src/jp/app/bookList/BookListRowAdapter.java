package jp.app.bookList;

import java.util.ArrayList;

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

	public BookListRowAdapter(Context context, int textViewResourceId, ArrayList<BookRow> items, int mode) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mode = mode;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		switch(mode){
		case NORMAL:
		{
			if (view == null) {
				view = inflater.inflate(R.layout.book_list_row, null);
			}
			BookRow item = items.get(position);
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
			if (view == null) {
				view = inflater.inflate(R.layout.book_list_row_delete, null);
			}
			BookRow item = items.get(position);
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
				}
			}
			break;
		}
		}
		
		return view;
	}
}