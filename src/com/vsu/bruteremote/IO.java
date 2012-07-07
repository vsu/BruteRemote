/*
 * Copyright (c) 2011 Victor Su
 *
 * This program is open source. For license terms, see the LICENSE file.
 *
 */

package com.vsu.bruteremote;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class IO {
    // Debugging
    private static final String TAG = "IO";
    private static final boolean D = false;

	// Command to control equalizer magnitudes.
	public static final String CMD_EQ_MAG = "EQM";

	// Commands to control enable settings.
	public static final String CMD_EQ_ENABLE = "EQEN";
	public static final String CMD_FILE1_ENABLE = "F1EN";
	public static final String CMD_FILE2_ENABLE = "F2EN";
	public static final String CMD_FILE3_ENABLE = "F3EN";

	// Commands to control level settings.
	public static final String CMD_EQ_LEVEL = "EQLV";
	public static final String CMD_FILE1_LEVEL = "F1LV";
	public static final String CMD_FILE2_LEVEL = "F2LV";
	public static final String CMD_FILE3_LEVEL = "F3LV";

	// Commands to control filename settings.
	public static final String CMD_FILE1_FILENAME = "F1FN";
	public static final String CMD_FILE2_FILENAME = "F2FN";
	public static final String CMD_FILE3_FILENAME = "F3FN";

	// Commands to get metadata settings.
	public static final String CMD_FILE1_METADATA = "F1MD";
	public static final String CMD_FILE2_METADATA = "F2MD";
	public static final String CMD_FILE3_METADATA = "F3MD";

	// Command to get directory listing.
	public static final String CMD_LIST_DIR = "DIR";

	// Command to close the connection.
	public static final String CMD_CLOSE_CONN = "CLOSE";

	// The special filename indicating no file.
	public static final String FILENAME_NONE = "?";

	// The command terminator string.
	private static final String CMD_TERM = "\r";

	// The command-data delimiter string.
	private static final String CMD_DELIM = " ";

	// The operation successful status string.
	private static final String STATUS_OK = "OK";

	// The operation failed status string.
	private static final String STATUS_ERROR = "ERR";

    // Reference to socket instance.
    private Socket mSocket = null;

    // The server hostname.
    private String mHostname = "";

    // The server port.
    private int mPort = -1;
    
    /**
     * Sets the server hostname.
     * @param hostname  The server hostname.
     */
    public void setHostname(String hostname)
    {
    	mHostname = hostname;
    }
    
    /**
     * Sets the server port.
     * @param port  The server port.
     */
    public void setPort(String port)
    {
        int value;
        try {
        	value = Integer.parseInt(port);
        }
        catch (NumberFormatException e) {
        	value = -1;
        }
        
    	mPort = value;
    }
    
    /**
     * Opens a connection to the server.
     * @returns  True if successful, false otherwise.
     */
    public boolean connect() {
        try	{
            mSocket = new Socket();
            mSocket.connect(new InetSocketAddress(mHostname, mPort), 1000);
        }
        catch (UnknownHostException e) {
            // Unknown host
            if (D) Log.e(TAG, "connect: UnknownHostException", e);
            mSocket = null;
            return false;
        }
        catch (IOException e) {
            // Could not get I/O for the connection
            if (D) Log.e(TAG, "connect: IOException", e);
            mSocket = null;
            return false;
        }

        if (D) Log.e(TAG, "connected");
        return true;
    }

    /**
     * Closes the connection to the server.
     */
    public void close() {
    	if (mSocket != null) {
    		try {
				mSocket.close();
			} catch (IOException e) {
	            if (D) Log.e(TAG, "close: IOException", e);
			}

            if (D) Log.e(TAG, "closed");

    		mSocket = null;
    	}
    }

    /**
     * Determines if the connection to the server is open.
     * @returns  True if connected, false otherwise.
     */
    public boolean isConnected() {
    	return (mSocket != null);
    }

    /**
     * Checks for a server connection and opens one if necessary.
     */
	public boolean checkConnection() {
		if (mSocket != null) {
			return true;
		}
		else {
			if (!mHostname.equals("") && (mPort != -1)) {
				return connect();
			}
		}

		return false;
	}

	/**
     * Sets the enable setting.
     * @param cmd     The enable command.
     * @param enable  The enable state.
     * @returns       True if successful, false otherwise.
     */
    public boolean setEnable(String cmd, boolean enabled) {
		if (isConnected()) {
			String message = cmd + CMD_DELIM + (enabled ? "1" : "0") + CMD_TERM;
			String response = sendAndReceiveMessage(message);
			return response.equals(STATUS_OK);
		}

		return false;
	}

    /**
     * Gets the enable setting.
     * @param cmd  The enable command.
     * @returns    The enable state.
     */
	public boolean getEnable(String cmd) {
		if (isConnected()) {
			String message = cmd + CMD_TERM;
			String response = sendAndReceiveMessage(message);
			return response.equals("1");
		}

		return false;
	}

    /**
     * Sets the level setting.
     * @param cmd    The level command.
     * @param level  The level value.
     * @returns      True if successful, false otherwise.
     */
	public boolean setLevel(String cmd, int value) {
		if (isConnected()) {
			String message = cmd + CMD_DELIM + Integer.toString(value) + CMD_TERM;
			String response = sendAndReceiveMessage(message);
			return response.equals(STATUS_OK);
		}

		return false;
	}

    /**
     * Gets the level setting.
     * @param cmd  The level command.
     * @returns    The level value.
     */
	public int getLevel(String cmd) {
		int value = 0;

		if (isConnected()) {
			String message = cmd + CMD_TERM;
			String response = sendAndReceiveMessage(message);

			try {
				value = Integer.parseInt(response);
			}
	        catch (NumberFormatException e) {
	        }
		}

		return value;
	}

    /**
     * Sets the filename.
     * @param cmd       The filename command.
     * @param filename  The filename.
     * @returns         True if successful, false otherwise.
     */
	public boolean setFilename(String cmd, String filename) {
		if (isConnected()) {
			String message = cmd + CMD_DELIM + filename + CMD_TERM;
			String response = sendAndReceiveMessage(message);
			return response.equals(STATUS_OK);
		}

		return false;
	}

    /**
     * Gets the filename.
     * @param cmd  The filename command.
     * @returns    The filename.
     */
	public String getFilename(String cmd) {
		String filename = "";

		if (isConnected()) {
			String message = cmd + CMD_TERM;
			String response = sendAndReceiveMessage(message);

			if (!response.equals(STATUS_ERROR)) {
				filename = response;
			}
		}

		return filename;
	}

    /**
     * Gets the metadata.
     * @param cmd  The metadata command.
     * @returns    The metadata.
     */
	public String getMetadata(String cmd) {
		String metadata = "";

		if (isConnected()) {
			String message = cmd + CMD_TERM;
			String response = sendAndReceiveMessage(message);

			if (!response.equals(STATUS_ERROR)) {
				metadata = response;
			}
		}

		return metadata;
	}

    /**
     * Returns a listing of the specified directory.
     * @param path  The directory path to list.
     * @returns     A JSON string containing directory information.
     */
	public String listDirectory(String path) {
		String json = "";

		if (isConnected()) {
			String message;
			if ((path == null) || path.equals("")) {
				message = CMD_LIST_DIR + CMD_TERM;
			}
			else {
				message = CMD_LIST_DIR + CMD_DELIM + path + CMD_TERM;
			}

			String response = sendAndReceiveMessage(message);

			if (!response.equals(STATUS_ERROR)) {
				json = response;
			}
		}

		return json;
	}

    /**
     * Exchanges a message with the server.
     * @param message  The message to send to the server.
     * @returns        The message received from the server.
     */
    private String sendAndReceiveMessage(String message) {
        String response = "";

        if (mSocket != null) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter
                        (new OutputStreamWriter(mSocket.getOutputStream())), true);

                out.print(message);
                out.flush();

                InputStreamReader isr = new InputStreamReader(mSocket.getInputStream());
                StringBuilder inStringBuffer = new StringBuilder();
                int ch;

                while ((ch = isr.read()) != -1) {
                	char[] chars = { (char)ch };
                	String str = new String(chars);

                	if (str.equals(CMD_TERM)) {
                		break;
                	}

                	inStringBuffer.append(str);
                }

                response = inStringBuffer.toString();
            }
            catch (Exception e) {
                // Error sending to host
                if (D) Log.e(TAG, "sendAndReceiveMessage: Exception", e);
            }
        }

        return response;
    }
}
