package jp.app.bookList;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookRow {
	private String isbn;
	private String step2;
	private String title;
	private String author;
	private String label;
	private String binding;
	private String price;
	private String note;
	private String market;
	private String sellput;
	private String contents;
	private String first;
	private String latest;
	private String repetition;

	public BookRow() {
		this.isbn = new String();
		this.step2 = new String();
		this.title = new String();
		this.author = new String();
		this.label = new String();
		this.binding = new String();
		this.price = new String();
		this.note = new String();
		this.market = new String();
		this.sellput = new String();
		this.contents = new String();
		this.first = new String();
		this.latest = new String();
		this.repetition = new String();
	}

	public BookRow(String isbn, String step2, String title, String author, String label, String binding, String price, String market, String sellput, String contents) {
		this.isbn = isbn;
		this.step2 = step2;
		this.title = title;
		this.author = author;
		this.label = label;
		this.binding = binding;
		this.price = price;
		this.note = new String();
		this.market = market;
		this.sellput = sellput;
		this.contents = contents;
		registerTime();
	}

	public BookRow(String isbn, String step2, String title, String author, String label, String binding, String price, String note, String market, String sellput, String contents, String first, String latest, String repetition) {
		this.isbn = isbn;
		this.step2 = step2;
		this.title = title;
		this.author = author;
		this.label = label;
		this.binding = binding;
		this.price = price;
		this.note = note;
		this.market = market;
		this.sellput = sellput;
		this.contents = contents;
		this.first = first;
		this.latest = latest;
		this.repetition = repetition;
	}

	@SuppressLint("SimpleDateFormat")
	public void registerTime() {
		if (first == null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy':'MM':'dd'-'hh':'mm':'ss");
			first = sdf.format(cal.getTime());
			latest = first;
			repetition = String.valueOf(1);
		}
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getIsbn(){
		return isbn;
	}
	
	public String getStep2(){
		return step2;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getLabel() {
		return label;
	}

	public String getBinding() {
		return binding;
	}

	public String getPrice() {
		return price;
	}

	public String getNote() {
		return note;
	}
	
	public String getMarket() {
		return market;
	}
	
	public String getSellput() {
		return sellput;
	}
	
	public String getContents() {
		return contents;
	}

	public String getFirst() {
		return first;
	}

	public String getLatest() {
		return latest;
	}

	public String getRepetition() {
		return repetition;
	}
}