package com.company;

/**
 * Created by Alex on 2017-03-09.
 */
public class FQDNParser {

    public static String Parse(byte[] byteArray)
    {
        boolean done = false;
        int cursor = 0;
        String returnString = "";


        while(true)
        {
            if(byteArray[cursor] == 0)
                return returnString;

            if(cursor!=0)
                returnString += ".";

            int numChars = byteArray[cursor++];
            for(int i = 0; i < numChars; i++)
            {
                returnString += (char)byteArray[cursor++];
            }


        }
    }
}
