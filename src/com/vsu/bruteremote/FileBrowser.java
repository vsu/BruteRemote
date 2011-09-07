/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FileBrowser extends ListActivity {
    // Debugging
    private static final String TAG = "FileBrowser";
    private static final boolean D = false;

    // Intent extra for displayed filename.
    public static String EXTRA_FILENAME = "filename";

    // Intent extra for full path to file.
    public static String EXTRA_FULLPATH = "fullpath";

    // Intent extra for parent directory of file.
    public static String EXTRA_PARENTPATH = "directory";

    // Intent extra for initial file browser path.
    public static String EXTRA_STARTPATH = "startpath";

	// Intent request codes
    public static final int REQUEST_SELECT_FILE = 1;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState  If the activity is being re-created from a previous
     *                            saved state, this is the state.
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
        if (D) Log.e(TAG, "+ ON CREATE +");

		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();

		String directory = "";
		if (extras != null) {
			directory = extras.getString(EXTRA_STARTPATH);
		}

	    // Render initial directory listing
	    refreshData(directory);

        // Set result CANCELED in case the user backs out
        setResult(Activity.RESULT_CANCELED);
	}

	/**
	 * Called when a list item is clicked.
	 * @param l         The ListView where the click happened
	 * @param v         The view that was clicked within the ListView
     * @param position  The position of the view in the list
     * @param id        The row id of the item that was clicked
     */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Get the item that was clicked
		FileBrowserItem item = (FileBrowserItem)this.getListAdapter().getItem(position);

		if (item.isSubdirectory()) {
			refreshData(item.getFullPath());
		}
		else {
	        // Create the result Intent and include the filename
	        Intent intent = new Intent();
	        intent.putExtra(EXTRA_FILENAME, item.getDisplayText());
	        intent.putExtra(EXTRA_PARENTPATH, item.getParentPath());
	        intent.putExtra(EXTRA_FULLPATH, item.getFullPath());

	        // Set result and finish this Activity
	        setResult(Activity.RESULT_OK, intent);

			finish();
		}
	}

	/**
	 * Retrieves data and refreshes the view.
     * @param path  The directory path to load.
     */
	private void refreshData(String path) {
        if (BruteRemote.mIO.checkConnection()) {
		    String json = BruteRemote.mIO.listDirectory(path);

		    if (!json.equals("")) {
		    	try {
		    		FileBrowserItem[] items = parse(json);
		    		if (items != null) {
		    			this.setTitle(path);
					    this.setListAdapter(new FileBrowserArrayAdapter(this, items));
		    		}
		    	}
		    	catch (Exception e) {
		    	}
		    }
        }
	}

	private FileBrowserItem[] parse(String jString) throws Exception {
	    List<FileBrowserItem> items = new ArrayList<FileBrowserItem>();

	    JSONObject jObject = new JSONObject(jString);

		String dir = jObject.getString("dir");
		JSONArray subdirArray = jObject.getJSONArray("subdir");
		JSONArray fileArray = jObject.getJSONArray("file");

		for (int ix = 0; ix < subdirArray.length(); ix++) {
			JSONObject obj = subdirArray.getJSONObject(ix);

			String display = obj.getString("display");
			String name = obj.getString("name");
			String path = obj.getString("path");

	    	items.add(new FileBrowserItem(display, name, dir, path, true));
		}

		for (int ix = 0; ix < fileArray.length(); ix++) {
			JSONObject obj = fileArray.getJSONObject(ix);

			String display = obj.getString("display");
			String name = obj.getString("name");
			String path = obj.getString("path");

	    	items.add(new FileBrowserItem(display, name, dir, path, false));
		}

	    return items.toArray(new FileBrowserItem[0]);
	}

	public class FileBrowserArrayAdapter extends ArrayAdapter<FileBrowserItem> {
		// The list of file browser items.
		private final FileBrowserItem[] mItems;

	    /**
	     * Constructor for the class.
	     * @param context  The activity context.
	     * @param items    The file browser items.
	     */
		public FileBrowserArrayAdapter(Context context, FileBrowserItem[] items) {
			super(context, R.layout.rowlayout, items);
			mItems = items;
		}

		/**
		 * Called to render the View.
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View rowView = inflater.inflate(R.layout.rowlayout, null, true);

			TextView textView = (TextView) rowView.findViewById(R.id.label);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

			// Change the icon for directories
			FileBrowserItem item = mItems[position];
			if (item.isSubdirectory()) {
				imageView.setImageResource(R.drawable.ic_menu_archive);
			} else {
				imageView.setImageResource(R.drawable.ic_menu_compose);
			}

			textView.setText(item.getDisplayText());

			return rowView;
		}
	}
}
