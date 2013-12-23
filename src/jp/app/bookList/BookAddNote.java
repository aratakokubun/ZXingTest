package jp.app.bookList;

import jp.app.zxing.R;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAddNote extends LayoutView {
	/// Debugging
	private static final boolean D = false;
	private static final String TAG = "Add memo text";
	
	private static TextView title;
	private static ImageView finish;
	private static EditText note;
	
	private static BookRow bookRow;
	private static Dialog dialog;

	public BookAddNote(BookListActivity bookListActivity) {
		super(bookListActivity);
	}

	@Override
	public void initView(int id) {
		super.initView(id);
		prepared = false;
		
		// note contents
		note = (EditText) view.findViewById(R.id.note_edit_text);
		
		// finish button
		finish = (ImageView) view.findViewById(R.id.button_finish);
		finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateNote();
				backToBookDetail();
			}
		});
	}
	
	//--------------------------------------------------------------------------------------------
	public void setViewInfo()
	{
		title = (TextView) view.findViewById(R.id.title_value_box);
		title.setText(bookRow.getTitle());
		note.setText(bookRow.getNote());
		// Show software keyboard
		activity.showKeyboard(note);
		prepared = true;
	}
	
	@Override
	public void startView(){}
	
	@Override
	public boolean prepareView()
	{
		if(!prepared) {
			setViewInfo();
		}
		return prepared;
	}
	
	//--------------------------------------------------------------------------------------------
	public static void setBookDetailInfo(BookRow item)
	{
		bookRow = item;
	}
	
	private void updateNote(){
		bookRow.setNote(note.getText().toString());
		activity.fileBookData.editBookData(bookRow);
		BookDetail.setBookDetailInfo(bookRow);
	}
	
	private void backToBookDetail(){
		activity.hideKeyboard(note);
		activity.requestPrepare(BookListActivity.BOOK_DETAIL);
		activity.changeView(BookListActivity.BOOK_DETAIL);
	}
	
	// -------------------------------------------------------------------------------------------------//
	private void showDialog(String title, View content) {
		dialog = new Dialog(activity);
		dialog.setTitle(title);
		dialog.setContentView(content);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.show();
	}

}