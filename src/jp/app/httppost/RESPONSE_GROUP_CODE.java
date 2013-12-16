package jp.app.httppost;

public class RESPONSE_GROUP_CODE
{
	public static final String ACCESSORIES = "Accessories";
	public static final String ALTERNATEVERSIONS = "AlternateVersions";
	/**
	 * 検索に該当した商品のブラウズノードに関する情報を取得する場合には「BrowseNodes」を設定します。
	 */
	public static final String BROWSENODES = "BrowseNodes";
	public static final String COLLECTIONS = "Collections";
	public static final String EDITORIALREVIEW = "EditorialReview";
	/**
	 * 検索に該当した商品の画像を表示する為のURLを取得する場合には「Images」を設定します。
	 */
	public static final String IMAGES = "Images";
	/**
	 * 検索に該当した商品の詳細情報を取得する場合には「ItemAttributes」を設定します。
	 */
	public static final String ITEMATTRIBUTES = "ItemAttributes";
	public static final String ITEMIDS = "ItemIds";
	/**
	 * 「Large」は「Medium」に加えてレビューやリストマニア、ノード、関連情報などほぼ全ての商品情報を取得できるように設定されたグループです。
	 */
	public static final String LARGE = "Large";
	/**
	 * 検索に該当した商品に関連するリストマニアの情報を取得する場合には「ListmaniaLists」を設定します。
	 */
	public static final String LISTMANIALISTS = "ListmaniaLists";
	/**
	 * 「Medium」は「Small」に加えて画像や販売に関する情報を追加して取得できるように設定されたグループです。
	 */
	public static final String MEDIUM = "Medium";
	public static final String MERCHANTITEMATTRIBUTES = "MerchantItemAttributes";
	/**
	 * 「OfferSummary」よりも詳細な商品の新品価格や中古価格に関する情報を取得する場合には「OfferFull」を設定します。
	 * 中古の商品についても検索対象とする場合は出品者を表す「MerchantId」パラメータに「All」を指定して下さい。(デフォルトでは「Amazon」となっておりAmazonの商品のみ対象となるため)。
	 * また対象商品の状態を表す「Condition」パラメータに「All」を指定して下さい。(デフォルトでは「New」となっており新品のみ対象となるため)。
	 */
	public static final String OFFERFULL = "OfferFull";
	public static final String OFFERLISTINGS = "OfferListings";
	/**
	 * 検索に該当した商品の新品価格や中古価格に関する情報を取得する場合には「OfferSummary」を設定します。
	 */
	public static final String OFFERSUMMARY = "OfferSummary";
	public static final String OFFERS = "Offers";
	public static final String PROMOTIONDETAILS = "PromotionDetails";
	public static final String PROMOTIONSUMMARY = "PromotionSummary";
	public static final String PROMOTIONALTAG = "PromotionalTag";
	public static final String RELATEDITEMS = "RelatedItems";
	/**
	 * 送信したリクエストの内容をデバックなどのために取得したい場合には「Request」を設定します。
	 */
	public static final String REQUEST = "Request";
	/**
	 * 検索に該当した商品のレビューを取得する場合には「Reviews」を設定します。
	 */
	public static final String REVIEWS = "Reviews";
	/**
	 * 検索に該当した商品の売上ランキングを取得する場合には「SalesRank」を設定します。
	 */
	public static final String SALESRANK = "SalesRank";
	public static final String SEARCHBINS = "SearchBins";
	public static final String SEARCHINSIDE = "SearchInside";
	public static final String SHIPPINGCHARGES = "ShippingCharges";
	/**
	 * 検索に該当した商品の関連商品の情報を取得する場合には「Similarities」を設定します。
	 */
	public static final String SIMILARITIES = "Similarities";
	/**
	 * 「Small」は商品に関して必要最小限の情報を取得できるように設定されたグループです。
	 * 「ASIN」や「Title」の他に「DetailPageURL」や「Role」など「ItemAttributes」を指定した時に取得できる情報の一部を取得できます。
	 */
	public static final String SMALL = "Small";
	public static final String SUBJECTS = "Subjects";
	public static final String TAGS = "Tags";
	public static final String TAGSSUMMARY = "TagsSummary";
	/**
	 * CDなどに含まれている曲目のリストを取得する場合には「Tracks」を設定します。実際に記述する場合は次のようにします。
	 * 商品が例えば2枚組みのCDの場合などにはCD毎に曲目の一覧を取得します。また曲目が含まれる「Track」要素には「Number」属性を使って曲目番号が付けられています。
	 */
	public static final String TRACKS = "Tracks";
	public static final String VARIATIONIMAGES = "VariationImages";
	public static final String VARIATIONMATRIX = "VariationMatrix";
	public static final String VARIATIONMINIMUM = "VariationMinimum";
	public static final String VARIATIONOFFERS = "VariationOffers";
	public static final String VARIATIONSUMMARY = "VariationSummary";
	public static final String VARIATIONS = "Variations";
}