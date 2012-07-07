/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class File2 extends Activity {
    // Debugging
    private static final String TAG = "File2";
    private static final boolean D = false;

    // The maximum level in dB.
    private static final int LEVEL_MAX_DB = 20;

    // The number of adjustment steps per dB for level.
    private static final int LEVEL_STEPS_PER_DB = 10;

    // The seekbar value range for level.
    private static final int LEVEL_SEEKBAR_RANGE = 2 * LEVEL_MAX_DB * LEVEL_STEPS_PER_DB;

    // The most recently used directory path.
    private static final String KEY_PREF_DIRECTORY = "file2_directory";

    // A reference to the enable button.
    private ToggleButton mButtonEnable;

    // A reference to the level seekbar.
    private SeekBar mSeekBarLevel;

    // A reference to shared preferences.
    private SharedPreferences mPreferences;

    /**
     * Called when the activity is created.
     * @param savedInstanceState  If the activity is being re-created from a previous
     *                            saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (D) Log.e(TAG, "+ ON CREATE +");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.file2);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mButtonEnable = (ToggleButton)findViewById(R.id.toggleButtonEnableFile2);
        mButtonEnable.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               if (BruteRemote.mIO.checkConnection()) {
                    BruteRemote.mIO.setEnable(IO.CMD_FILE2_ENABLE, mButtonEnable.isChecked());
            }
            }
        });

        final TextView textViewLevel = (TextView)findViewById(R.id.textViewLevelFile2);
        mSeekBarLevel = (SeekBar)findViewById(R.id.seekBarLevelFile2);

        mSeekBarLevel.setMax(LEVEL_SEEKBAR_RANGE);
        mSeekBarLevel.setProgress(toProgress(0));

        mSeekBarLevel.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double level = (double)toLevel(progress) / (double)LEVEL_STEPS_PER_DB;
                textViewLevel.setText(Double.toString(level) + " dB");

                if (fromUser) {
                    if (BruteRemote.mIO.checkConnection()) {
                        BruteRemote.mIO.setLevel(IO.CMD_FILE2_LEVEL, toLevel(progress));
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final Button buttonBrowse = (Button)findViewById(R.id.buttonBrowseFile2);
        buttonBrowse.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String directory = mPreferences.getString(KEY_PREF_DIRECTORY, "");

                Intent intent = new Intent(v.getContext(), FileBrowser.class);
                intent.putExtra(FileBrowser.EXTRA_STARTPATH, directory);

                startActivityForResult(intent, FileBrowser.REQUEST_SELECT_FILE);
            }
        });

        final Button buttonDelete = (Button)findViewById(R.id.buttonDeleteFile2);
        buttonDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TextView textViewFilename =
                        (TextView)findViewById(R.id.textViewFilenameFile2);

                if (!textViewFilename.getText().equals("")) {
                    new AlertDialog.Builder(v.getContext())
	                    .setIcon(android.R.drawable.ic_dialog_info)
	                    .setTitle(R.string.title_file_confirm)
	                    .setMessage(R.string.message_delete_file)
	                    .setPositiveButton(R.string.button_ok,
	                        new DialogInterface.OnClickListener() {
	                            public void onClick(DialogInterface dialog, int whichButton) {
	                                if (BruteRemote.mIO.checkConnection()) {
	                                    BruteRemote.mIO.setFilename(IO.CMD_FILE2_FILENAME, IO.FILENAME_NONE);
	                                refreshData();
	                            }
	                        }
                        }
                    )
                    .setNegativeButton(R.string.button_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }
                    )
                    .show();
                }
            }
        });
    }

    /**
     * Called when the activity is resumed.
     */
    @Override
    public synchronized void onResume() {
        if (D) Log.e(TAG, "+ ON RESUME +");

        super.onResume();
        refreshData();
    }

    /**
     * Called when an activity returns with result.
     * @param requestCode  The original code passed to the activity.
     * @param resultCode   The result code returned from the activity.
     * @param data         The data returned from the activity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == FileBrowser.REQUEST_SELECT_FILE) {
            if (data.hasExtra(FileBrowser.EXTRA_FILENAME)) {
                final String filename = data.getExtras().getString(FileBrowser.EXTRA_FILENAME);
                final String parentPath = data.getExtras().getString(FileBrowser.EXTRA_PARENTPATH);
                final String fullPath = data.getExtras().getString(FileBrowser.EXTRA_FULLPATH);

                if ((filename != null) && !filename.equals("")) {
                    String message =
                        getResources().getString(R.string.message_open_file) + " " + filename +	"?";

                    new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle(R.string.title_file_confirm)
                        .setMessage(message)
                        .setPositiveButton(R.string.button_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // Save directory to preferences
                                    SharedPreferences.Editor editor = mPreferences.edit();
                                    editor.putString(KEY_PREF_DIRECTORY, parentPath);
                                    editor.commit();

                                    // Set filename and refresh page
                                    if (BruteRemote.mIO.checkConnection()) {
                                        if (BruteRemote.mIO.setFilename(IO.CMD_FILE2_FILENAME, fullPath)) {
                                            refreshData();
                                        }
                                        else {
                                            Resources res = getResources();
	
                                       	    new AlertDialog.Builder(File2.this)
                                   		.setIcon(android.R.drawable.ic_dialog_alert)
                            	    		.setTitle(res.getString(R.string.title_invalid_file))
                            	    		.setMessage(res.getString(R.string.message_invalid_file))
                            	    		.setNegativeButton(res.getString(R.string.button_close), null)
                            	    		.show();
                                        }
                                    }
                                }
                            }
                        )
                        .setNegativeButton(R.string.button_cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            }
                        )
                        .show();
                }
                else {
                    if (BruteRemote.mIO.checkConnection()) {
                        BruteRemote.mIO.setFilename(IO.CMD_FILE2_FILENAME, IO.FILENAME_NONE);
                    refreshData();
                }
            }
        }
    }
    }

    /**
     * Translate the level value to seekbar progress value.
     * The zero level value is the seekbar range / 2.
     * @param level  The level value.
     * @returns      The seekbar progress value.
     */
    private int toProgress(int level) {
        return level + (LEVEL_SEEKBAR_RANGE / 2);
    }

    /**
     * Translate the seekbar progress value to level value.
     * The zero level value is the seekbar range / 2.
     * @param progress  The seekbar progress value.
     * @returns         The level value.
     */
    private int toLevel(int progress) {
        return progress - (LEVEL_SEEKBAR_RANGE / 2);
    }

    /**
     * Retrieves data and refreshes the view.
     */
    private void refreshData() {
        if (BruteRemote.mIO.checkConnection()) {
            mButtonEnable.setChecked(BruteRemote.mIO.getEnable(IO.CMD_FILE2_ENABLE));
            mSeekBarLevel.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_FILE2_LEVEL)));

            TextView textViewFilename = (TextView)findViewById(R.id.textViewFilenameFile2);
            textViewFilename.setText(BruteRemote.mIO.getMetadata(IO.CMD_FILE2_FILENAME));

            TextView textViewMetadata = (TextView)findViewById(R.id.textViewMetadataFile2);
            textViewMetadata.setText(BruteRemote.mIO.getMetadata(IO.CMD_FILE2_METADATA));
		} else {
	    	Resources res = getResources();

        	new AlertDialog.Builder(this)
    		.setIcon(android.R.drawable.ic_dialog_alert)
    		.setTitle(res.getString(R.string.title_connection_error))
    		.setMessage(res.getString(R.string.message_connection_error))
    		.setNegativeButton(res.getString(R.string.button_close), null)
    		.show();		
		}
    }
}
