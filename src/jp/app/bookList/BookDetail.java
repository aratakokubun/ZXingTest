package jp.app.bookList;

import jp.app.twitter.TwitterOuath;
import jp.app.twitter.TwitterUtils;
import jp.app.zxing.R;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookDetail extends LayoutView {
	private static final String SHOW_OUATH = "ツイッターの認証";
	
	private static TextView title;
	private static TextView author;
	private static TextView label;
	private static TextView binding;
	private static TextView price;
	private static TextView note;
	private static ImageView edit;
	private static LinearLayout tweet;
	
	private static BookRow bookRow;
	private static Dialog dialog;

	public BookDetail(BookListActivity bookListActivity) {
		super(bookListActivity);
	}

	@Override
	public void initView(int id) {
		super.initView(id);
		prepared = false;
		
		//add button
		edit = (ImageView) view.findViewById(R.id.button_add);
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BookAddNote.setBookDetailInfo(bookRow);
				activity.requestPrepare(BookListActivity.BOOK_ADD_NOTE);
				activity.changeView(BookListActivity.BOOK_ADD_NOTE);
			}
		});
		
		//tweet button
		tweet = (LinearLayout) view.findViewById(R.id.button_tweet);
		tweet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO 認証をしたらそれをファイルに記録しておき，一度認証が終わっていたらもうしなくていい
				if(!TwitterUtils.hasAccessToken(activity)){
					//showDialogTwitterOuath();
					launchTwitterOuath();
				}
				// twitter投稿画面に遷移
				// TODO
			}
		});
	}
	
	//--------------------------------------------------------------------------------------------
	public void setViewInfo()
	{
		//detail information text view box
		title = (TextView) view.findViewById(R.id.title_value_box);
		title.setText(bookRow.getTitle());
		author = (TextView) view.findViewById(R.id.author_value_box);
		author.setText(bookRow.getAuthor());
		label = (TextView) view.findViewById(R.id.label_value_box);
		label.setText(bookRow.getLabel());
		binding = (TextView) view.findViewById(R.id.binding_value_box);
		binding.setText(bookRow.getBinding());
		price = (TextView) view.findViewById(R.id.price_value_box);
		price.setText(bookRow.getPrice());
		note = (TextView) view.findViewById(R.id.note_value_box);
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
	
	//--------------------------------------------------------------------------------------------
	private void showDialogTwitterOuath()
	{
		showDialog(SHOW_OUATH, activity.getLayoutInflater().inflate(R.layout.twitter_ouath, null));

		final ImageView button_yes = (ImageView) dialog.findViewById(R.id.button_yes);
		button_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//認証が終わったら投稿画面に移動
				
				dialog.dismiss();
			}
		});
		final ImageView button_no = (ImageView) dialog.findViewById(R.id.button_no);
		button_no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog.dismiss();
			}
		});
	}
	
	private void launchTwitterOuath()
	{
        Intent intent = new Intent(activity, TwitterOuath.class);
        activity.startActivity(intent);
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