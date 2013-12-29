package jp.app.barcode;

import java.util.ArrayList;


public class Contents
{
	public static final int CODE_GENERALDESCRIPTION = 0;
	public static final int CODE_ENCYCLOPEDIA = 1;
	public static final int CODE_ALMANACMAGAZINE = 2;
	public static final int CODE_INFORMATIONSCIENCE = 4;
	public static final int CODE_PHILOSOPHY = 10;
	public static final int CODE_PSYCOLOGY = 11;
	public static final int CODE_ETHICS = 12;
	public static final int CODE_RELIGION = 14;
	public static final int CODE_BUDDHISM = 15;
	public static final int CODE_CHRISTIANITY = 16;
	public static final int CODE_TOTAL_HISTORY = 20;
	public static final int CODE_JAPANESEHISTORY = 21;
	public static final int CODE_FOREIGNHISTORY = 22;
	public static final int CODE_BIOGRAPHY = 23;
	public static final int CODE_GEOGRAPHY = 25;
	public static final int CODE_TRAVEL = 26;
	public static final int CODE_TOTAL_SOCIALSCIENCE = 30;
	public static final int CODE_POLITICS = 31;
	public static final int CODE_LAW = 32;
	public static final int CODE_ECONOMY = 33;
	public static final int CODE_MANAGEMENT = 34;
	public static final int CODE_SOCIETY = 36;
	public static final int CODE_EDUCATION = 37;
	public static final int CODE_CUSTOM = 39;
	public static final int CODE_TOTAL_NATURALSCIENCE = 40;
	public static final int CODE_PHYSICS = 41;
	public static final int CODE_CHEMISTRY = 43;
	public static final int CODE_ASTRONOMY = 44;
	public static final int CODE_BIOLOGY = 45;
	public static final int CODE_MEDICINE = 47;
	public static final int CODE_ENGINEERING = 50;
	public static final int CODE_ENGINEERING_WORK = 51;
	public static final int CODE_CONSTRUCTION = 52;
	public static final int CODE_MACHINE = 53;
	public static final int CODE_ELECTRICITY = 54;
	public static final int CODE_ELECTRONIC_COMMUNICATIONS = 55;
	public static final int CODE_MARINEAFFAIRS = 56;
	public static final int CODE_MINING = 57;
	public static final int CODE_OTHER_INDUSTRY = 58;
	public static final int CODE_TOTAL_INDUSTRY = 60;
	public static final int CODE_AGRICULTURE = 61;
	public static final int CODE_FISHERIES = 62;
	public static final int CODE_COMMERCE = 63;
	public static final int CODE_TRAFFIC = 65;
	public static final int CODE_TOTAL_ART = 70;
	public static final int CODE_PICTURE_AND_SCULPTURE = 71;
	public static final int CODE_PHOTOGRAPH_AND_CRAFT = 72;
	public static final int CODE_MUSIC_AND_DANCE = 73;
	public static final int CODE_THEATRE_AND_MOVIE = 74;
	public static final int CODE_GYMNASTICS_AND_SPORT = 75;
	public static final int CODE_AMUSEMENT = 76;
	public static final int CODE_HOUSEKEEP = 77;
	public static final int CODE_COMMIC_AND_GRAPHICNOVEL = 79;
	public static final int CODE_TOTAL_LANGUAGE = 80;
	public static final int CODE_JAPANESE = 81;
	public static final int CODE_ENGLISH = 82;
	public static final int CODE_GERMAN = 84;
	public static final int CODE_FRENCH = 85;
	public static final int CODE_LANGUAGES = 87;
	public static final int CODE_TOTAL_LITERATURE = 90;
	public static final int CODE_TOTAL_JAPANESELITERATURE = 91;
	public static final int CODE_JAPANESEPOEM = 92;
	public static final int CODE_JAPANESENOVEL_AND_TALE = 93;
	public static final int CODE_CRITICISM_AND_ESSAY = 95;
	public static final int CODE_FOREIGNNOVEL = 97;
	public static final int CODE_FOREIGNLITERATURE = 98;
	public static final int CODE_NONE = 99;

