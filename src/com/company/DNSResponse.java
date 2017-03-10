package com.company;

/**
 * Created by Alex on 2017-03-09.
 */
public class DNSResponse {

    DNSHeader header;

    DNSQuestion[] questions;
    ResourceRecord[] answers;
    ResourceRecord[] authorities;
    ResourceRecord[] additionals;


    public DNSResponse( byte [] responseBytes )
    {
        byte [] headerBytes = new byte[12];

        System.arraycopy(responseBytes, 0, headerBytes, 0, 12);
        header = new DNSHeader(headerBytes);

        questions = new DNSQuestion[header.q_count];
        answers = new ResourceRecord[header.ans_count];
        authorities = new ResourceRecord[header.auth_count];
        additionals = new ResourceRecord[header.add_count];

        int sectionIdx = 12;

        for(int i = 0; i < header.q_count; i++)
        {
            int bytesLeft = responseBytes.length - sectionIdx;
            byte[] remainingPacket = new byte[bytesLeft];

            questions[i] = new DNSQuestion(remainingPacket);
            sectionIdx += questions[i].hostName.length()+2 + 4;
        }

        for(int i = 0; i < header.ans_count; i++)
        {
            int bytesLeft = responseBytes.length - sectionIdx;
            byte[] remainingPacket = new byte[bytesLeft];

            ResourceRecord rr = new ResourceRecord(remainingPacket, responseBytes);
            answers[i] = rr;
            sectionIdx += rr.recordLength;
        }

        for(int i = 0; i < header.auth_count; i++)
        {
            int bytesLeft = responseBytes.length - sectionIdx;
            byte[] remainingPacket = new byte[bytesLeft];

            ResourceRecord rr = new ResourceRecord(remainingPacket, responseBytes);
            authorities[i] = rr;
            sectionIdx += rr.recordLength;
        }

        for(int i = 0; i < header.add_count; i++)
        {
            int bytesLeft = responseBytes.length - sectionIdx;
            byte[] remainingPacket = new byte[bytesLeft];

            ResourceRecord rr = new ResourceRecord(remainingPacket, responseBytes);
            additionals[i] = rr;
            sectionIdx += rr.recordLength;
        }


    }

}
