/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class BruteRemote extends TabActivity {
    // Debugging
    private static final String TAG = "BruteRemote";
    private static final boolean D = false;

    // Reference to the tab host.
    TabHost mTabHost;

    // The IO object for communicating with the server.
    public static IO mIO;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState  If the activity is being re-created from a previous
     *                            saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();

        setContentView(R.layout.main);
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);

        /** TabSpec used to create a new tab.
        * By using TabSpec only we can able to setContent to the tab.
        * By using TabSpec setIndicator() we can set name to tab. */
        TabSpec equalizerTabSpec = mTabHost.newTabSpec("equalizer");
        TabSpec file1TabSpec = mTabHost.newTabSpec("file1");
        TabSpec file2TabSpec = mTabHost.newTabSpec("file2");
        TabSpec file3TabSpec = mTabHost.newTabSpec("file3");

        /** TabSpec setIndicator() is used to set name for the tab. */
        /** TabSpec setContent() is used to set content for a particular tab. */
        equalizerTabSpec
        	.setIndicator(res.getString(R.string.tab_equalizer), 
                          res.getDrawable(R.drawable.ic_tab_equalizer))
        	.setContent(new Intent(this, Equalizer.class));
        file1TabSpec
        	.setIndicator(res.getString(R.string.tab_file1),
                          res.getDrawable(R.drawable.ic_tab_file))
        	.setContent(new Intent(this, File1.class));
        file2TabSpec
        	.setIndicator(res.getString(R.string.tab_file2),
                          res.getDrawable(R.drawable.ic_tab_file))
        	.setContent(new Intent(this, File2.class));
        file3TabSpec
        	.setIndicator(res.getString(R.string.tab_file3),
                          res.getDrawable(R.drawable.ic_tab_file))
        	.setContent(new Intent(this, File3.class));

        /** Add tabSpec to the TabHost to display. */
        mTabHost.addTab(equalizerTabSpec);
        mTabHost.addTab(file1TabSpec);        
        mTabHost.addTab(file2TabSpec);        
        mTabHost.addTab(file3TabSpec);        
        
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mIO = new IO();
        mIO.setHostname(preferences.getString(Preferences.KEY_PREF_IP_ADDRESS, ""));
        mIO.setPort(preferences.getString(Preferences.KEY_PREF_PORT, ""));
    }

    /**
     * Called to save instance state.
     * @param outState  The state to save.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }

    /**
     * Called when the activity is paused.
     */
    @Override
    public synchronized void onPause() {
        if (D) Log.e(TAG, "+ ON PAUSE +");
        super.onPause();

    	if (mIO.isConnected()) {
    		mIO.close();
    	}
    }

    /**
     * Creates the options menu.
     * @param menu  The menu object of the Activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    /**
     * Called when an item is selected from the options menu.
     * @param item  The menu item selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.preferences:
            startActivity(new Intent(this, Preferences.class));
            return true;
        }

        return false;
    }
}