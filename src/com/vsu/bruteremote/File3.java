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
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class File3 extends Fragment {
    // Debugging
    private static final String TAG = "File3";
    private static final boolean D = false;

    // The maximum level in dB.
    private static final int LEVEL_MAX_DB = 20;

    // The number of adjustment steps per dB for level.
    private static final int LEVEL_STEPS_PER_DB = 10;

    // The seekbar value range for level.
    private static final int LEVEL_SEEKBAR_RANGE = 2 * LEVEL_MAX_DB * LEVEL_STEPS_PER_DB;

    // The most recently used directory path.
    private static final String KEY_PREF_DIRECTORY = "file3_directory";

    // A reference to the enable button.
    private ToggleButton mButtonEnable;

    // A reference to the level seekbar.
    private SeekBar mSeekBarLevel;

    // A reference to shared preferences.
    private SharedPreferences mPreferences;

    /**
     * Called when the fragment is first created.
     * @param savedInstanceState  If the fragment is being re-created from a previous
     *                            saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (D) Log.e(TAG, "+ ON CREATE +");

        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    /**
     * Called when the fragment is requested to draw its user interface.
     * @param inflater   The LayoutInflater object that can be used to inflate
     *                   any views in the fragment.
     * @param container  If non-null, this is the parent view that the fragment's UI
     *                   should be attached to. The fragment should not add the view
     *                   itself, but this can be used to generate the LayoutParams of
     *                   the view.
     * @param savedInstanceState  If non-null, this fragment is being re-constructed
     *                            from a previous saved state as given here.
     */
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (D) Log.e(TAG, "+ ON CREATE VIEW +");
        View v = inflater.inflate(R.layout.file3, container, false);

        mButtonEnable = (ToggleButton)v.findViewById(R.id.toggleButtonEnableFile3);
        mButtonEnable.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BruteRemote.mIO.checkConnection()) {
                    BruteRemote.mIO.setEnable(IO.CMD_FILE3_ENABLE, mButtonEnable.isChecked());
                }
            }
        });

        final TextView textViewLevel = (TextView)v.findViewById(R.id.textViewLevelFile3);
        mSeekBarLevel = (SeekBar)v.findViewById(R.id.seekBarLevelFile3);

        mSeekBarLevel.setMax(LEVEL_SEEKBAR_RANGE);

        mSeekBarLevel.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double level = (double)toLevel(progress) / (double)LEVEL_STEPS_PER_DB;
                textViewLevel.setText(Double.toString(level) + " dB");

                if (fromUser) {
                    if (BruteRemote.mIO.checkConnection()) {
                        BruteRemote.mIO.setLevel(IO.CMD_FILE3_LEVEL, toLevel(progress));
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final Button buttonBrowse = (Button)v.findViewById(R.id.buttonBrowseFile3);
        buttonBrowse.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String directory = mPreferences.getString(KEY_PREF_DIRECTORY, "");

                Intent intent = new Intent(getActivity(), FileBrowser.class);
                intent.putExtra(FileBrowser.EXTRA_STARTPATH, directory);

                startActivityForResult(intent, FileBrowser.REQUEST_SELECT_FILE);
            }
        });

        final Button buttonDelete = (Button)v.findViewById(R.id.buttonDeleteFile3);
        buttonDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TextView textViewFilename =
                        (TextView)getActivity().findViewById(R.id.textViewFilenameFile3);

                if (!textViewFilename.getText().equals("")) {
                    new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle(R.string.title_file_confirm)
                    .setMessage(R.string.message_delete_file)
                    .setPositiveButton(R.string.button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (BruteRemote.mIO.checkConnection()) {
                                    BruteRemote.mIO.setFilename(IO.CMD_FILE3_FILENAME, IO.FILENAME_NONE);
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

        return v;
    }

    /**
     * Called when the fragment is resumed.
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

                    new AlertDialog.Builder(getActivity())
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
                                        BruteRemote.mIO.setFilename(IO.CMD_FILE3_FILENAME, fullPath);
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
                else {
                    if (BruteRemote.mIO.checkConnection()) {
                        BruteRemote.mIO.setFilename(IO.CMD_FILE3_FILENAME, IO.FILENAME_NONE);
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
            mButtonEnable.setChecked(BruteRemote.mIO.getEnable(IO.CMD_FILE3_ENABLE));
            mSeekBarLevel.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_FILE3_LEVEL)));

            TextView textViewFilename = (TextView)getActivity().findViewById(R.id.textViewFilenameFile3);
            textViewFilename.setText(BruteRemote.mIO.getMetadata(IO.CMD_FILE3_FILENAME));

            TextView textViewMetadata = (TextView)getActivity().findViewById(R.id.textViewMetadataFile3);
            textViewMetadata.setText(BruteRemote.mIO.getMetadata(IO.CMD_FILE3_METADATA));
        }
    }
}
