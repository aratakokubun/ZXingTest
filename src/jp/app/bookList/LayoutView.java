package jp.app.bookList;

import jp.app.fileio.FileBookData;
import android.view.View;

public abstract class LayoutView {
	/** 呼び出し元のクラス */ public BookListActivity activity;
	/** メインとなるビュー */ public View view;
	/** ビューの表示準備ができたか否か。準備が必要な場合はinitViewで初期化すること。 */ public boolean prepared;
	/** ユーザーデータ */ public FileBookData book;

	public LayoutView(BookListActivity bookListActivity) {
		activity = bookListActivity;
		book = bookListActivity.fileBookData;
	}

	/**
	 * アプリ起動時に呼ばれます。
	 * xmlファイルからビューを生成します。
	 * ボタンのクリックリスナーなどの登録を行います。
	 */
	protected void initView(int id) {
		view = activity.getLayoutInflater().inflate(id, null);
	}
	
	/**
	 * ビューの表示準備をします。通信などを開始しましょう。
	 * 特に準備することがない場合はtrueを返します。
	 */
	protected boolean prepareView() {
		return true;
	}

	/**
	 * ビュー表示時に呼ばれます。
	 * 通信はprepareView()などで用意しておきましょう。
	 */
	protected void startView() {}

	/**
	 * ビューを閉じる時に呼ばれます。
	 * 画像などを読み込んだ場合はリサイクルしましょう。
	 */
	protected void endView() {}
}