package com.company;

import java.util.Random;

public class DNSHeader
        {
                short id;       // identification number
                boolean rd;     // recursion desired
                boolean tc;     // truncated message
                boolean aa;     // authoritive answer
                int opcode; // purpose of message
                boolean qr;     // query/response flag
                int rcode ;  // response code
                boolean cd;     // checking disabled
                boolean ad;     // authenticated data
                boolean z;      // its z! reserved
                boolean ra;     // recursion available
                short q_count;  // number of question entries
                short ans_count; // number of answer entries
                short auth_count; // number of authority entries
                short add_count; // number of resource entries

                public byte[] serialize()
                {
                        byte returnArray[] = new byte[12];

                        returnArray[0] = (byte)((id>>8) & 0xff);
                        returnArray[1] = (byte)(id & 0xff);

                        if(qr)
                                returnArray[2] |= 1 << 7;

                        returnArray[2] |= (opcode <<6);

                        if(aa)
                                returnArray[2] |= 1 << 2;

                        if(tc)
                                returnArray[2] |= 1 << 1;

                        if(rd)
                                returnArray[2] |= 1;

                        if(ra)
                                returnArray[3] |= 1 << 7;

                        returnArray[3] |= rcode;

                        returnArray[4] = (byte)((q_count>>8) & 0xff);
                        returnArray[5] = (byte)(q_count & 0xff);

                        returnArray[6] = (byte)((ans_count>>8) & 0xff);
                        returnArray[7] = (byte)(ans_count & 0xff);

                        returnArray[8] = (byte)((auth_count>>8) & 0xff);
                        returnArray[9] = (byte)(auth_count & 0xff);

                        returnArray[10] = (byte)((add_count>>8) & 0xff);
                        returnArray[11] = (byte)(add_count & 0xff);

                        return returnArray;
                }


                public DNSHeader()
                {
                        Random rnd = new Random();
                        id = (short)rnd.nextInt(32767);

                        rd = false; // never use recursion
                        tc = false;
                        aa = false;

                        q_count = 1;
                        ans_count = 0;
                        auth_count = 0;
                        add_count = 0;

                }

        }
