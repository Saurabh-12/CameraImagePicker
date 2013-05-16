package sks.kindle.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends Activity {
	// Global Variable
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// get intent data
		Intent i = getIntent();
		
		// Selected image id
		int position = i.getExtras().getInt("id");
		ImageView imageView = (ImageView)findViewById(R.id.imageView1);
		imageView.setImageResource(position);
		
	}

}
