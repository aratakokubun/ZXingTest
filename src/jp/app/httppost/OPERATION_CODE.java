package jp.app.httppost;

public class OPERATION_CODE
{
	/**
	 * 指定したブラウズノードに含まれる子のブラウズノードや、トップレベルのブラウズノードからの各階層のブラウズノードに関する情報を取得できます。
	 */
	public static final String BROWSE_NODE_LOOKUP = "BrowseNodeLookup";
	/**
	 * カートに商品追加
	 */
	public static final String CART_ADD = "CartAdd";
	/**
	 * カートをクリア
	 */
	public static final String CART_CLEAR = "CartClear";
	/**
	 * カートを作成
	 */
	public static final String CART_CREATE = "CartCreate";
	/**
	 * カートを取得
	 */
	public static final String CART_GET = "CartGet";
	/**
	 * カート内の情報変更
	 */
	public static final String CART_MODIFY = "CartModify";
	/**
	 * 顧客をID検索
	 */
	public static final String CUSTOMER_CONTENT_LOOKUP = "CustomerContentLookup";
	/**
	 * 顧客を検索
	 */
	public static final String CUSTOMER_CONTENT_SEARCH = "CustomerContentSearch";
	/**
	 * HELPを表示
	 */
	public static final String HELP = "Help";
	/**
	 * ISBN,ASINなどを指定してAmazonで販売している商品を検索します。
	 */
	public static final String ITEM_LOOKUP = "ItemLookup";
	/**
	 * 商品名などを指定してAmazonで販売している商品を検索します。
	 */
	public static final String ITEM_SEARCH = "ItemSearch";
	/**
	 * リストマニア情報をID検索
	 */
	public static final String LIST_LOOKUP = "ListLookup";
	/**
	 * リストマニア情報を検索
	 */
	public static final String LIST_SEARCH = "ListSearch";
	/**
	 * 特定の売り手の商品をID検索
	 */
	public static final String SELLER_LISTING_LOOKUP = "SellerListingLookup";
	/**
	 * 特定の売り手の商品検索
	 */
	public static final String SELLER_LISTING_SEARCH = "SellerListingSearch";
	/**
	 * 売り手を検索
	 */
	public static final String SELLER_LOOKUP = "SellerLookup";
	/**
	 * 関連商品の検索
	 */
	public static final String SIMILARITY_LOOKUP = "SimilarityLookup";
}