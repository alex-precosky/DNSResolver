package com.company;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DNSQuestion {
    public String hostName = "";
    short qtype = 1;  // 1 for ipv4 address
    short qclass = 1; // 1 for internet

    public byte[] serialize() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        byte[] hostnameBytes = format_hostname(hostName);
        try {
            dos.write(hostnameBytes, 0, hostnameBytes.length);

            dos.writeByte((byte) ((qtype >> 8) & 0xff));
            dos.writeByte((byte) (qtype & 0xff));

            dos.writeByte((byte) ((qclass >> 8) & 0xff));
            dos.writeByte((byte) (qclass & 0xff));

            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

    private byte[] format_hostname(String hostName) {
        String[] host_components = hostName.split("\\.");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        for (int i = 0; i < host_components.length; i++) {
            try {
                dos.writeByte((host_components[i].length()));
                dos.writeBytes(host_components[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            dos.writeByte(0);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

}
