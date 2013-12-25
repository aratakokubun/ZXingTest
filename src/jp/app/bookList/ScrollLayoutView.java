package jp.app.bookList;

import grimbo.android.demo.slidingmenu.MyHorizontalScrollView;
import grimbo.android.demo.slidingmenu.MyHorizontalScrollView.SizeCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;

public class ScrollLayoutView extends LayoutView {
	public MyHorizontalScrollView scrollView;
	public View menu, app;

	public ScrollLayoutView(BookListActivity bookListActivity) {
		super(bookListActivity);
	}
		
	@Override
	public void initScrollView(int scrollViewId, int menuId, int appId){
		super.initScrollView(scrollViewId, menuId, appId);
		
		LayoutInflater inflater = LayoutInflater.from(activity);
		scrollView = (MyHorizontalScrollView) inflater.inflate(scrollViewId, null);
		view = scrollView; // Set scroll view to it's content view
		
		menu = inflater.inflate(menuId, null);
		app = inflater.inflate(appId, null);
	}
	
    /**
     * Helper for examples with a HSV that should be scrolled by a menu View's width.
     */
    static class ClickListenerForScrolling implements OnClickListener {
        HorizontalScrollView scrollView;
        View menu, app;
        /**
         * Menu must NOT be out/shown to start with.
         */
        boolean menuOut = false;

        // TODO
        // 右からにするときはしっかり各viewの幅を決めないとうまくいかない可能性が高い
        public ClickListenerForScrolling(HorizontalScrollView scrollView, View menu) {
            super();
            this.scrollView = scrollView;
            this.menu = menu;
        }

        @Override
        public void onClick(View v) {
            // Context context = menu.getContext();

            int menuWidth = menu.getMeasuredWidth();

            // Ensure menu is visible
            menu.setVisibility(View.VISIBLE);

            if (!menuOut) {
            	// Scroll to 0 to reveal menu
            	int left = 0;
            	scrollView.smoothScrollTo(left, 0);
            } else {
            	// Scroll to menuWidth so menu isn't on screen.
            	int left = menuWidth;
            	scrollView.smoothScrollTo(left, 0);
            }
            menuOut = !menuOut;
        }
    }
    
    /**
     * Helper that remembers the width of the 'slide' button, so that the 'slide' button remains in view, even when the menu is
     * showing.
     */
    static class SizeCallbackForMenu implements SizeCallback {
        int btnWidth;
        View btnSlide;

        public SizeCallbackForMenu(View btnSlide) {
            super();
            this.btnSlide = btnSlide;
        }

        @Override
        public void onGlobalLayout() {
            btnWidth = btnSlide.getMeasuredWidth();
        }

        @Override
        public void getViewSize(int idx, int w, int h, int[] dims) {
            dims[0] = w;
            dims[1] = h;
            final int menuIdx = 0;
            if (idx == menuIdx) {
                dims[0] = w - btnWidth;
            }
        }
    }
}
