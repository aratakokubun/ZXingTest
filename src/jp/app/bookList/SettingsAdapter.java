package jp.app.bookList;

import java.util.ArrayList;

import jp.app.zxing.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsAdapter extends ArrayAdapter<Settings> {
	private ArrayList<Settings> items;
	private LayoutInflater inflater;

	public SettingsAdapter(Context context, int textViewResourceId, ArrayList<Settings> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.book_list_menu_row, null);
		}
		Settings item = items.get(position);
		if (item != null) {
			ImageView iconImage = (ImageView) view.findViewById(R.id.icon);
			if (iconImage != null) {
				iconImage.setImageResource(item.getImageId());
			}
			TextView mainText = (TextView) view.findViewById(R.id.name);
			if (mainText != null) {
				mainText.setText(item.getName());
			}
		}
		return view;
	}
}