	public static final String NAME_GENERALDESCRIPTION = "総記";
	public static final String NAME_ENCYCLOPEDIA = "百科事典";
	public static final String NAME_ALMANACMAGAZINE = "年鑑雑誌";
	public static final String NAME_INFORMATIONSCIENCE = "情報科学";
	public static final String NAME_PHILOSOPHY = "哲学";
	public static final String NAME_PSYCOLOGY = "心理学";
	public static final String NAME_ETHICS = "倫理";
	public static final String NAME_RELIGION = "宗教";
	public static final String NAME_BUDDHISM = "仏教";
	public static final String NAME_CHRISTIANITY = "キリスト教";
	public static final String NAME_TOTAL_HISTORY = "歴史総記";
	public static final String NAME_JAPANESEHISTORY = "日本歴史";
	public static final String NAME_FOREIGNHISTORY = "外国歴史";
	public static final String NAME_BIOGRAPHY = "伝記";
	public static final String NAME_GEOGRAPHY = "地理";
	public static final String NAME_TRAVEL = "旅行";
	public static final String NAME_TOTAL_SOCIALSCIENCE = "社会科学総記";
	public static final String NAME_POLITICS = "政治";
	public static final String NAME_LAW = "法律";
	public static final String NAME_ECONOMY = "経済財政統計";
	public static final String NAME_MANAGEMENT = "経営";
	public static final String NAME_SOCIETY = "社会";
	public static final String NAME_EDUCATION = "教育";
	public static final String NAME_CUSTOM = "民族風習";
	public static final String NAME_TOTAL_NATURALSCIENCE = "自然科学総記";
	public static final String NAME_PHYSICS = "物理学";
	public static final String NAME_CHEMISTRY = "化学";
	public static final String NAME_ASTRONOMY = "天文地学";
	public static final String NAME_BIOLOGY = "生物学";
	public static final String NAME_MEDICINE = "医学私学薬学";
	public static final String NAME_ENGINEERING = "工学";
	public static final String NAME_ENGINEERING_WORK = "土木";
	public static final String NAME_CONSTRUCTION = "建築";
	public static final String NAME_MACHINE = "機械";
	public static final String NAME_ELECTRICITY = "電気";
	public static final String NAME_ELECTRONIC_COMMUNICATIONS = "電子通信";
	public static final String NAME_MARINEAFFAIRS = "海事";
	public static final String NAME_MINING = "採鉱";
	public static final String NAME_OTHER_INDUSTRY = "その他の工業";
	public static final String NAME_TOTAL_INDUSTRY = "産業総記";
	public static final String NAME_AGRICULTURE = "農林業";
	public static final String NAME_FISHERIES = "水産業";
	public static final String NAME_COMMERCE = "商業";
	public static final String NAME_TRAFFIC = "交通通信";
	public static final String NAME_TOTAL_ART = "芸術総記";
	public static final String NAME_PICTURE_AND_SCULPTURE = "絵画彫刻";
	public static final String NAME_PHOTOGRAPH_AND_CRAFT = "写真工芸";
	public static final String NAME_MUSIC_AND_DANCE = "音楽舞踊";
	public static final String NAME_THEATRE_AND_MOVIE = "演劇映画";
	public static final String NAME_GYMNASTICS_AND_SPORT = "体育スポーツ";
	public static final String NAME_AMUSEMENT = "諸芸娯楽";
	public static final String NAME_HOUSEKEEP = "家事";
	public static final String NAME_COMMIC_AND_GRAPHICNOVEL = "コミックス劇画";
	public static final String NAME_TOTAL_LANGUAGE = "語学総記";
	public static final String NAME_JAPANESE = "日本語";
	public static final String NAME_ENGLISH = "英米語";
	public static final String NAME_GERMAN = "ドイツ語";
	public static final String NAME_FRENCH = "フランス語";
	public static final String NAME_LANGUAGES = "各国語";
	public static final String NAME_TOTAL_LITERATURE = "文学総記";
	public static final String NAME_TOTAL_JAPANESELITERATURE = "日本文学総記";
	public static final String NAME_JAPANESEPOEM = "日本文学詩歌";
	public static final String NAME_JAPANESENOVEL_AND_TALE = "日本小説物語";
	public static final String NAME_CRITICISM_AND_ESSAY = "評論随筆";
	public static final String NAME_FOREIGNNOVEL = "外国文学小説";
	public static final String NAME_FOREIGNLITERATURE = "外国文学";
	public static final String NAME_NONE = "該当なし";
	
