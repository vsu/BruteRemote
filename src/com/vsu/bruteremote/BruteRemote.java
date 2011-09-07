/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class BruteRemote extends FragmentActivity {
    // Debugging
    private static final String TAG = "BruteRemote";
    private static final boolean D = false;

    // Reference to the tab host.
    TabHost mTabHost;

    // Reference to the tab manager.
    TabManager mTabManager;

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
        mTabHost.setup();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);

        mTabManager.addTab(
        		mTabHost.newTabSpec("equalizer")
        				.setIndicator(res.getString(R.string.tab_equalizer),
        							  res.getDrawable(R.drawable.ic_tab_equalizer)),
                Equalizer.class,
                null);

        mTabManager.addTab(
        		mTabHost.newTabSpec("file1")
        				.setIndicator(res.getString(R.string.tab_file1),
        						      res.getDrawable(R.drawable.ic_tab_file)),
                File1.class,
                null);

        mTabManager.addTab(
        		mTabHost.newTabSpec("file2")
        				.setIndicator(res.getString(R.string.tab_file2),
        							  res.getDrawable(R.drawable.ic_tab_file)),
                File2.class,
                null);

        mTabManager.addTab(
        		mTabHost.newTabSpec("file3")
        				.setIndicator(res.getString(R.string.tab_file3),
        						      res.getDrawable(R.drawable.ic_tab_file)),
                File3.class,
                null);

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        mIO = new IO();
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
     * Called when the activity is resumed.
     */
    @Override
    public synchronized void onResume() {
        if (D) Log.e(TAG, "+ ON RESUME +");
        super.onResume();

        Resources res = getResources();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String hostname = preferences.getString(Preferences.KEY_PREF_IP_ADDRESS, "");

        int port;
        try {
        	port = Integer.parseInt(preferences.getString(Preferences.KEY_PREF_PORT, ""));
        }
        catch (NumberFormatException e) {
        	port = -1;
        }

        if (hostname.equals("") || port == -1) {
        	new AlertDialog.Builder(this)
        		.setIcon(android.R.drawable.ic_dialog_alert)
	    		.setTitle(res.getString(R.string.title_no_server))
	    		.setMessage(res.getString(R.string.message_no_server))
	    		.setNegativeButton(res.getString(R.string.button_close), null)
	    		.show();
        }
        else
        {
        	if (mIO.isConnected()) {
        		mIO.close();
        	}

        	if (!mIO.connect(hostname, port)) {
                Toast.makeText(
                        this,
                        res.getString(R.string.message_open_connection_error),
                        Toast.LENGTH_LONG).show();
	        }
        }
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

    /**
     * This is a helper class that implements a generic mechanism for
     * associating fragments with the tabs in a tab host.  It relies on a
     * trick.  Normally a tab host has a simple API for supplying a View or
     * Intent that each tab will show.  This is not sufficient for switching
     * between fragments.  So instead we make the content part of the tab host
     * 0dp high (it is not shown) and the TabManager supplies its own dummy
     * view to show as the tab content.  It listens to changes in tabs, and takes
     * care of switch to the correct fragment shown in a separate content area
     * whenever the selected tab changes.
     */
    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentActivity mActivity;
        private final TabHost mTabHost;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerId = containerId;
            mTabHost.setOnTabChangedListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);

            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }

            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        @Override
        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();

                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
                        ft.detach(mLastTab.fragment);
                    }
                }

                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
                        ft.attach(newTab.fragment);
                    }
                }

                mLastTab = newTab;
                ft.commit();
                mActivity.getSupportFragmentManager().executePendingTransactions();
            }
        }
    }
}