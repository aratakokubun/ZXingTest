package jp.app.httppost;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jp.app.bookList.BookRow;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import android.util.Log;

import com.amazon.advertising.api.sample.SignedRequestsHelper;

/**
 * http://ecs.amazonaws.jp/onca/xml? Service=AWSECommerceService
 * &AWSAccessKeyId=[AccessKey] &Version=2009-07-01 &ResponseGroup=Small,Images
 * &Operation=ItemLookup &ItemId=4344016645
 */
public class ManageRequestCode {
	private static boolean D = false;
	private static final String TAG = "Manage request code";
	
	private static final String URL_AMAZON_XML = "http://ecs.amazonaws.jp/onca/xml?";
	/** [AccessKey] */
	private static final String ACCESS_KEY = "AWSAccessKeyId";
	/** a */
	private static final String ASSOCIATE_TAG = "AssociateTag";
	/** nnnnnnnnnn */
	private static final String ITEM_ID = "ItemId";
	/** ItemLookUp etc... */
	private static final String OPERATION = "Operation";
	/** Small,Images,... */
	private static final String RESPONSE_GROUP = "ResponseGroup";
	/** AWSECommerceService */
	private static final String SERVICE = "Service";
	/** yyyy-mm-dd */
	private static final String VERSION = "Version";
	/** yyyy-MM-dd'T'HH:mm:ss'Z' */
	private static final String TIMESTAMP = "TimeStamp";

	/**
	 * Your AWS Access Key ID, as taken from the AWS Your Account page.
	 */
	private static final String AWS_ACCESS_KEY_ID = "AKIAJEZBQ55UN33EJY2A";
	/**
	 * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
	 * Your Account page.
	 */
	private static final String AWS_SECRET_KEY = "YBCsk3tmHFlo0VEL1kHpuoT5BH7q/s/LCFAGB33K";
	/**
	 * Use one of the following end-points, according to the region you are
	 * interested in:
	 * 
	 * US: ecs.amazonaws.com CA: ecs.amazonaws.ca UK: ecs.amazonaws.co.uk DE:
	 * ecs.amazonaws.de FR: ecs.amazonaws.fr JP: ecs.amazonaws.jp
	 * 
	 */
	private static final String ENDPOINT = "ecs.amazonaws.jp";
	//private static final String ENDPOINT = "webservices.amazon.com";
	/**
	 * service name of amazon
	 */
	private static final String SERVICE_AWSE = "AWSECommerceService";
	/**
	 * newest version of Amazon API
	 */
	private static final String VERSION_20110802 = "2011-08-02";
	/**
	 * dummy code of associate tag
	 */
	private static final String ASSOCIATE_TAG_ANNONYMOUS = "a";
	/**
	 * mode of helper
	 */
	private static final boolean USE_HASHMAP = false;
	/**
	 * preserved book data
	 * can be accessed from other classes
	 */
	public static BookRow bookRow;

	/*---------------------------------------------------------------------------------------*/
	/**
	 * make and throw amazon request code 
	 * @return
	 */
	public static int throwHttpRequestCode(String itemid, String operation, String response_group)
	{
		/*
		 * Set up the signed requests helper
		 */
		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (IllegalArgumentException e){
			return RESPONSE_CODE.NO_DATA;
		} catch (Exception e) {
			e.printStackTrace();
			return RESPONSE_CODE.WIFI_ERROR;
		}

		String requestUrl = null;

		/* The helper can sign requests in two forms - map form and string form */
		if (USE_HASHMAP) {
			/*
			 * Here is an example in map form, where the request parameters are
			 * stored in a map.
			 */
			Map<String, String> params = new HashMap<String, String>();
			params.put(SERVICE, SERVICE_AWSE);
			params.put(VERSION, VERSION_20110802);
			params.put(OPERATION, operation);
			params.put(ITEM_ID, itemid);
			params.put(RESPONSE_GROUP, response_group);
			params.put(ASSOCIATE_TAG, ASSOCIATE_TAG_ANNONYMOUS);

			requestUrl = helper.sign(params);
			if(D) Log.i(TAG, requestUrl);
			return fetchBookData(requestUrl, itemid, operation);
		} else {
			/*
			 * Here is an example with string form, where the requests
			 * parameters have already been concatenated into a query string.
			 */
			String queryString =
					SERVICE + "=" + SERVICE_AWSE + "&" + 
					VERSION + "=" + VERSION_20110802 + "&" + 
					OPERATION + "=" + operation + "&" + 
					RESPONSE_GROUP + "=" + response_group + "&" + 
					ITEM_ID + "=" + itemid + "&" + 
					ASSOCIATE_TAG + "=" + ASSOCIATE_TAG_ANNONYMOUS;
			requestUrl = helper.sign(queryString);
			if(D) Log.i(TAG, requestUrl);
			return fetchBookData(requestUrl, itemid, operation);
		}
	}

	/*---------------------------------------------------------------------------------------*/
	/**
	 * Utility function to fetch the response from the service and extract the
	 * title from the XML.
	 */
	public static String fetchTitle(String requestUrl) {
		String title = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			Node titleNode = doc.getElementsByTagName("Title").item(0);
			title = titleNode.getTextContent();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return title;
	}

	/*---------------------------------------------------------------------------------------*/
	/**
	 * set book data from xml of amazon
	 */
	public static final String TITLE = "Title";
	public static final String AUTHOR = "Author";
	public static final String LABEL = "Label";
	public static final String BINDING = "Binding";
	public static final String AMOUNT = "Amount";
	public static final String CURRENCY = "CurrencyCode";
	
	public static int fetchBookData(String requestUrl, String isbn, String step2)
	{
		String title = null;
		String author = null;
		String label = null;
		String binding = null;
		String price = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			
			Node titleNode = doc.getElementsByTagName(TITLE).item(0);
			Node authorNode = doc.getElementsByTagName(AUTHOR).item(0);
			Node labelNode = doc.getElementsByTagName(LABEL).item(0);
			Node bindingNode = doc.getElementsByTagName(BINDING).item(0);
			Node amountNode = doc.getElementsByTagName(AMOUNT).item(0);
			Node currencyNode = doc.getElementsByTagName(CURRENCY).item(0);
			
			title = titleNode.getTextContent();
			author = authorNode.getTextContent();
			label = labelNode.getTextContent();
			binding = bindingNode.getTextContent();
			price = currencyNode.getTextContent() + amountNode.getTextContent();
			
			if(D) Log.i(TAG, isbn + ", " + step2 + ", " + title + ", " + author + ", " + label + ", " + binding + ", " + price);
			bookRow = new BookRow(isbn, step2, title, author, label, binding, price);
			return RESPONSE_CODE.CORRECTLY_DECODED;
		} catch (Exception e) {
			//throw new RuntimeException(e);
			return RESPONSE_CODE.DECODE_ERROR;
		}
	}
}