	public static String getContentName(int code) {
		switch(code) {
		case CODE_GENERALDESCRIPTION:
			return NAME_GENERALDESCRIPTION;
		case CODE_ENCYCLOPEDIA:
			return NAME_ENCYCLOPEDIA;
		case CODE_ALMANACMAGAZINE:
			return NAME_ALMANACMAGAZINE;
		case CODE_INFORMATIONSCIENCE:
			return NAME_INFORMATIONSCIENCE;
		case CODE_PHILOSOPHY:
			return NAME_PHILOSOPHY;
		case CODE_PSYCOLOGY:
			return NAME_PSYCOLOGY;
		case CODE_ETHICS:
			return NAME_ETHICS;
		case CODE_RELIGION:
			return NAME_RELIGION;
		case CODE_BUDDHISM:
			return NAME_BUDDHISM;
		case CODE_CHRISTIANITY:
			return NAME_CHRISTIANITY;
		case CODE_TOTAL_HISTORY:
			return NAME_TOTAL_HISTORY;
		case CODE_JAPANESEHISTORY:
			return NAME_JAPANESEHISTORY;
		case CODE_FOREIGNHISTORY:
			return NAME_FOREIGNHISTORY;
		case CODE_BIOGRAPHY:
			return NAME_BIOGRAPHY;
		case CODE_GEOGRAPHY:
			return NAME_GEOGRAPHY;
		case CODE_TRAVEL:
			return NAME_TRAVEL;
		case CODE_TOTAL_SOCIALSCIENCE:
			return NAME_TOTAL_SOCIALSCIENCE;
		case CODE_POLITICS:
			return NAME_POLITICS;
		case CODE_LAW:
			return NAME_LAW;
		case CODE_ECONOMY:
			return NAME_ECONOMY;
		case CODE_MANAGEMENT:
			return NAME_MANAGEMENT;
		case CODE_SOCIETY:
			return NAME_SOCIETY;
		case CODE_EDUCATION:
			return NAME_EDUCATION;
		case CODE_CUSTOM:
			return NAME_CUSTOM;
		case CODE_TOTAL_NATURALSCIENCE:
			return NAME_TOTAL_NATURALSCIENCE;
		case CODE_PHYSICS:
			return NAME_PHYSICS;
		case CODE_CHEMISTRY:
			return NAME_CHEMISTRY;
		case CODE_ASTRONOMY:
			return NAME_ASTRONOMY;
		case CODE_BIOLOGY:
			return NAME_BIOGRAPHY;
		case CODE_MEDICINE:
			return NAME_MEDICINE;
		case CODE_ENGINEERING:
			return NAME_ENGINEERING;
		case CODE_ENGINEERING_WORK:
			return NAME_ENGINEERING_WORK;
		case CODE_CONSTRUCTION:
			return NAME_CONSTRUCTION;
		case CODE_MACHINE:
			return NAME_MACHINE;
		case CODE_ELECTRICITY:
			return NAME_ELECTRICITY;
		case CODE_ELECTRONIC_COMMUNICATIONS:
			return NAME_ELECTRONIC_COMMUNICATIONS;
		case CODE_MARINEAFFAIRS:
			return NAME_MARINEAFFAIRS;
		case CODE_MINING:
			return NAME_MINING;
		case CODE_OTHER_INDUSTRY:
			return NAME_OTHER_INDUSTRY;
		case CODE_TOTAL_INDUSTRY:
			return NAME_TOTAL_INDUSTRY;
		case CODE_AGRICULTURE:
			return NAME_AGRICULTURE;
		case CODE_FISHERIES:
			return NAME_FISHERIES;
		case CODE_COMMERCE:
			return NAME_COMMERCE;
		case CODE_TRAFFIC:
			return NAME_TRAFFIC;
		case CODE_TOTAL_ART:
			return NAME_TOTAL_ART;
		case CODE_PICTURE_AND_SCULPTURE:
			return NAME_PICTURE_AND_SCULPTURE;
		case CODE_PHOTOGRAPH_AND_CRAFT:
			return NAME_PHOTOGRAPH_AND_CRAFT;
		case CODE_MUSIC_AND_DANCE:
			return NAME_MUSIC_AND_DANCE;
		case CODE_THEATRE_AND_MOVIE:
			return NAME_THEATRE_AND_MOVIE;
		case CODE_GYMNASTICS_AND_SPORT:
			return NAME_GYMNASTICS_AND_SPORT;
		case CODE_AMUSEMENT:
			return NAME_AMUSEMENT;
		case CODE_HOUSEKEEP:
			return NAME_HOUSEKEEP;
		case CODE_COMMIC_AND_GRAPHICNOVEL:
			return NAME_COMMIC_AND_GRAPHICNOVEL;
		case CODE_TOTAL_LANGUAGE:
			return NAME_TOTAL_LANGUAGE;
		case CODE_JAPANESE:
			return NAME_JAPANESE;
		case CODE_ENGLISH:
			return NAME_ENGLISH;
		case CODE_GERMAN:
			return NAME_GERMAN;
		case CODE_FRENCH:
			return NAME_FRENCH;
		case CODE_LANGUAGES:
			return NAME_LANGUAGES;
		case CODE_TOTAL_LITERATURE:
			return NAME_TOTAL_LITERATURE;
		case CODE_TOTAL_JAPANESELITERATURE:
			return NAME_TOTAL_JAPANESELITERATURE;
		case CODE_JAPANESEPOEM:
			return NAME_JAPANESEPOEM;
		case CODE_JAPANESENOVEL_AND_TALE:
			return NAME_JAPANESENOVEL_AND_TALE;
		case CODE_CRITICISM_AND_ESSAY:
			return NAME_CRITICISM_AND_ESSAY;
		case CODE_FOREIGNNOVEL:
			return NAME_FOREIGNNOVEL;
		case CODE_FOREIGNLITERATURE:
			return NAME_FOREIGNLITERATURE;
		default:
			return NAME_NONE;
		}
	}
	
