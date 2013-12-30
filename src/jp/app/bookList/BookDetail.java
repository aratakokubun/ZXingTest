package jp.app.bookList;

import java.util.NoSuchElementException;

import jp.app.twitter.TwitterOuath;
import jp.app.twitter.TwitterUtils;
import jp.app.zxing.R;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetail extends LayoutView {
	private TextView title;
	private TextView author;
	private TextView label;
	private TextView binding;
	private TextView price;
	private TextView note;
	private TextView market;
	private TextView sellput;
	private TextView contents;
	private ImageView back;
	private ImageView edit;
	private LinearLayout tweet;
	private LinearLayout delete;
	
	private static BookRow bookRow;

	public BookDetail(BookListActivity bookListActivity) {
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
				activity.requestPrepare(BookListActivity.BOOK_LIST);
				activity.changeView(BookListActivity.BOOK_LIST);
			}
		});
		
		// Edit button
		edit = (ImageView) view.findViewById(R.id.button_add);
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BookAddNote.setBookDetailInfo(bookRow);
				activity.requestPrepare(BookListActivity.BOOK_ADD_NOTE);
				activity.changeView(BookListActivity.BOOK_ADD_NOTE);
			}
		});
		
		// Tweet button
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
		
		// Delete button
		delete = (LinearLayout) view.findViewById(R.id.button_delete);
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteItem();
			}
		});
	}
	
	//--------------------------------------------------------------------------------------------
	public void setViewInfo()
	{
		// Detail information text view box
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
		market = (TextView) view.findViewById(R.id.market_value_box);
		market.setText(bookRow.getMarket());
		sellput = (TextView) view.findViewById(R.id.sellput_value_box);
		sellput.setText(bookRow.getSellput());
		contents = (TextView) view.findViewById(R.id.contents_value_box);
		contents.setText(bookRow.getContents());
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
	
	private void launchTwitterOuath()
	{
        Intent intent = new Intent(activity, TwitterOuath.class);
        activity.startActivity(intent);
	}

	private void deleteItem(){
		try {
			activity.fileBookData.deleteBookArray(bookRow.getIsbn());
			activity.requestPrepare(BookListActivity.BOOK_LIST);
			activity.changeView(BookListActivity.BOOK_LIST);
			Toast.makeText(activity, R.string.delete_complete, Toast.LENGTH_SHORT).show();
		} catch(NoSuchElementException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}