package com.company;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


public class Main {



    public static void main(String[] args) {

        String dnsServerHostName =  "198.162.35.1";
        dnsServerHostName = "127.0.0.1";
        int portNumber = 53;


        DatagramSocket mySocket = null;

        try{
            mySocket = new DatagramSocket();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        // Send the query
        try{
            DNSRequest request = new DNSRequest("www.google.com");
            byte[] packetBytes = request.serialize();
            InetAddress dnsAddress = InetAddress.getByName(dnsServerHostName);
            DatagramPacket packet = new DatagramPacket(packetBytes, 0, packetBytes.length, dnsAddress, 53);
            mySocket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
