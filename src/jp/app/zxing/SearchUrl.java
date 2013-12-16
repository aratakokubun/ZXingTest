package jp.app.zxing;

import android.net.Uri;

public class SearchUrl
{
	public static final int AMAZON = 0;
	public static final int RAKUTEN = 1;
	public static final int BOOKSERVICE = 2;
	public static final int KINOKUNIYA = 3;
	public static final int BOOKSORJP = 4;
	public static final int NOSITE = 5;
	
	private static final String NAME_AMAZON = "amazon";
	private static final String NAME_RAKUTEN = "楽天";
	private static final String NAME_BOOKSERVICE = "Book Service";
	private static final String NAME_KINOKUNIYA = "紀伊国屋";
	private static final String NAME_BOOKSORJP = "Books.or.jp";
	private static final String NAME_NOSITE = "検索サイトが設定されていません.";
	
	private static final String URL_AMAZON_FORE_ISBN = "http://www.amazon.co.jp/s/ref=nb_sb_noss?__mk_ja_JP=%83J%83%5E%83J%83i&url=search-alias%3Dstripbooks&field-keywords=";
	private static final String URL_AMAZON_POST_ISBN = "";
	private static final String URL_RAKUTEN_FORE_ISBN = "http://search.books.rakuten.co.jp/bksearch/nm?sv=30&sitem=ISBN%A1%A7";
	private static final String URL_RAKUTEN_POST_ISBN = "";
	private static final String URL_BOOKSERVICE_FORE_ISBN = "http://www.bookservice.jp/SearchItem?CID=10&LAYER=1&LINK_NO=2&FREE_WORD=";
	private static final String URL_BOOKSERVICE_POST_ISBN = "";
	private static final String URL_KINOKUNIYA_FORE_ISBN = "http://www.kinokuniya.co.jp/disp/CSfGoodsPage_001.jsp?CAT=01&GOODS_STK_NO=";
	private static final String URL_KINOKUNIYA_POST_ISBN = "";
	private static final String URL_BOOKSORJP_FORE_ISBN = "http://www.books.or.jp/ResultList.aspx?scode=&searchtype=1&title=&series=&writer=&ymin=&ymax=&syuppansya=&isbn=";
	private static final String URL_BOOKSORJP_POST_ISBN = "&showcount=20&startindex=0";
	
	public static String getSiteName(int code)
	{
		switch(code) {
		case AMAZON:
			return NAME_AMAZON;
		case RAKUTEN:
			return NAME_RAKUTEN;
		case BOOKSERVICE:
			return NAME_BOOKSERVICE;
		case KINOKUNIYA:
			return NAME_KINOKUNIYA;
		case BOOKSORJP:
			return NAME_BOOKSORJP;
		default:
			return NAME_NOSITE;
		}
	}
	
	public static Uri getUri(int code, String jan)
	{
		String url = "";
		//String isbn = jan.substring(3,12);
		switch(code) {
		case AMAZON:
			url = URL_AMAZON_FORE_ISBN + jan + URL_AMAZON_POST_ISBN;
			break;
		case RAKUTEN:
			url = URL_RAKUTEN_FORE_ISBN + jan + URL_RAKUTEN_POST_ISBN;
			break;
		case BOOKSERVICE:
			url = URL_BOOKSERVICE_FORE_ISBN + jan + URL_BOOKSERVICE_POST_ISBN;
			break;
		case KINOKUNIYA:
			url = URL_KINOKUNIYA_FORE_ISBN + jan + URL_KINOKUNIYA_POST_ISBN;
			break;
		case BOOKSORJP:
			url = URL_BOOKSORJP_FORE_ISBN + jan + URL_BOOKSORJP_POST_ISBN;
			break;
		default:
			return null;
		}
		Uri uri = Uri.parse(url);
		return uri;
	}
}