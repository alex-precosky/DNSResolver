package com.company;


public class DNSRequest {

    public DNSHeader header;
    public DNSQuestion question;

    public void DNSRequest( DNSHeader header, DNSQuestion question)
    {
        this.header = header;
        this.question = question;
    }

    public DNSRequest( String hostName )
    {
        DNSHeader header = new DNSHeader();
        header.id = 1;
        header.rd = true;
        header.q_count = 1;

        DNSQuestion question = new DNSQuestion();
        question.qtype = 1;
        question.qclass = 1;
        question.hostName = hostName;

        this.header = header;
        this.question = question;
    }

    public byte [] serialize()
    {
        byte[] headerBytes = header.serialize();
        byte[] questionBytes = question.serialize();

        byte returnArray[] = new byte [headerBytes.length + questionBytes.length];

        for(int i = 0; i < headerBytes.length; i++)
            returnArray[i] = headerBytes[i];

        for(int i = 0; i < questionBytes.length; i++)
            returnArray[i+12] = questionBytes[i];

        return returnArray;
    }

}