	public static void setArrayListAllStrings(ArrayList<String> list){
		list.add(NAME_GENERALDESCRIPTION);
		list.add(NAME_ENCYCLOPEDIA);
		list.add(NAME_ALMANACMAGAZINE);
		list.add(NAME_INFORMATIONSCIENCE);
		list.add(NAME_PHILOSOPHY);
		list.add(NAME_PSYCOLOGY);
		list.add(NAME_ETHICS);
		list.add(NAME_RELIGION);
		list.add(NAME_BUDDHISM);
		list.add(NAME_CHRISTIANITY);
		list.add(NAME_TOTAL_HISTORY);
		list.add(NAME_JAPANESEHISTORY);
		list.add(NAME_FOREIGNHISTORY);
		list.add(NAME_BIOGRAPHY);
		list.add(NAME_GEOGRAPHY);
		list.add(NAME_TRAVEL);
		list.add(NAME_TOTAL_SOCIALSCIENCE);
		list.add(NAME_POLITICS);
		list.add(NAME_LAW);
		list.add(NAME_ECONOMY);
		list.add(NAME_MANAGEMENT);
		list.add(NAME_SOCIETY);
		list.add(NAME_EDUCATION);
		list.add(NAME_CUSTOM);
		list.add(NAME_TOTAL_NATURALSCIENCE);
		list.add(NAME_PHYSICS);
		list.add(NAME_CHEMISTRY);
		list.add(NAME_ASTRONOMY);
		list.add(NAME_BIOLOGY);
		list.add(NAME_MEDICINE);
		list.add(NAME_ENGINEERING);
		list.add(NAME_ENGINEERING_WORK);
		list.add(NAME_CONSTRUCTION);
		list.add(NAME_MACHINE);
		list.add(NAME_ELECTRICITY);
		list.add(NAME_ELECTRONIC_COMMUNICATIONS);
		list.add(NAME_MARINEAFFAIRS);
		list.add(NAME_MINING);
		list.add(NAME_OTHER_INDUSTRY);
		list.add(NAME_TOTAL_INDUSTRY);
		list.add(NAME_AGRICULTURE);
		list.add(NAME_FISHERIES);
		list.add(NAME_COMMERCE);
		list.add(NAME_TRAFFIC);
		list.add(NAME_TOTAL_ART);
		list.add(NAME_PICTURE_AND_SCULPTURE);
		list.add(NAME_PHOTOGRAPH_AND_CRAFT);
		list.add(NAME_MUSIC_AND_DANCE);
		list.add(NAME_THEATRE_AND_MOVIE);
		list.add(NAME_GYMNASTICS_AND_SPORT);
		list.add(NAME_AMUSEMENT);
		list.add(NAME_HOUSEKEEP);
		list.add(NAME_COMMIC_AND_GRAPHICNOVEL);
		list.add(NAME_TOTAL_LANGUAGE);
		list.add(NAME_JAPANESE);
		list.add(NAME_ENGLISH);
		list.add(NAME_GERMAN);
		list.add(NAME_FRENCH);
		list.add(NAME_LANGUAGES);
		list.add(NAME_TOTAL_LITERATURE);
		list.add(NAME_TOTAL_JAPANESELITERATURE);
		list.add(NAME_JAPANESEPOEM);
		list.add(NAME_JAPANESENOVEL_AND_TALE);
		list.add(NAME_CRITICISM_AND_ESSAY);
		list.add(NAME_FOREIGNNOVEL);
		list.add(NAME_FOREIGNLITERATURE);
		list.add(NAME_NONE);
	}
	
