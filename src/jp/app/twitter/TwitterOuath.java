package jp.app.twitter;

import jp.app.zxing.R;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class TwitterOuath extends Activity {

	private static final String TAG = "Twitter Ouath";
	private static final boolean D = true;
	
    private String mCallbackURL;
    private Twitter mTwitter;
    private RequestToken mRequestToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_ouath);

        mCallbackURL = getString(R.string.twitter_callback_url);
        mTwitter = TwitterUtils.getTwitterInstance(this);

        final ImageView yes = (ImageView)findViewById(R.id.button_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	// TODO 通信中は同期してクルクルを表示
                startAuthorize();
            }
        });
        
        final ImageView no = (ImageView)findViewById(R.id.button_no);
        no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backToMainActivity();
			}
		});
    }

    /**
     * OAuth認証（厳密には認可）を開始します。
     * 
     * @param listener
     */
    private void startAuthorize() {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    mRequestToken = mTwitter.getOAuthRequestToken(mCallbackURL);
                    return mRequestToken.getAuthorizationURL();
                } catch (TwitterException e) {
                    if(D) e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String url) {
                if (url != null) {
                    if(D) Log.e(TAG, "ready to get intent");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    if(D) Log.e(TAG, "ready to start activity intent");
                    startActivity(intent);
                } else {
                    // 失敗。。。
                	showToast(getString(R.string.action_view_launch_failed));
                }
            }
        };
        task.execute();
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (intent == null
                || intent.getData() == null
                || !intent.getData().toString().startsWith(mCallbackURL)) {
            return;
        }
        String verifier = intent.getData().getQueryParameter("oauth_verifier");

        AsyncTask<String, Void, AccessToken> task = new AsyncTask<String, Void, AccessToken>() {
            @Override
            protected AccessToken doInBackground(String... params) {
                try {
                    return mTwitter.getOAuthAccessToken(mRequestToken, params[0]);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(AccessToken accessToken) {
                if (accessToken != null) {
                    // oauth succeed
                    showToast(getString(R.string.twitter_ouath_success));
                    successOAuth(accessToken);
                } else {
                    // oauth failed
                    showToast(getString(R.string.twitter_ouath_failed));
                }
            }
        };
        task.execute(verifier);
    }

    private void successOAuth(AccessToken accessToken) {
        TwitterUtils.storeAccessToken(this, accessToken);
        //別のActivityから起動するので，戻る際Intentを渡す必要はない
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        finish();
    }
    
	//--------------------------------------------------------------------------------------------
    private void backToMainActivity()
    {
    	this.finish();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}