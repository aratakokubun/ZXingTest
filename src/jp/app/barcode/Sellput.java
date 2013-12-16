package jp.app.barcode;

public class Sellput
{
	public static final int CODE_SEPARATE = 0;
	public static final int CODE_POCKETEDITIONA = 1;
	public static final int CODE_NEWBOOK = 2;
	public static final int CODE_COMPLETEWORK = 3;
	public static final int CODE_MAGAZINE = 4;
	public static final int CODE_DICTIONARY = 5;
	public static final int CODE_PICTORIAL = 6;
	public static final int CODE_PICTURE = 7;
	public static final int CODE_MAGNETIC = 8;
	public static final int CODE_COMMIC = 9;

	public static final String NAME_SEPARATE = "単行本";
	public static final String NAME_POCKETEDITIONA = "文庫";
	public static final String NAME_NEWBOOK = "新書";
	public static final String NAME_COMPLETEWORK = "全集";
	public static final String NAME_MAGAZINE = "ムック";
	public static final String NAME_DICTIONARY = "辞典";
	public static final String NAME_PICTORIAL = "図鑑";
	public static final String NAME_PICTURE = "絵本";
	public static final String NAME_MAGNETIC = "磁性媒体など";
	public static final String NAME_COMMIC = "コミック";
	public static final String NAME_NONE = "該当なし";
	
	public static String getSellputName(int code) {
		switch (code) {
		case CODE_SEPARATE:
			return NAME_SEPARATE;
		case CODE_POCKETEDITIONA:
			return NAME_POCKETEDITIONA;
		case CODE_NEWBOOK:
			return NAME_NEWBOOK;
		case CODE_COMPLETEWORK:
			return NAME_COMPLETEWORK;
		case CODE_MAGAZINE:
			return NAME_MAGAZINE;
		case CODE_DICTIONARY:
			return NAME_DICTIONARY;
		case CODE_PICTORIAL:
			return NAME_PICTORIAL;
		case CODE_PICTURE:
			return NAME_PICTURE;
		case CODE_MAGNETIC:
			return NAME_MAGNETIC;
		case CODE_COMMIC:
			return NAME_COMMIC;
		default:
			return NAME_NONE;
		}
	}
}