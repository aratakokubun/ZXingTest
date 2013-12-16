package jp.app.httppost;

import jp.app.zxing.SearchBookWeb;
import android.app.AlertDialog;
import android.util.Log;

public class HttpPostAmazon
{
	private static final String TAG = "HttpPostAmazon";
	private SearchBookWeb activity;
	
	public HttpPostAmazon(SearchBookWeb searchBookWeb) {
		activity = searchBookWeb;
	}
	
	public void searchIsbnAmazon(String isbn)
	{
		Log.e(TAG, "http start.");
		HttpPostTask task = new HttpPostTask(
				activity,
				"https://ecs.amazonaws.jp/onca/xml?",
				//ManageRequestCode.makeHttpRequestCode(isbn, OPERATION_CODE.ITEM_LOOKUP, RESPONSE_GROUP_CODE.ITEMATTRIBUTES),
				new HttpPostHandler() {
			@Override
			public void onPostCompleted(String response) {
				//TODO
				Log.e(TAG, response);
			}

			@Override
			public void onPostFailed(String response) {
				showErrorDialog();
			}
		});
		task.execute();
	}
	
	/**
	 * 一般的な通信エラーを表示します。
	 * onPostFailed()で特にやることがなかったら表示しましょう。
	 */
	public void showErrorDialog() {
		if(activity == null) return;
		new AlertDialog.Builder(activity)
		.setIcon(android.R.drawable.ic_menu_close_clear_cancel)
		.setTitle("通信エラー")
		.setMessage("通信エラーが発生しました。電波の良いところでもう一度試して下さい。")
		.setPositiveButton("OK", null)
		.show();
	}
}