package jp.app.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

/**
 * ファイルの入出力を行う抽象クラス
 */
public abstract class FileIO {
	private Context context;

	public FileIO(Context context) {
		this.context = context;
	}

	abstract String getFileName();
	abstract boolean loadData();
	abstract boolean saveData();

	/**
	 * ファイルの読み込み関数
	 * @return
	 */
	protected String readFile() {
		String data = "";
		try {
			FileInputStream fis = context.openFileInput(getFileName());
			InputStreamReader isw = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isw);
			String line;
			while ((line = br.readLine()) != null) {
				data += line;
			}
			br.close();
		} catch (IOException e) {
			writeFile("");
			return "";
		}
		return data;
	}

	/**
	 * ファイルへのデータの書き込み関数
	 * @return
	 */
	protected boolean writeFile(String data) {
		try {
			FileOutputStream fos = context.openFileOutput(getFileName(), Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(data);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}