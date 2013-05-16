package sks.kindle.com;
/**
 * Author 
 * Saurabh sharma*/

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewClass extends FragmentActivity implements LoaderCallbacks<Cursor> {
	
	/** SimpleCursorAdapter, holds images and layout for the gridview */
	SimpleCursorAdapter mAdapter;
	
	@Override
	protected void onStart() {		
		super.onStart();
		
		/** Initializes the Loader */
        getSupportLoaderManager().initLoader(0, null, this);
	}
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        /** Getting a reference to gridview of the MainActivity layout */
        GridView gridView = (GridView) findViewById(R.id.gridview);
        
        /** Create an adapter for the gridview */
        /** This adapter defines the data and the layout for the grid view */ 
        mAdapter = new SimpleCursorAdapter(
        								getBaseContext(), 
        								R.layout.gridview, 
        								null, 
        								new String[] { "_data","_id"} , 
        								new int[] { R.id.img},
        								0
        );
        
        /** Setting adapter for the gridview */
        gridView.setAdapter(mAdapter);     
        
        /** Loader to get images from the SD Card */
        getSupportLoaderManager().initLoader(0, null, this);
        
        /** Defining item click listener for the grid view */
        OnItemClickListener itemClickListener = new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        		
        		/** Getting the cursor object corresponds to the clicked item */
        		Cursor c1 = (Cursor ) arg0.getItemAtPosition(position);
        		
        		/** Getting the image_id from the cursor */
        		/** image_id of the thumbnail is same as the original image id */
        		String id = c1.getString(c1.getColumnIndex("image_id"));
        		//String name = c1.getString(c1.getColumnIndex("image_name"));
        		
        		
        		/** Creating a bundle object to pass the image_id to the ImageDialog */
        		Bundle b = new Bundle();
        		
        		/** Storing image_id in the bundle object */
        		b.putString("image_id", id);
        		//b.putString("image_name", name);
        		
        		/** Instantiating ImageDialog, which displays the clicked image */
        		ImageDialog img = new ImageDialog();
        		
        		/** Setting the bundle object to the ImageDialog */
        		img.setArguments(b);
        		
        		/** Opening ImageDialog */
        		img.show(getSupportFragmentManager(), "IMAGEDIALOG");
        		return ;
        		
        	}
        	
		};
        
		/** Setting itemclicklistener for the grid view */
		gridView.setOnItemClickListener(itemClickListener);
		
    }

 

    /** A callback method invoked by the loader when initLoader() is called */
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		/** Getting uri to the Thumbnail images stored in the external storage */
		Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;		
		
		/** Invoking the uri */
		return new CursorLoader(this, uri, null, null, null, null);
	}

	/** A callback method, invoked after the requested content provider returned all the data */	
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {		
		mAdapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub		
	}

}
