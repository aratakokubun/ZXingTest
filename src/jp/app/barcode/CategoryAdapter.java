package jp.app.barcode;

import java.util.ArrayList;

import jp.app.zxing.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<CategoryRow> {
	private ArrayList<CategoryRow> items;
	private LayoutInflater inflater;

	public CategoryAdapter(Context context, int textViewResourceId, ArrayList<CategoryRow> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		view = inflater.inflate(R.layout.category_spinner, null);
		final CategoryRow item = items.get(position);
		if (item != null) {
			TextView categoryTitle = (TextView) view.findViewById(R.id.category_title);
			if (categoryTitle != null) {
				categoryTitle.setText(item.getName());
			}
		}
		
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		view = inflater.inflate(R.layout.category_dropdown, null);
		final CategoryRow item = items.get(position);
		if (item != null) {
			TextView categoryTitle = (TextView) view.findViewById(R.id.category_title);
			if (categoryTitle != null) {
				categoryTitle.setText(item.getName());
			}
		}
		
		return view;
	}
	
}