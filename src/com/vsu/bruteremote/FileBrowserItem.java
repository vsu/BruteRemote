/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

public class FileBrowserItem {
	// The text to display.
	private String mDisplayText;

	// The name of the file or subdirectory.
	private String mName;

	// The full path of the parent directory.
	private String mParentPath;

	// The full path of the file or subdirectory.
	private String mFullPath;

	// Indicates whether the item is a subdirectory.
	private boolean mIsSubdirectory;

	/**
	 * Constructor for the class.
	 * @param displayText     The text to display.
	 * @param name            The name of the file or subdirectory.
	 * @param parentPath      The full path of the parent directory.
	 * @param fullPath        The full path of the file or subdirectory.
	 * @param isSubDirectory  True if item is a subdirectory, false if a file.
	 */
	public FileBrowserItem(String displayText, String name, String parentPath,
			String fullPath, boolean isSubdirectory) {
		mDisplayText = displayText;
		mName = name;
		mParentPath = parentPath;
		mFullPath = fullPath;
		mIsSubdirectory = isSubdirectory;
	}

	/**
	 * Returns the text to display.
	 * @return  The display text.
	 */
	public String getDisplayText() {
		return mDisplayText;
	}

	/**
	 * Returns the name of the file or subdirectory.
	 * @return  The file or subdirectory name.
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Returns the full path of the parent directory.
	 * @return  The parent directory path.
	 */
	public String getParentPath() {
		return mParentPath;
	}

	/**
	 * Returns the full path of the file or subdirectory.
	 * @return  The file or subdirectory path.
	 */
	public String getFullPath() {
		return mFullPath;
	}

	/**
	 * Returns a value indicating whether the item is a subdirectory.
	 * @return  True if a subdirectory, false if a file.
	 */
	public boolean isSubdirectory() {
		return mIsSubdirectory;
	}
}
