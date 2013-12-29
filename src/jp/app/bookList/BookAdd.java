package jp.app.bookList;

import java.util.ArrayList;

import jp.app.barcode.Category;
import jp.app.barcode.CategoryAdapter;
import jp.app.barcode.CategoryRow;
import jp.app.barcode.Contents;
import jp.app.barcode.Market;
import jp.app.barcode.Sellput;
import jp.app.zxing.R;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class BookAdd extends LayoutView {
	private EditText title;
	private EditText author;
	private EditText label;
	private EditText binding;
	private EditText price;
	private EditText note;
	private Spinner market;
	private Spinner sellput;
	private Spinner contents;
	private ImageView finish;
	private ImageView cancel;
	
	private static BookRow bookRow = null;
	
	private ArrayList<CategoryRow> categoryList;

	public BookAdd(BookListActivity bookListActivity) {
		super(bookListActivity);
	}

	@Override
	public void initView(int id) {
		super.initView(id);
		prepared = false;
		
		// Button to finish edit
		finish = (ImageView) view.findViewById(R.id.button_finish);
		finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setBookInfo();
				saveBookInfo();
				moveBackToList();
			}
		});
		
		// Button to cancel edit
		cancel = (ImageView) view.findViewById(R.id.button_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveBackToList();
			}
		});
		
		// Make array adapter
		// Market
		categoryList = new ArrayList<CategoryRow>();
		Market.setArrayListAllItems(categoryList); // Add items
		CategoryAdapter categoryAdapter = new CategoryAdapter(activity, R.layout.category_spinner, categoryList);
		categoryAdapter.setDropDownViewResource(R.layout.category_dropdown);
		market = (Spinner) view.findViewById(R.id.spinner_market);
		market.setAdapter(categoryAdapter);
		// Sellput
		categoryList = new ArrayList<CategoryRow>();
		Sellput.setArrayListAllItems(categoryList);
		categoryAdapter = new CategoryAdapter(activity, R.layout.category_spinner, categoryList);
		categoryAdapter.setDropDownViewResource(R.layout.category_dropdown);
		sellput = (Spinner) view.findViewById(R.id.spinner_sellput);
		sellput.setAdapter(categoryAdapter);
		// Contents
		categoryList = new ArrayList<CategoryRow>();
		Contents.setArrayListAllItems(categoryList); // Add items
		categoryAdapter = new CategoryAdapter(activity, R.layout.category_spinner, categoryList);
		categoryAdapter.setDropDownViewResource(R.layout.category_dropdown);
		contents = (Spinner) view.findViewById(R.id.spinner_contents);
		contents.setAdapter(categoryAdapter);
	}
	
	//--------------------------------------------------------------------------------------------
	public void setViewInfo()
	{
		// Detail information text view box
		title = (EditText) view.findViewById(R.id.title_value_box);
		title.setText(bookRow.getTitle());
		author = (EditText) view.findViewById(R.id.author_value_box);
		author.setText(bookRow.getAuthor());
		label = (EditText) view.findViewById(R.id.label_value_box);
		label.setText(bookRow.getLabel());
		binding = (EditText) view.findViewById(R.id.binding_value_box);
		binding.setText(bookRow.getBinding());
		price = (EditText) view.findViewById(R.id.price_value_box);
		price.setText(bookRow.getPrice());
		note = (EditText) view.findViewById(R.id.note_value_box);
		note.setText(bookRow.getNote());
		prepared = true;
	}
	
	private void setBookInfo(){
		boolean duplicated = true;
		// Make random isbn which is not duplicated with present isbns
		int count = 0;
		String isbn = new String();
		while(duplicated){
			int isbnInt = (int)(Math.random()*Math.pow(10, 12));
			isbn = String.valueOf(isbnInt);
			duplicated = activity.fileBookData.searchBookIsbn(isbn);
			if(count > (int)(Math.pow(10, 12))){
				break; // If no-duplicated isbn is not found after 10^12 trials, this application gives up to add it to database.
			}
		}
		
		String strTitle = title.getText().toString();
		String strAuthor = author.getText().toString();
		String strLabel = label.getText().toString();
		String strBinding = binding.getText().toString();
		String strPrice = price.getText().toString();
		String strNote = note.getText().toString();
		String strMarket = ((CategoryRow)market.getSelectedItem()).getName();
		String strSellput = ((CategoryRow)sellput.getSelectedItem()).getName();
		String strContents = ((CategoryRow)contents.getSelectedItem()).getName();
		
		String step2 = Category.makeJanStep2FromBookInfo(strMarket, strSellput, strContents, strPrice);
		
		bookRow = new BookRow(isbn, step2, strTitle, strAuthor, strLabel, strBinding, strPrice, strMarket, strSellput, strContents);
		bookRow.setNote(strNote);
	}
	
	private void saveBookInfo(){
		if(!activity.fileBookData.putBookArray(bookRow)){
			activity.fileBookData.editBookData(bookRow);
		}
	}
	
	private void moveBackToList(){
		bookRow = null;
		activity.requestPrepare(BookListActivity.BOOK_LIST);
		activity.changeView(BookListActivity.BOOK_LIST);
	}
	
	@Override
	public void startView(){
	}
	
	@Override
	public boolean prepareView()
	{
		if(!prepared) {
			if(bookRow == null){
				bookRow = new BookRow();
			}
			setViewInfo();
		}
		return prepared;
	}
	
	//--------------------------------------------------------------------------------------------
	public static void setBookDetailInfo(BookRow item)
	{
		bookRow = item;
	}

}