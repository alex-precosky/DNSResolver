package com.company;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


public class Main {



    public static void main(String[] args) {

        String dnsServerHostName =  "198.162.35.1";
        dnsServerHostName = "l.gtld-servers.net";
        int portNumber = 53;


        DatagramSocket mySocket = null;

        try{
            mySocket = new DatagramSocket();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        byte[] receiveData = new byte[512];
        try{
            // Set up a receiver
            DatagramSocket receiveSocket = new DatagramSocket(portNumber);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Send the query
            DNSRequest request = new DNSRequest("google.com");
            byte[] packetBytes = request.serialize();
            InetAddress dnsAddress = InetAddress.getByName(dnsServerHostName);
            DatagramPacket packet = new DatagramPacket(packetBytes, 0, packetBytes.length, dnsAddress, portNumber);
            mySocket.send(packet);

            // Get the reply
            receiveSocket.receive(receivePacket);

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Parse the reply
        DNSResponse response = new DNSResponse(receiveData);

    }
}
