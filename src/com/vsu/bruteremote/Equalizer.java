/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Equalizer extends Activity {
	// Debugging
	private static final String TAG = "Equalizer";
	private static final boolean D = false;

	// The maximum level in dB.
	private static final int LEVEL_MAX_DB = 20;

	// The number of adjustment steps per dB for level.
	private static final int LEVEL_STEPS_PER_DB = 10;

	// The seekbar value range for level.
	private static final int LEVEL_SEEKBAR_RANGE = 2 * LEVEL_MAX_DB
			* LEVEL_STEPS_PER_DB;

	// Flag that indicates whether button magnitude controls are visible.
	private boolean mButtonsVisible = false;

	// A reference to the enable button.
	private ToggleButton mButtonEnable;

	// References to seekbar controls.
	private SeekBar mSeekBarLevel;
	private SeekBar mSeekBarMag1;
	private SeekBar mSeekBarMag2;
	private SeekBar mSeekBarMag3;
	private SeekBar mSeekBarMag4;
	private SeekBar mSeekBarMag5;
	private SeekBar mSeekBarMag6;
	private SeekBar mSeekBarMag7;
	private SeekBar mSeekBarMag8;
	private SeekBar mSeekBarMag9;
	private SeekBar mSeekBarMag10;
	private SeekBar mSeekBarMag11;
	private SeekBar mSeekBarMag12;
	private SeekBar mSeekBarMag13;
	private SeekBar mSeekBarMag14;
	private SeekBar mSeekBarMag15;
	private SeekBar mSeekBarMag16;
	private SeekBar mSeekBarMag17;
	private SeekBar mSeekBarMag18;
	private SeekBar mSeekBarMag19;
	private SeekBar mSeekBarMag20;
	private SeekBar mSeekBarMag21;
	private SeekBar mSeekBarMag22;
	private SeekBar mSeekBarMag23;
	private SeekBar mSeekBarMag24;
	private SeekBar mSeekBarMag25;
	private SeekBar mSeekBarMag26;
	private SeekBar mSeekBarMag27;
	private SeekBar mSeekBarMag28;
	private SeekBar mSeekBarMag29;
	private SeekBar mSeekBarMag30;
	private SeekBar mSeekBarMag31;

	// SeekBar to TextView label mapping.
	private Map<Object, Object> mSeekBar2TextView = new HashMap<Object, Object>();

	// Increment button to seekbar mapping.
	private Map<Object, Object> mIncrementButton2SeekBar = new HashMap<Object, Object>();

	// Decrement button to seekbar mapping.
	private Map<Object, Object> mDecrementButton2SeekBar = new HashMap<Object, Object>();

	// SeekBar to magnitude index mapping.
	private Map<Object, Object> mSeekBar2MagIndex = new HashMap<Object, Object>();

	/**
	 * Called when the activity is created.
	 * 
	 * @param savedInstanceState  If the activity is being re-created from a previous saved
	 *            			      state, this is the state.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (D)
			Log.e(TAG, "+ ON CREATE +");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.equalizer);

		// Populate seekbar to textview label map
		mSeekBar2TextView.put(R.id.seekBarLevelEq, R.id.textViewLevelEq);
		mSeekBar2TextView.put(R.id.seekBarMag1, R.id.textViewMag1);
		mSeekBar2TextView.put(R.id.seekBarMag2, R.id.textViewMag2);
		mSeekBar2TextView.put(R.id.seekBarMag3, R.id.textViewMag3);
		mSeekBar2TextView.put(R.id.seekBarMag4, R.id.textViewMag4);
		mSeekBar2TextView.put(R.id.seekBarMag5, R.id.textViewMag5);
		mSeekBar2TextView.put(R.id.seekBarMag6, R.id.textViewMag6);
		mSeekBar2TextView.put(R.id.seekBarMag7, R.id.textViewMag7);
		mSeekBar2TextView.put(R.id.seekBarMag8, R.id.textViewMag8);
		mSeekBar2TextView.put(R.id.seekBarMag9, R.id.textViewMag9);
		mSeekBar2TextView.put(R.id.seekBarMag10, R.id.textViewMag10);
		mSeekBar2TextView.put(R.id.seekBarMag11, R.id.textViewMag11);
		mSeekBar2TextView.put(R.id.seekBarMag12, R.id.textViewMag12);
		mSeekBar2TextView.put(R.id.seekBarMag13, R.id.textViewMag13);
		mSeekBar2TextView.put(R.id.seekBarMag14, R.id.textViewMag14);
		mSeekBar2TextView.put(R.id.seekBarMag15, R.id.textViewMag15);
		mSeekBar2TextView.put(R.id.seekBarMag16, R.id.textViewMag16);
		mSeekBar2TextView.put(R.id.seekBarMag17, R.id.textViewMag17);
		mSeekBar2TextView.put(R.id.seekBarMag18, R.id.textViewMag18);
		mSeekBar2TextView.put(R.id.seekBarMag19, R.id.textViewMag19);
		mSeekBar2TextView.put(R.id.seekBarMag20, R.id.textViewMag20);
		mSeekBar2TextView.put(R.id.seekBarMag21, R.id.textViewMag21);
		mSeekBar2TextView.put(R.id.seekBarMag22, R.id.textViewMag22);
		mSeekBar2TextView.put(R.id.seekBarMag23, R.id.textViewMag23);
		mSeekBar2TextView.put(R.id.seekBarMag24, R.id.textViewMag24);
		mSeekBar2TextView.put(R.id.seekBarMag25, R.id.textViewMag25);
		mSeekBar2TextView.put(R.id.seekBarMag26, R.id.textViewMag26);
		mSeekBar2TextView.put(R.id.seekBarMag27, R.id.textViewMag27);
		mSeekBar2TextView.put(R.id.seekBarMag28, R.id.textViewMag28);
		mSeekBar2TextView.put(R.id.seekBarMag29, R.id.textViewMag29);
		mSeekBar2TextView.put(R.id.seekBarMag30, R.id.textViewMag30);
		mSeekBar2TextView.put(R.id.seekBarMag31, R.id.textViewMag31);

		// Populate increment button to seekbar map
		mIncrementButton2SeekBar.put(R.id.buttonIncrementLevelEq, R.id.seekBarLevelEq);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag1, R.id.seekBarMag1);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag2, R.id.seekBarMag2);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag3, R.id.seekBarMag3);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag4, R.id.seekBarMag4);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag5, R.id.seekBarMag5);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag6, R.id.seekBarMag6);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag7, R.id.seekBarMag7);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag8, R.id.seekBarMag8);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag9, R.id.seekBarMag9);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag10,	R.id.seekBarMag10);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag11,	R.id.seekBarMag11);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag12, R.id.seekBarMag12);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag13,	R.id.seekBarMag13);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag14,	R.id.seekBarMag14);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag15, R.id.seekBarMag15);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag16,	R.id.seekBarMag16);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag17,	R.id.seekBarMag17);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag18,	R.id.seekBarMag18);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag19,	R.id.seekBarMag19);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag20,	R.id.seekBarMag20);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag21,	R.id.seekBarMag21);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag22,	R.id.seekBarMag22);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag23,	R.id.seekBarMag23);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag24,	R.id.seekBarMag24);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag25,	R.id.seekBarMag25);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag26,	R.id.seekBarMag26);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag27,	R.id.seekBarMag27);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag28,	R.id.seekBarMag28);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag29,	R.id.seekBarMag29);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag30,	R.id.seekBarMag30);
		mIncrementButton2SeekBar.put(R.id.buttonIncrementMag31,	R.id.seekBarMag31);

		// Populate decrement button to seekbar map
		mDecrementButton2SeekBar.put(R.id.buttonDecrementLevelEq, R.id.seekBarLevelEq);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag1, R.id.seekBarMag1);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag2, R.id.seekBarMag2);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag3, R.id.seekBarMag3);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag4, R.id.seekBarMag4);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag5, R.id.seekBarMag5);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag6, R.id.seekBarMag6);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag7, R.id.seekBarMag7);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag8, R.id.seekBarMag8);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag9, R.id.seekBarMag9);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag10,	R.id.seekBarMag10);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag11,	R.id.seekBarMag11);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag12,	R.id.seekBarMag12);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag13,	R.id.seekBarMag13);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag14,	R.id.seekBarMag14);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag15,	R.id.seekBarMag15);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag16,	R.id.seekBarMag16);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag17,	R.id.seekBarMag17);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag18,	R.id.seekBarMag18);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag19,	R.id.seekBarMag19);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag20,	R.id.seekBarMag20);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag21,	R.id.seekBarMag21);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag22,	R.id.seekBarMag22);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag23,	R.id.seekBarMag23);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag24,	R.id.seekBarMag24);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag25,	R.id.seekBarMag25);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag26,	R.id.seekBarMag26);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag27,	R.id.seekBarMag27);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag28,	R.id.seekBarMag28);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag29,	R.id.seekBarMag29);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag30,	R.id.seekBarMag30);
		mDecrementButton2SeekBar.put(R.id.buttonDecrementMag31,	R.id.seekBarMag31);

		// Populate seekbar to magnitude index map
		mSeekBar2MagIndex.put(R.id.seekBarMag1, "0");
		mSeekBar2MagIndex.put(R.id.seekBarMag2, "1");
		mSeekBar2MagIndex.put(R.id.seekBarMag3, "2");
		mSeekBar2MagIndex.put(R.id.seekBarMag4, "3");
		mSeekBar2MagIndex.put(R.id.seekBarMag5, "4");
		mSeekBar2MagIndex.put(R.id.seekBarMag6, "5");
		mSeekBar2MagIndex.put(R.id.seekBarMag7, "6");
		mSeekBar2MagIndex.put(R.id.seekBarMag8, "7");
		mSeekBar2MagIndex.put(R.id.seekBarMag9, "8");
		mSeekBar2MagIndex.put(R.id.seekBarMag10, "9");
		mSeekBar2MagIndex.put(R.id.seekBarMag11, "10");
		mSeekBar2MagIndex.put(R.id.seekBarMag12, "11");
		mSeekBar2MagIndex.put(R.id.seekBarMag13, "12");
		mSeekBar2MagIndex.put(R.id.seekBarMag14, "13");
		mSeekBar2MagIndex.put(R.id.seekBarMag15, "14");
		mSeekBar2MagIndex.put(R.id.seekBarMag16, "15");
		mSeekBar2MagIndex.put(R.id.seekBarMag17, "16");
		mSeekBar2MagIndex.put(R.id.seekBarMag18, "17");
		mSeekBar2MagIndex.put(R.id.seekBarMag19, "18");
		mSeekBar2MagIndex.put(R.id.seekBarMag20, "19");
		mSeekBar2MagIndex.put(R.id.seekBarMag21, "20");
		mSeekBar2MagIndex.put(R.id.seekBarMag22, "21");
		mSeekBar2MagIndex.put(R.id.seekBarMag23, "22");
		mSeekBar2MagIndex.put(R.id.seekBarMag24, "23");
		mSeekBar2MagIndex.put(R.id.seekBarMag25, "24");
		mSeekBar2MagIndex.put(R.id.seekBarMag26, "25");
		mSeekBar2MagIndex.put(R.id.seekBarMag27, "26");
		mSeekBar2MagIndex.put(R.id.seekBarMag28, "27");
		mSeekBar2MagIndex.put(R.id.seekBarMag29, "28");
		mSeekBar2MagIndex.put(R.id.seekBarMag30, "29");
		mSeekBar2MagIndex.put(R.id.seekBarMag31, "30");

		mSeekBarLevel = (SeekBar) findViewById(R.id.seekBarLevelEq);
		mSeekBarMag1 = (SeekBar) findViewById(R.id.seekBarMag1);
		mSeekBarMag2 = (SeekBar) findViewById(R.id.seekBarMag2);
		mSeekBarMag3 = (SeekBar) findViewById(R.id.seekBarMag3);
		mSeekBarMag4 = (SeekBar) findViewById(R.id.seekBarMag4);
		mSeekBarMag5 = (SeekBar) findViewById(R.id.seekBarMag5);
		mSeekBarMag6 = (SeekBar) findViewById(R.id.seekBarMag6);
		mSeekBarMag7 = (SeekBar) findViewById(R.id.seekBarMag7);
		mSeekBarMag8 = (SeekBar) findViewById(R.id.seekBarMag8);
		mSeekBarMag9 = (SeekBar) findViewById(R.id.seekBarMag9);
		mSeekBarMag10 = (SeekBar) findViewById(R.id.seekBarMag10);
		mSeekBarMag11 = (SeekBar) findViewById(R.id.seekBarMag11);
		mSeekBarMag12 = (SeekBar) findViewById(R.id.seekBarMag12);
		mSeekBarMag13 = (SeekBar) findViewById(R.id.seekBarMag13);
		mSeekBarMag14 = (SeekBar) findViewById(R.id.seekBarMag14);
		mSeekBarMag15 = (SeekBar) findViewById(R.id.seekBarMag15);
		mSeekBarMag16 = (SeekBar) findViewById(R.id.seekBarMag16);
		mSeekBarMag17 = (SeekBar) findViewById(R.id.seekBarMag17);
		mSeekBarMag18 = (SeekBar) findViewById(R.id.seekBarMag18);
		mSeekBarMag19 = (SeekBar) findViewById(R.id.seekBarMag19);
		mSeekBarMag20 = (SeekBar) findViewById(R.id.seekBarMag20);
		mSeekBarMag21 = (SeekBar) findViewById(R.id.seekBarMag21);
		mSeekBarMag22 = (SeekBar) findViewById(R.id.seekBarMag22);
		mSeekBarMag23 = (SeekBar) findViewById(R.id.seekBarMag23);
		mSeekBarMag24 = (SeekBar) findViewById(R.id.seekBarMag24);
		mSeekBarMag25 = (SeekBar) findViewById(R.id.seekBarMag25);
		mSeekBarMag26 = (SeekBar) findViewById(R.id.seekBarMag26);
		mSeekBarMag27 = (SeekBar) findViewById(R.id.seekBarMag27);
		mSeekBarMag28 = (SeekBar) findViewById(R.id.seekBarMag28);
		mSeekBarMag29 = (SeekBar) findViewById(R.id.seekBarMag29);
		mSeekBarMag30 = (SeekBar) findViewById(R.id.seekBarMag30);
		mSeekBarMag31 = (SeekBar) findViewById(R.id.seekBarMag31);

		final Button buttonIncrementLevel = (Button) findViewById(R.id.buttonIncrementLevelEq);
		final Button buttonIncrementMag1 = (Button) findViewById(R.id.buttonIncrementMag1);
		final Button buttonIncrementMag2 = (Button) findViewById(R.id.buttonIncrementMag2);
		final Button buttonIncrementMag3 = (Button) findViewById(R.id.buttonIncrementMag3);
		final Button buttonIncrementMag4 = (Button) findViewById(R.id.buttonIncrementMag4);
		final Button buttonIncrementMag5 = (Button) findViewById(R.id.buttonIncrementMag5);
		final Button buttonIncrementMag6 = (Button) findViewById(R.id.buttonIncrementMag6);
		final Button buttonIncrementMag7 = (Button) findViewById(R.id.buttonIncrementMag7);
		final Button buttonIncrementMag8 = (Button) findViewById(R.id.buttonIncrementMag8);
		final Button buttonIncrementMag9 = (Button) findViewById(R.id.buttonIncrementMag9);
		final Button buttonIncrementMag10 = (Button) findViewById(R.id.buttonIncrementMag10);
		final Button buttonIncrementMag11 = (Button) findViewById(R.id.buttonIncrementMag11);
		final Button buttonIncrementMag12 = (Button) findViewById(R.id.buttonIncrementMag12);
		final Button buttonIncrementMag13 = (Button) findViewById(R.id.buttonIncrementMag13);
		final Button buttonIncrementMag14 = (Button) findViewById(R.id.buttonIncrementMag14);
		final Button buttonIncrementMag15 = (Button) findViewById(R.id.buttonIncrementMag15);
		final Button buttonIncrementMag16 = (Button) findViewById(R.id.buttonIncrementMag16);
		final Button buttonIncrementMag17 = (Button) findViewById(R.id.buttonIncrementMag17);
		final Button buttonIncrementMag18 = (Button) findViewById(R.id.buttonIncrementMag18);
		final Button buttonIncrementMag19 = (Button) findViewById(R.id.buttonIncrementMag19);
		final Button buttonIncrementMag20 = (Button) findViewById(R.id.buttonIncrementMag20);
		final Button buttonIncrementMag21 = (Button) findViewById(R.id.buttonIncrementMag21);
		final Button buttonIncrementMag22 = (Button) findViewById(R.id.buttonIncrementMag22);
		final Button buttonIncrementMag23 = (Button) findViewById(R.id.buttonIncrementMag23);
		final Button buttonIncrementMag24 = (Button) findViewById(R.id.buttonIncrementMag24);
		final Button buttonIncrementMag25 = (Button) findViewById(R.id.buttonIncrementMag25);
		final Button buttonIncrementMag26 = (Button) findViewById(R.id.buttonIncrementMag26);
		final Button buttonIncrementMag27 = (Button) findViewById(R.id.buttonIncrementMag27);
		final Button buttonIncrementMag28 = (Button) findViewById(R.id.buttonIncrementMag28);
		final Button buttonIncrementMag29 = (Button) findViewById(R.id.buttonIncrementMag29);
		final Button buttonIncrementMag30 = (Button) findViewById(R.id.buttonIncrementMag30);
		final Button buttonIncrementMag31 = (Button) findViewById(R.id.buttonIncrementMag31);

		final Button buttonDecrementLevel = (Button) findViewById(R.id.buttonDecrementLevelEq);
		final Button buttonDecrementMag1 = (Button) findViewById(R.id.buttonDecrementMag1);
		final Button buttonDecrementMag2 = (Button) findViewById(R.id.buttonDecrementMag2);
		final Button buttonDecrementMag3 = (Button) findViewById(R.id.buttonDecrementMag3);
		final Button buttonDecrementMag4 = (Button) findViewById(R.id.buttonDecrementMag4);
		final Button buttonDecrementMag5 = (Button) findViewById(R.id.buttonDecrementMag5);
		final Button buttonDecrementMag6 = (Button) findViewById(R.id.buttonDecrementMag6);
		final Button buttonDecrementMag7 = (Button) findViewById(R.id.buttonDecrementMag7);
		final Button buttonDecrementMag8 = (Button) findViewById(R.id.buttonDecrementMag8);
		final Button buttonDecrementMag9 = (Button) findViewById(R.id.buttonDecrementMag9);
		final Button buttonDecrementMag10 = (Button) findViewById(R.id.buttonDecrementMag10);
		final Button buttonDecrementMag11 = (Button) findViewById(R.id.buttonDecrementMag11);
		final Button buttonDecrementMag12 = (Button) findViewById(R.id.buttonDecrementMag12);
		final Button buttonDecrementMag13 = (Button) findViewById(R.id.buttonDecrementMag13);
		final Button buttonDecrementMag14 = (Button) findViewById(R.id.buttonDecrementMag14);
		final Button buttonDecrementMag15 = (Button) findViewById(R.id.buttonDecrementMag15);
		final Button buttonDecrementMag16 = (Button) findViewById(R.id.buttonDecrementMag16);
		final Button buttonDecrementMag17 = (Button) findViewById(R.id.buttonDecrementMag17);
		final Button buttonDecrementMag18 = (Button) findViewById(R.id.buttonDecrementMag18);
		final Button buttonDecrementMag19 = (Button) findViewById(R.id.buttonDecrementMag19);
		final Button buttonDecrementMag20 = (Button) findViewById(R.id.buttonDecrementMag20);
		final Button buttonDecrementMag21 = (Button) findViewById(R.id.buttonDecrementMag21);
		final Button buttonDecrementMag22 = (Button) findViewById(R.id.buttonDecrementMag22);
		final Button buttonDecrementMag23 = (Button) findViewById(R.id.buttonDecrementMag23);
		final Button buttonDecrementMag24 = (Button) findViewById(R.id.buttonDecrementMag24);
		final Button buttonDecrementMag25 = (Button) findViewById(R.id.buttonDecrementMag25);
		final Button buttonDecrementMag26 = (Button) findViewById(R.id.buttonDecrementMag26);
		final Button buttonDecrementMag27 = (Button) findViewById(R.id.buttonDecrementMag27);
		final Button buttonDecrementMag28 = (Button) findViewById(R.id.buttonDecrementMag28);
		final Button buttonDecrementMag29 = (Button) findViewById(R.id.buttonDecrementMag29);
		final Button buttonDecrementMag30 = (Button) findViewById(R.id.buttonDecrementMag30);
		final Button buttonDecrementMag31 = (Button) findViewById(R.id.buttonDecrementMag31);

		mSeekBarLevel.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag1.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag2.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag3.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag4.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag5.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag6.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag7.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag8.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag9.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag10.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag11.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag12.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag13.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag14.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag15.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag16.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag17.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag18.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag19.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag20.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag21.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag22.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag23.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag24.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag25.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag26.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag27.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag28.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag29.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag30.setMax(LEVEL_SEEKBAR_RANGE);
		mSeekBarMag31.setMax(LEVEL_SEEKBAR_RANGE);

		mSeekBarLevel.setProgress(toProgress(0));
		mSeekBarMag1.setProgress(toProgress(0));
		mSeekBarMag2.setProgress(toProgress(0));
		mSeekBarMag3.setProgress(toProgress(0));
		mSeekBarMag4.setProgress(toProgress(0));
		mSeekBarMag5.setProgress(toProgress(0));
		mSeekBarMag6.setProgress(toProgress(0));
		mSeekBarMag7.setProgress(toProgress(0));
		mSeekBarMag8.setProgress(toProgress(0));
		mSeekBarMag9.setProgress(toProgress(0));
		mSeekBarMag10.setProgress(toProgress(0));
		mSeekBarMag11.setProgress(toProgress(0));
		mSeekBarMag12.setProgress(toProgress(0));
		mSeekBarMag13.setProgress(toProgress(0));
		mSeekBarMag14.setProgress(toProgress(0));
		mSeekBarMag15.setProgress(toProgress(0));
		mSeekBarMag16.setProgress(toProgress(0));
		mSeekBarMag17.setProgress(toProgress(0));
		mSeekBarMag18.setProgress(toProgress(0));
		mSeekBarMag19.setProgress(toProgress(0));
		mSeekBarMag20.setProgress(toProgress(0));
		mSeekBarMag21.setProgress(toProgress(0));
		mSeekBarMag22.setProgress(toProgress(0));
		mSeekBarMag23.setProgress(toProgress(0));
		mSeekBarMag24.setProgress(toProgress(0));
		mSeekBarMag25.setProgress(toProgress(0));
		mSeekBarMag26.setProgress(toProgress(0));
		mSeekBarMag27.setProgress(toProgress(0));
		mSeekBarMag28.setProgress(toProgress(0));
		mSeekBarMag29.setProgress(toProgress(0));
		mSeekBarMag30.setProgress(toProgress(0));
		mSeekBarMag31.setProgress(toProgress(0));
		
		mSeekBarLevel.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag1.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag2.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag3.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag4.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag5.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag6.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag7.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag8.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag9.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag10.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag11.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag12.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag13.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag14.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag15.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag16.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag17.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag18.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag19.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag20.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag21.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag22.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag23.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag24.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag25.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag26.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag27.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag28.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag29.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag30.setOnSeekBarChangeListener(seekBarChangeListener);
		mSeekBarMag31.setOnSeekBarChangeListener(seekBarChangeListener);

		buttonIncrementLevel.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag1.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag2.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag3.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag4.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag5.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag6.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag7.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag8.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag9.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag10.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag11.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag12.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag13.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag14.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag15.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag16.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag17.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag18.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag19.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag20.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag21.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag22.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag23.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag24.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag25.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag26.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag27.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag28.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag29.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag30.setOnClickListener(buttonIncrementClickListener);
		buttonIncrementMag31.setOnClickListener(buttonIncrementClickListener);

		buttonDecrementLevel.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag1.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag2.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag3.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag4.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag5.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag6.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag7.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag8.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag9.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag10.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag11.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag12.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag13.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag14.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag15.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag16.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag17.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag18.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag19.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag20.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag21.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag22.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag23.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag24.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag25.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag26.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag27.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag28.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag29.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag30.setOnClickListener(buttonDecrementClickListener);
		buttonDecrementMag31.setOnClickListener(buttonDecrementClickListener);

		mButtonEnable = (ToggleButton) findViewById(R.id.toggleButtonEnableEq);
		mButtonEnable.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (BruteRemote.mIO.checkConnection()) {
					BruteRemote.mIO.setEnable(IO.CMD_EQ_ENABLE,	mButtonEnable.isChecked());
				}
			}
		});

		final Button buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonReset.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int zeroProgress = LEVEL_SEEKBAR_RANGE / 2;

				mSeekBarLevel.setProgress(zeroProgress);
				mSeekBarMag1.setProgress(zeroProgress);
				mSeekBarMag2.setProgress(zeroProgress);
				mSeekBarMag3.setProgress(zeroProgress);
				mSeekBarMag4.setProgress(zeroProgress);
				mSeekBarMag5.setProgress(zeroProgress);
				mSeekBarMag6.setProgress(zeroProgress);
				mSeekBarMag7.setProgress(zeroProgress);
				mSeekBarMag8.setProgress(zeroProgress);
				mSeekBarMag9.setProgress(zeroProgress);
				mSeekBarMag10.setProgress(zeroProgress);
				mSeekBarMag11.setProgress(zeroProgress);
				mSeekBarMag12.setProgress(zeroProgress);
				mSeekBarMag13.setProgress(zeroProgress);
				mSeekBarMag14.setProgress(zeroProgress);
				mSeekBarMag15.setProgress(zeroProgress);
				mSeekBarMag16.setProgress(zeroProgress);
				mSeekBarMag17.setProgress(zeroProgress);
				mSeekBarMag18.setProgress(zeroProgress);
				mSeekBarMag19.setProgress(zeroProgress);
				mSeekBarMag20.setProgress(zeroProgress);
				mSeekBarMag21.setProgress(zeroProgress);
				mSeekBarMag22.setProgress(zeroProgress);
				mSeekBarMag23.setProgress(zeroProgress);
				mSeekBarMag24.setProgress(zeroProgress);
				mSeekBarMag25.setProgress(zeroProgress);
				mSeekBarMag26.setProgress(zeroProgress);
				mSeekBarMag27.setProgress(zeroProgress);
				mSeekBarMag28.setProgress(zeroProgress);
				mSeekBarMag29.setProgress(zeroProgress);
				mSeekBarMag30.setProgress(zeroProgress);
				mSeekBarMag31.setProgress(zeroProgress);

				if (BruteRemote.mIO.checkConnection()) {
					BruteRemote.mIO.setLevel(IO.CMD_EQ_LEVEL, toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "0", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "1", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "2", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "3", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "4", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "5", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "6", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "7", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "8", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "9", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "10", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "11", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "12", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "13", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "14", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "15", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "16", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "17", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "18", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "19", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "20", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "21", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "22", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "23", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "24", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "25", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "26", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "27", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "28", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "29", toLevel(zeroProgress));
					BruteRemote.mIO.setLevel(IO.CMD_EQ_MAG + "30", toLevel(zeroProgress));
				}
			}
		});

		final Button buttonView = (Button) findViewById(R.id.buttonView);
		buttonView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mButtonsVisible) {
					mSeekBarLevel.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag1.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag2.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag3.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag4.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag5.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag6.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag7.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag8.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag9.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag10.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag11.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag12.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag13.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag14.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag15.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag16.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag17.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag18.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag19.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag20.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag21.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag22.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag23.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag24.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag25.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag26.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag27.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag28.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag29.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag30.setVisibility(android.view.View.VISIBLE);
					mSeekBarMag31.setVisibility(android.view.View.VISIBLE);

					buttonIncrementLevel.setVisibility(android.view.View.GONE);
					buttonIncrementMag1.setVisibility(android.view.View.GONE);
					buttonIncrementMag2.setVisibility(android.view.View.GONE);
					buttonIncrementMag3.setVisibility(android.view.View.GONE);
					buttonIncrementMag4.setVisibility(android.view.View.GONE);
					buttonIncrementMag5.setVisibility(android.view.View.GONE);
					buttonIncrementMag6.setVisibility(android.view.View.GONE);
					buttonIncrementMag7.setVisibility(android.view.View.GONE);
					buttonIncrementMag8.setVisibility(android.view.View.GONE);
					buttonIncrementMag9.setVisibility(android.view.View.GONE);
					buttonIncrementMag10.setVisibility(android.view.View.GONE);
					buttonIncrementMag11.setVisibility(android.view.View.GONE);
					buttonIncrementMag12.setVisibility(android.view.View.GONE);
					buttonIncrementMag13.setVisibility(android.view.View.GONE);
					buttonIncrementMag14.setVisibility(android.view.View.GONE);
					buttonIncrementMag15.setVisibility(android.view.View.GONE);
					buttonIncrementMag16.setVisibility(android.view.View.GONE);
					buttonIncrementMag17.setVisibility(android.view.View.GONE);
					buttonIncrementMag18.setVisibility(android.view.View.GONE);
					buttonIncrementMag19.setVisibility(android.view.View.GONE);
					buttonIncrementMag20.setVisibility(android.view.View.GONE);
					buttonIncrementMag21.setVisibility(android.view.View.GONE);
					buttonIncrementMag22.setVisibility(android.view.View.GONE);
					buttonIncrementMag23.setVisibility(android.view.View.GONE);
					buttonIncrementMag24.setVisibility(android.view.View.GONE);
					buttonIncrementMag25.setVisibility(android.view.View.GONE);
					buttonIncrementMag26.setVisibility(android.view.View.GONE);
					buttonIncrementMag27.setVisibility(android.view.View.GONE);
					buttonIncrementMag28.setVisibility(android.view.View.GONE);
					buttonIncrementMag29.setVisibility(android.view.View.GONE);
					buttonIncrementMag30.setVisibility(android.view.View.GONE);
					buttonIncrementMag31.setVisibility(android.view.View.GONE);

					buttonDecrementLevel.setVisibility(android.view.View.GONE);
					buttonDecrementMag1.setVisibility(android.view.View.GONE);
					buttonDecrementMag2.setVisibility(android.view.View.GONE);
					buttonDecrementMag3.setVisibility(android.view.View.GONE);
					buttonDecrementMag4.setVisibility(android.view.View.GONE);
					buttonDecrementMag5.setVisibility(android.view.View.GONE);
					buttonDecrementMag6.setVisibility(android.view.View.GONE);
					buttonDecrementMag7.setVisibility(android.view.View.GONE);
					buttonDecrementMag8.setVisibility(android.view.View.GONE);
					buttonDecrementMag9.setVisibility(android.view.View.GONE);
					buttonDecrementMag10.setVisibility(android.view.View.GONE);
					buttonDecrementMag11.setVisibility(android.view.View.GONE);
					buttonDecrementMag12.setVisibility(android.view.View.GONE);
					buttonDecrementMag13.setVisibility(android.view.View.GONE);
					buttonDecrementMag14.setVisibility(android.view.View.GONE);
					buttonDecrementMag15.setVisibility(android.view.View.GONE);
					buttonDecrementMag16.setVisibility(android.view.View.GONE);
					buttonDecrementMag17.setVisibility(android.view.View.GONE);
					buttonDecrementMag18.setVisibility(android.view.View.GONE);
					buttonDecrementMag19.setVisibility(android.view.View.GONE);
					buttonDecrementMag20.setVisibility(android.view.View.GONE);
					buttonDecrementMag21.setVisibility(android.view.View.GONE);
					buttonDecrementMag22.setVisibility(android.view.View.GONE);
					buttonDecrementMag23.setVisibility(android.view.View.GONE);
					buttonDecrementMag24.setVisibility(android.view.View.GONE);
					buttonDecrementMag25.setVisibility(android.view.View.GONE);
					buttonDecrementMag26.setVisibility(android.view.View.GONE);
					buttonDecrementMag27.setVisibility(android.view.View.GONE);
					buttonDecrementMag28.setVisibility(android.view.View.GONE);
					buttonDecrementMag29.setVisibility(android.view.View.GONE);
					buttonDecrementMag30.setVisibility(android.view.View.GONE);
					buttonDecrementMag31.setVisibility(android.view.View.GONE);
				} else {
					mSeekBarLevel.setVisibility(android.view.View.GONE);
					mSeekBarMag1.setVisibility(android.view.View.GONE);
					mSeekBarMag2.setVisibility(android.view.View.GONE);
					mSeekBarMag3.setVisibility(android.view.View.GONE);
					mSeekBarMag4.setVisibility(android.view.View.GONE);
					mSeekBarMag5.setVisibility(android.view.View.GONE);
					mSeekBarMag6.setVisibility(android.view.View.GONE);
					mSeekBarMag7.setVisibility(android.view.View.GONE);
					mSeekBarMag8.setVisibility(android.view.View.GONE);
					mSeekBarMag9.setVisibility(android.view.View.GONE);
					mSeekBarMag10.setVisibility(android.view.View.GONE);
					mSeekBarMag11.setVisibility(android.view.View.GONE);
					mSeekBarMag12.setVisibility(android.view.View.GONE);
					mSeekBarMag13.setVisibility(android.view.View.GONE);
					mSeekBarMag14.setVisibility(android.view.View.GONE);
					mSeekBarMag15.setVisibility(android.view.View.GONE);
					mSeekBarMag16.setVisibility(android.view.View.GONE);
					mSeekBarMag17.setVisibility(android.view.View.GONE);
					mSeekBarMag18.setVisibility(android.view.View.GONE);
					mSeekBarMag19.setVisibility(android.view.View.GONE);
					mSeekBarMag20.setVisibility(android.view.View.GONE);
					mSeekBarMag21.setVisibility(android.view.View.GONE);
					mSeekBarMag22.setVisibility(android.view.View.GONE);
					mSeekBarMag23.setVisibility(android.view.View.GONE);
					mSeekBarMag24.setVisibility(android.view.View.GONE);
					mSeekBarMag25.setVisibility(android.view.View.GONE);
					mSeekBarMag26.setVisibility(android.view.View.GONE);
					mSeekBarMag27.setVisibility(android.view.View.GONE);
					mSeekBarMag28.setVisibility(android.view.View.GONE);
					mSeekBarMag29.setVisibility(android.view.View.GONE);
					mSeekBarMag30.setVisibility(android.view.View.GONE);
					mSeekBarMag31.setVisibility(android.view.View.GONE);

					buttonIncrementLevel.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag1.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag2.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag3.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag4.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag5.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag6.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag7.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag8.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag9.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag10.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag11.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag12.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag13.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag14.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag15.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag16.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag17.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag18.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag19.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag20.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag21.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag22.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag23.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag24.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag25.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag26.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag27.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag28.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag29.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag30.setVisibility(android.view.View.VISIBLE);
					buttonIncrementMag31.setVisibility(android.view.View.VISIBLE);

					buttonDecrementLevel.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag1.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag2.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag3.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag4.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag5.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag6.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag7.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag8.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag9.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag10.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag11.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag12.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag13.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag14.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag15.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag16.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag17.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag18.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag19.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag20.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag21.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag22.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag23.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag24.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag25.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag26.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag27.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag28.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag29.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag30.setVisibility(android.view.View.VISIBLE);
					buttonDecrementMag31.setVisibility(android.view.View.VISIBLE);
				}

				mButtonsVisible = !mButtonsVisible;
			}
		});
	}

	/**
	 * Called when the activity is resumed.
	 */
	@Override
	public synchronized void onResume() {
		if (D)
			Log.e(TAG, "+ ON RESUME +");

		super.onResume();
		refreshData();
	}

	/**
	 * Seekbar change handler.
	 */
	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			int textViewId = (Integer) mSeekBar2TextView.get(seekBar.getId());

			TextView textView = (TextView) findViewById(textViewId);
			updateDbLabel(textView, progress);

			if (fromUser) {
				if (BruteRemote.mIO.checkConnection()) {
					if (seekBar.getId() == R.id.seekBarLevelEq) {
						BruteRemote.mIO.setLevel(IO.CMD_EQ_LEVEL, toLevel(progress));
					} else {
						BruteRemote.mIO.setLevel(
								IO.CMD_EQ_MAG + (String) mSeekBar2MagIndex.get(seekBar.getId()),
								toLevel(progress));
					}
				}
			}
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	};

	/**
	 * Increment button click handler.
	 */
	private OnClickListener buttonIncrementClickListener = new OnClickListener() {
		public void onClick(View v) {
			int seekBarId = (Integer) mIncrementButton2SeekBar.get(v.getId());
			int textViewId = (Integer) mSeekBar2TextView.get(seekBarId);

			SeekBar seekBar = (SeekBar) findViewById(seekBarId);
			TextView textView = (TextView) findViewById(textViewId);

			incrementSeekBar(seekBar, textView);
		}
	};

	/**
	 * Decrement button click handler.
	 */
	private OnClickListener buttonDecrementClickListener = new OnClickListener() {
		public void onClick(View v) {
			int seekBarId = (Integer) mDecrementButton2SeekBar.get(v.getId());
			int textViewId = (Integer) mSeekBar2TextView.get(seekBarId);

			SeekBar seekBar = (SeekBar) findViewById(seekBarId);
			TextView textView = (TextView) findViewById(textViewId);

			decrementSeekBar(seekBar, textView);
		}
	};

	/**
	 * Updates the specified dB level label.
	 * 
	 * @param textViewLabel	 The textview label to update.
	 * @param progress       The seekbar progress level.
	 */
	private void updateDbLabel(TextView textViewLabel, int progress) {
		double dBLevel = (double) toLevel(progress)	/ (double) LEVEL_STEPS_PER_DB;
		textViewLabel.setText(Double.toString(dBLevel) + " dB");
	}

	/**
	 * Increments the specified seekbar and update label.
	 * 
	 * @param seekBar        The seekbar to increment.
	 * @param textViewLabel  The textview label to update.
	 */
	private void incrementSeekBar(SeekBar seekBar, TextView textViewLabel) {
		int progress = seekBar.getProgress();

		if (progress < LEVEL_SEEKBAR_RANGE) {
			progress++;
			seekBar.setProgress(progress);
			updateDbLabel(textViewLabel, progress);

			if (BruteRemote.mIO.checkConnection()) {
				if (seekBar.getId() == R.id.seekBarLevelEq) {
					BruteRemote.mIO.setLevel(IO.CMD_EQ_LEVEL, toLevel(progress));
				} else {
					BruteRemote.mIO.setLevel(
							IO.CMD_EQ_MAG + (String) mSeekBar2MagIndex.get(seekBar.getId()),
							toLevel(progress));
				}
			}
		}
	}

	/**
	 * Decrements the specified seekbar and update label.
	 * 
	 * @param seekBar        The seekbar to decrement.
	 * @param textViewLabel  The textview label to update.
	 */
	private void decrementSeekBar(SeekBar seekBar, TextView textViewLabel) {
		int progress = seekBar.getProgress();

		if (progress > 0) {
			progress--;
			seekBar.setProgress(progress);
			updateDbLabel(textViewLabel, progress);

			if (BruteRemote.mIO.checkConnection()) {
				if (seekBar.getId() == R.id.seekBarLevelEq) {
					BruteRemote.mIO.setLevel(IO.CMD_EQ_LEVEL, toLevel(progress));
				} else {
					BruteRemote.mIO.setLevel(
							IO.CMD_EQ_MAG + (String) mSeekBar2MagIndex.get(seekBar.getId()),
							toLevel(progress));
				}
			}
		}
	}

	/**
	 * Translate the level value to seekbar progress value. The zero level value
	 * is the seekbar range / 2.
	 * 
	 * @param level  The level value.
	 * @returns The seekbar progress value.
	 */
	private int toProgress(int level) {
		return level + (LEVEL_SEEKBAR_RANGE / 2);
	}

	/**
	 * Translate the seekbar progress value to level value. The zero level value
	 * is the seekbar range / 2.
	 * 
	 * @param progress  The seekbar progress value.
	 * @returns The level value.
	 */
	private int toLevel(int progress) {
		return progress - (LEVEL_SEEKBAR_RANGE / 2);
	}

	/**
	 * Retrieve data and refresh the view.
	 */
	private void refreshData() {
		if (BruteRemote.mIO.checkConnection()) {
			mButtonEnable.setChecked(BruteRemote.mIO.getEnable(IO.CMD_EQ_ENABLE));
			mSeekBarLevel.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_LEVEL)));
			mSeekBarMag1.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "0")));
			mSeekBarMag2.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "1")));
			mSeekBarMag3.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "2")));
			mSeekBarMag4.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "3")));
			mSeekBarMag5.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "4")));
			mSeekBarMag6.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "5")));
			mSeekBarMag7.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "6")));
			mSeekBarMag8.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "7")));
			mSeekBarMag9.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "8")));
			mSeekBarMag10.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "9")));
			mSeekBarMag11.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "10")));
			mSeekBarMag12.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "11")));
			mSeekBarMag13.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "12")));
			mSeekBarMag14.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "13")));
			mSeekBarMag15.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "14")));
			mSeekBarMag16.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "15")));
			mSeekBarMag17.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "16")));
			mSeekBarMag18.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "17")));
			mSeekBarMag19.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "18")));
			mSeekBarMag20.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "19")));
			mSeekBarMag21.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "20")));
			mSeekBarMag22.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "21")));
			mSeekBarMag23.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "22")));
			mSeekBarMag24.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "23")));
			mSeekBarMag25.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "24")));
			mSeekBarMag26.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "25")));
			mSeekBarMag27.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "26")));
			mSeekBarMag28.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "27")));
			mSeekBarMag29.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "28")));
			mSeekBarMag30.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "29")));
			mSeekBarMag31.setProgress(toProgress(BruteRemote.mIO.getLevel(IO.CMD_EQ_MAG + "30")));
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
