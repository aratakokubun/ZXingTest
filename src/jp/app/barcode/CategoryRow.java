package jp.app.barcode;


public class CategoryRow {
	public static final int CONTENTS = 0;
	public static final int MARKET = 1;
	public static final int SELLPUT = 2;
	
	private int id;
	private String name;
	
	public CategoryRow(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryRow(int id, int code){
		this.id = id;
		switch(code){
		case CONTENTS:
			this.name = Contents.getContentName(id);
			break;
		case MARKET:
			this.name = Market.getMarketName(id);
			break;
		case SELLPUT:
			this.name = Sellput.getSellputName(id);
			break;
		}
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}