	public static void setArrayListAllItems(ArrayList<CategoryRow> list){
		list.add(new CategoryRow(CODE_GENERALDESCRIPTION, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ENCYCLOPEDIA, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ALMANACMAGAZINE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_INFORMATIONSCIENCE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_PHILOSOPHY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_PSYCOLOGY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ETHICS, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_RELIGION, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_BUDDHISM, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_CHRISTIANITY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_HISTORY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_JAPANESEHISTORY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_FOREIGNHISTORY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_BIOGRAPHY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_GEOGRAPHY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TRAVEL, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_SOCIALSCIENCE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_POLITICS, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_LAW, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ECONOMY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MANAGEMENT, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_SOCIETY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_EDUCATION, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_CUSTOM, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_NATURALSCIENCE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_PHYSICS, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_CHEMISTRY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ASTRONOMY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_BIOLOGY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MEDICINE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ENGINEERING, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ENGINEERING_WORK, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_CONSTRUCTION, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MACHINE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ELECTRICITY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ELECTRONIC_COMMUNICATIONS, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MARINEAFFAIRS, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MINING, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_OTHER_INDUSTRY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_INDUSTRY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_AGRICULTURE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_FISHERIES, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_COMMERCE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TRAFFIC, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_ART, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_PICTURE_AND_SCULPTURE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_PHOTOGRAPH_AND_CRAFT, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_MUSIC_AND_DANCE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_THEATRE_AND_MOVIE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_GYMNASTICS_AND_SPORT, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_AMUSEMENT, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_HOUSEKEEP, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_COMMIC_AND_GRAPHICNOVEL, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_LANGUAGE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_JAPANESE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_ENGLISH, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_GERMAN, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_FRENCH, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_LANGUAGES, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_LITERATURE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_TOTAL_JAPANESELITERATURE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_JAPANESEPOEM, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_JAPANESENOVEL_AND_TALE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_CRITICISM_AND_ESSAY, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_FOREIGNNOVEL, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_FOREIGNLITERATURE, CategoryRow.CONTENTS));
		list.add(new CategoryRow(CODE_NONE, CategoryRow.CONTENTS));
	}
}