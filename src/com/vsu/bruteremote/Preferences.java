/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	// The preference key for IP address.
	public static final String KEY_PREF_IP_ADDRESS = "ip_address";

	// The preference key for port.
	public static final String KEY_PREF_PORT = "port";

    // Reference to the IP address preference.
    private EditTextPreference mPrefIpAddress;

    // Reference to the port preference.
    private EditTextPreference mPrefPort;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState  If the activity is being re-created from a previous
     *                            saved state, this is the state.
     */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the XML preferences file
		addPreferencesFromResource(R.xml.preferences);

		// Get a reference to the preferences
        mPrefIpAddress = (EditTextPreference)findPreference(KEY_PREF_IP_ADDRESS);
        mPrefPort = (EditTextPreference)findPreference(KEY_PREF_PORT);
    }

    /**
     * Called when the activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Set up the initial values
        mPrefIpAddress.setSummary(mPrefIpAddress.getText());
        mPrefPort.setSummary(mPrefPort.getText());

        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Called when the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Called when shared preferences change.
     * @param sharedPreferences  Reference to shared preferences.
     * @param key                The preference key.
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	Preference pref = findPreference(key);

        if (pref instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) pref;
            pref.setSummary(editTextPref.getText());

    	    if (key.equals(KEY_PREF_IP_ADDRESS)) {
    			BruteRemote.mIO.setHostname(editTextPref.getText());
    	    } else if (key.equals(KEY_PREF_PORT)) {
    	    	BruteRemote.mIO.setPort(editTextPref.getText());
    	    }        
        }
    }
}
