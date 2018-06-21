package edu.handong.csee.java.BonusHW;

import java.net.*;
import java.io.*;
import java.util.*;

public class URLReader {
	String address;
	public URLReader(String a)
	{
		this.address = a;
	}
    public ArrayList<String> read() throws Exception {
    	ArrayList<String> htmlCode = new ArrayList<String>();
        
    	URL oracle = new URL(address);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            htmlCode.add(inputLine);
        in.close();
        
        return htmlCode;
    }
}