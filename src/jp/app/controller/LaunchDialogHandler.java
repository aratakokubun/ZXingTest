package jp.app.controller;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class LaunchDialogHandler {
	private static LaunchDialogHandler instance = null;
	
	private Activity parent;
	private Dialog dialog;
	
	public LaunchDialogHandler(){
		parent = null;
		dialog = null;
	}

	// Get singleton instance
	public static synchronized LaunchDialogHandler getInstance(){
		if(instance == null){
			instance = new LaunchDialogHandler();
		}
		return instance;
	}
	
	public void setActivity(Activity activity){
		parent = activity;
	}
	
	// Get view item in dialog
	public View findViewById(int id){
		return dialog.findViewById(id);
	}
	
	// Show dialog
	public void showDialog(View content) {
		dialog = new Dialog(parent);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(content);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.show();
	}
	
	public void showDialog(String title, View content) {
		if(checkDialogState()){
			dialog = new Dialog(parent);
			dialog.setTitle(title);
			dialog.setContentView(content);
			dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			dialog.show();
		}
	}
	
	public void showDialog(String title, int viewId){
		if(checkDialogState()){
			dialog = new Dialog(parent);
			dialog.setTitle(title);
			dialog.setContentView(viewId);
			// dialog.setContentView(parent.getLayoutInflater().inflate(viewId, null));
			dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			dialog.show();
		}
	}
	
	public void showDialog(int viewId){
		if(checkDialogState()){
			dialog = new Dialog(parent);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(viewId);
			// dialog.setContentView(parent.getLayoutInflater().inflate(viewId, null));
			dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			dialog.show();
		}
	}
	
	// Kill dialog
	public void dismissDialog(){
		dialog.dismiss();
	}
	
	// Check if dialog can be launched
	private boolean checkDialogState(){
		if(parent != null){
			if(dialog == null || !dialog.isShowing()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
