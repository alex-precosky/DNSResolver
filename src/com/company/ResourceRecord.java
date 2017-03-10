package com.company;

/**
 * Created by Alex on 2017-03-09.
 */
public class ResourceRecord {

    String name;
    short type;
    short rr_class;
    int ttl;
    short rlength;
    byte[] rData;

    int recordLength; // the bytes it took up in the response



    public ResourceRecord( byte[] recordBytes, byte[] responseBytes)
    {
        int index = 0;

        if(recordBytes[0] == 0xc0)
        {
            int offset = recordBytes[1];
            byte[] FQDNSearchArray = new byte[responseBytes.length-offset];
            System.arraycopy(responseBytes, offset, FQDNSearchArray, 0, FQDNSearchArray.length);

            name = FQDNParser.Parse(FQDNSearchArray);
            index += 2;
        }
        else
        {
            name = FQDNParser.Parse(recordBytes);
            index += name.length()+2; // +2 for the count before the first FQDN section, and for the terminator
        }

        type = (short)(recordBytes[index] << 8 + recordBytes[index+1]);
        index += 2;

        rr_class = (short)(recordBytes[index] << 8 + recordBytes[index+1]);
        index += 2;

        ttl = (recordBytes[index] << 24 + recordBytes[index+1] << 16 + recordBytes[index+2] << 8 + recordBytes[index+3]);
        index += 4;

        rlength = (short)(recordBytes[index] << 8 + recordBytes[index+1]);
        index += 2;

        rData = new byte[rlength];
        System.arraycopy(recordBytes, index, rData, 0, rlength);

        recordLength = index + rlength;
    }

}
