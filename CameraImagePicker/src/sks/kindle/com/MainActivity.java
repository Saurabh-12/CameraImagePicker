package sks.kindle.com;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//global variable
	Button btn1,btn2;
	Uri myPicture = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Getting View
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        
        
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "you clicked Camera", Toast.LENGTH_LONG).show();
				ContentValues values = new ContentValues();
		        values.put(Media.TITLE, "My demo image");
		        values.put(Media.DESCRIPTION, "Image Captured by Camera via an Intent");
		        myPicture = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		        i.putExtra(MediaStore.EXTRA_OUTPUT, myPicture);
		        startActivityForResult(i, 0);
				
			}
		});
        
        
        btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(getBaseContext(), GridViewClass.class);
				startActivity(i);
				
				
			}
		});
        
        
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0 && resultCode==Activity.RESULT_OK)
        {
/*             Intent inn = new Intent(Intent.ACTION_VIEW);
             inn.setData(myPicture);
             startActivity(inn);*/
 	        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
 			imageView.setImageURI(myPicture);
             
             
        }
    }
}
