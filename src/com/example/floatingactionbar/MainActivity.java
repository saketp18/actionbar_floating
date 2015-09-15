package com.example.floatingactionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.floatingactionbar.CustomScrollView.OnScrollViewListener;

public class MainActivity extends Activity {

	private ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ColorDrawable cd = new ColorDrawable(Color.rgb(68, 74, 83));
		mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(cd);
		
		cd.setAlpha(0);

		mActionBar.setDisplayHomeAsUpEnabled(true); //to activate back pressed on home button press
		mActionBar.setDisplayShowHomeEnabled(false); //
		mActionBar.setTitle("Menu");
		mActionBar.setSubtitle("Sub_Menu");
		
		
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
		addTextViews(linearLayout);

		
		
		CustomScrollView scrollView = (CustomScrollView) findViewById(R.id.scroll_view);
		scrollView.setOnScrollViewListener(new OnScrollViewListener() {
			
			@Override
			public void onScrollChanged(CustomScrollView v, int l, int t, int oldl, int oldt) {
				
				cd.setAlpha(getAlphaforActionBar(v.getScrollY()));
			}

			private int getAlphaforActionBar(int scrollY) {
				int minDist = 0,maxDist = 650;
				if(scrollY>maxDist){ 
					return 255;
					}
				else if(scrollY<minDist){
					return 0;
					}
				else {
					int alpha = 0;
					alpha = (int)  ((255.0/maxDist)*scrollY);
					return alpha;
				}
			}
		});
		
	}
	
	private void addTextViews(LinearLayout linearLayout) {
		
		for (int i = 0; i < 26; i++) {
			TextView text = new TextView(this);
			text.setText(String.valueOf(i));
			text.setTextSize(10);
			text.setWidth(500);
			text.setHeight(500);
			text.setBackgroundColor(Color.rgb(255-10*i, 255-10*i, 255-10*i)); //just for fun , varying back grounds
			text.setGravity(Gravity.CENTER);
			linearLayout.addView(text);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
