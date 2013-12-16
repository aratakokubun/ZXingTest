package jp.app.bookList;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookRow {
	private String title = "";
	private String author = "";
	private String label = "";
	private String binding = "";
	private String price = "";
	private String note = "";
	private String first = null;
	private String latest = null;
	private String repetition = "";

	public BookRow() {

	}

	public BookRow(String title, String author, String label, String binding, String price) {
		this.title = title;
		this.author = author;
		this.label = label;
		this.binding = binding;
		this.price = price;
		registerTime();
	}

	public BookRow(String title, String author, String label, String binding, String price, String note, String first, String latest, String repetition) {
		this.title = title;
		this.author = author;
		this.label = label;
		this.binding = binding;
		this.price = price;
		this.note = note;
		this.first = first;
		this.latest = latest;
		this.repetition = repetition;
	}

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