package com.ttl.udpdata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    private static final String host = null;
    private int port;
    String str=null;
    /** Called when the activity is first created. */
    TextView txtData;
    byte[] send_data = new byte[100];
    byte[] receiveData = new byte[100];
    String modifiedSentence;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtData = findViewById(R.id.tvData);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                str="test";
//                try {
//                    client();
//                    //txt1.setText(modifiedSentence);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                Python py = Python.getInstance();
                PyObject pyObject = py.getModule("script");
                PyObject obj = pyObject.callAttr("main");
                txtData.setText(obj.toString());
                Toast.makeText(MainActivity.this, "UDP ", Toast.LENGTH_SHORT).show();

            }
        });

    }

//    public void client() throws IOException{
//
//        Toast.makeText(this, "Client fun", Toast.LENGTH_SHORT).show();
//
//        DatagramSocket client_socket = new DatagramSocket(8000);
//        InetAddress IPAddress =  InetAddress.getByName("0.0.0.0");
//
//        //while (true)
//        // {
//        send_data = str.getBytes();
//        //System.out.println("Type Something (q or Q to quit): ");
//
////        DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 8000);
////        client_socket.receive(send_packet);
//        //chandra
//        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//        client_socket.receive(receivePacket);
//        modifiedSentence = new String(receivePacket.getData());
//        //System.out.println("FROM SERVER:" + modifiedSentence);
//        if(modifiedSentence.charAt(2)=='%')
//            txtData.setText(modifiedSentence.substring(0, 3));
//        else
//            txtData.setText(modifiedSentence);
//        modifiedSentence=null;
//        client_socket.close();
//
//        // }
//
//    }

}