package jp.app.bookList;

import jp.app.zxing.R;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAddNote extends LayoutView {
	/// Debugging
	private static final boolean D = false;
	private static final String TAG = "Add memo text";
	
	private static TextView title;
	private static ImageView back;
	private static ImageView finish;
	private static EditText note;
	
	private static BookRow bookRow;

	public BookAddNote(BookListActivity bookListActivity) {
		super(bookListActivity);
	}

	@Override
	public void initView(int id) {
		super.initView(id);
		prepared = false;
		
		// Back button
		back = (ImageView) view.findViewById(R.id.button_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 編集破棄の確認ダイアログを出す
				backToBookDetail();
			}
		});
		
		// Note contents
		// FIXME
		// 編集中にキーボードが隠れる問題
		note = (EditText) view.findViewById(R.id.note_edit_text);
		
		// Finish button
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
		if(D) Log.i(TAG, "Modify note");
		bookRow.setNote(note.getText().toString());
		activity.fileBookData.editBookData(bookRow);
		BookDetail.setBookDetailInfo(bookRow);
	}
	
	private void backToBookDetail(){
		activity.hideKeyboard(note);
		activity.requestPrepare(BookListActivity.BOOK_DETAIL);
		activity.changeView(BookListActivity.BOOK_DETAIL);
	}

}