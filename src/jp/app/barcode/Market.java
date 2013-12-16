package jp.app.barcode;

public class Market
{
	public static final int CODE_GENERAL = 0;
	public static final int CODE_EDUCATION = 1;
	public static final int CODE_PRACTICAL = 2;
	public static final int CODE_SPECIALITY = 3;
	public static final int CODE_NONE = 4;
	public static final int CODE_LADY = 5;
	public static final int CODE_STUDYMIDDLE = 6;
	public static final int CODE_STUDYHIGH = 7;
	public static final int CODE_CHILD = 8;
	public static final int CODE_MAGAZINE = 9;

	public static final String NAME_GENERAL = "一般";
	public static final String NAME_EDUCATION = "教養";
	public static final String NAME_PRACTICAL = "実用";
	public static final String NAME_SPECIALITY = "専門";
	public static final String NAME_NONE = "該当なし";
	public static final String NAME_LADY = "婦人";
	public static final String NAME_STUDYMIDDLE = "参考書(小中)";
	public static final String NAME_STUDYHIGH = "参考書(高校)";
	public static final String NAME_CHILD = "児童";
	public static final String NAME_MAGAZINE = "雑誌扱い";

	public static String getMarketName(int code) {
		switch (code) {
		case CODE_GENERAL:
			return NAME_GENERAL;
		case CODE_EDUCATION:
			return NAME_EDUCATION;
		case CODE_PRACTICAL:
			return NAME_PRACTICAL;
		case CODE_SPECIALITY:
			return NAME_SPECIALITY;
		case CODE_LADY:
			return NAME_LADY;
		case CODE_STUDYMIDDLE:
			return NAME_STUDYMIDDLE;
		case CODE_STUDYHIGH:
			return NAME_STUDYHIGH;
		case CODE_CHILD:
			return NAME_CHILD;
		case CODE_MAGAZINE:
			return NAME_MAGAZINE;
		default:
			return NAME_NONE;
		}
	}
}