package com.example.project4_anjani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    //initializing the result distance variables
    private static final int closeguess = 3;
    private static final int completemiss = 4;
    private static final int nearmiss = 2;
    private static final int success = 1;
    //
    final int gopher = 0;
    int g_position;
    final int p1 = 1;
    final int p2 = 2;
    //Initializing the arrays for the text views created
    final int[] holes_player1 ={
            R.id.cell1,R.id.cell2,R.id.cell3,R.id.cell4,R.id.cell5,
            R.id.cell6,R.id.cell7,R.id.cell8,R.id.cell9,R.id.cell10,
            R.id.cell11,R.id.cell12,R.id.cell13,R.id.cell14,R.id.cell15,
            R.id.cell16,R.id.cell17,R.id.cell18,R.id.cell19,R.id.cell20,
            R.id.cell21,R.id.cell22, R.id.cell23,R.id.cell24,R.id.cell25,
            R.id.cell26,R.id.cell27,R.id.cell28,R.id.cell29,R.id.cell30,
            R.id.cell31,R.id.cell32,R.id.cell33,R.id.cell34,R.id.cell35,
            R.id.cell36,R.id.cell37,R.id.cell38,R.id.cell39,R.id.cell40,
            R.id.cell41,R.id.cell42,R.id.cell43,R.id.cell44,R.id.cell45,
            R.id.cell46,R.id.cell47,R.id.cell48,R.id.cell49,R.id.cell50,
            R.id.cell51,R.id.cell52,R.id.cell53,R.id.cell54,R.id.cell55,
            R.id.cell56,R.id.cell57,R.id.cell58,R.id.cell59,R.id.cell60,
            R.id.cell61,R.id.cell62,R.id.cell63,R.id.cell64,R.id.cell65,
            R.id.cell66,R.id.cell67,R.id.cell68,R.id.cell69,R.id.cell70,
            R.id.cell71,R.id.cell72,R.id.cell73,R.id.cell74,R.id.cell75,
            R.id.cell76,R.id.cell77,R.id.cell78,R.id.cell79,R.id.cell80,
            R.id.cell81,R.id.cell82,R.id.cell83,R.id.cell84,R.id.cell85,
            R.id.cell86,R.id.cell87,R.id.cell88,R.id.cell89,R.id.cell90,
            R.id.cell91,R.id.cell92,R.id.cell93,R.id.cell94,R.id.cell95,
            R.id.cell96,R.id.cell97,R.id.cell98,R.id.cell99,R.id.cell100
    };
    final int[] holearray_1 = new int[100];
    final int[] holes_player2 ={
            R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,
            R.id.txt6,R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt10,
            R.id.txt11,R.id.txt12,R.id.txt13,R.id.txt14,R.id.txt15,
            R.id.txt16,R.id.txt17,R.id.txt18,R.id.txt19,R.id.txt20,
            R.id.txt21,R.id.txt22, R.id.txt23,R.id.txt24,R.id.txt25,
            R.id.txt26,R.id.txt27,R.id.txt28,R.id.txt29,R.id.txt30,
            R.id.txt31,R.id.txt32,R.id.txt33,R.id.txt34,R.id.txt35,
            R.id.txt36,R.id.txt37,R.id.txt38,R.id.txt39,R.id.txt40,
            R.id.txt41,R.id.txt42,R.id.txt43,R.id.txt44,R.id.txt45,
            R.id.txt46,R.id.txt47,R.id.txt48,R.id.txt49,R.id.txt50,
            R.id.txt51,R.id.txt52,R.id.txt53,R.id.txt54,R.id.txt55,
            R.id.txt56,R.id.txt57,R.id.txt58,R.id.txt59,R.id.txt60,
            R.id.txt61,R.id.txt62,R.id.txt63,R.id.txt64,R.id.txt65,
            R.id.txt66,R.id.txt67,R.id.txt68,R.id.txt69,R.id.txt70,
            R.id.txt71,R.id.txt72,R.id.txt73,R.id.txt74,R.id.txt75,
            R.id.txt76,R.id.txt77,R.id.txt78,R.id.txt79,R.id.txt80,
            R.id.txt81,R.id.txt82,R.id.txt83,R.id.txt84,R.id.txt85,
            R.id.txt86,R.id.txt87,R.id.txt88,R.id.txt89,R.id.txt90,
            R.id.txt91,R.id.txt92,R.id.txt93,R.id.txt94,R.id.txt95,
            R.id.txt96,R.id.txt97,R.id.txt98,R.id.txt99,R.id.txt100
    };
    final int[] holearray_2 = new int[100];

    TextView player1_status;
    TextView player2_status;
    Button Button2;
    //Handler variables
    final String[] result_status = {"","Success","Near Miss", "Close Guess", "Complete Miss"};
    public static Handler messageHandler;
    public static Handler r1Handler;
    public static Handler r2Handler;
    int p1_guesses=1;
    int p2_guesses=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Textviews where the status of the players are displayed.
        player1_status=findViewById(R.id.p1_status);
        player2_status=findViewById(R.id.p2_status);

        g_position = (int)(Math.random()*100)%100+1; //Reference : StackOverflow
        setobject(g_position,gopher);
        Button2 = findViewById(R.id.button3);
        Button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                r1Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Looper l1=Looper.myLooper();
                        l1.quit();
                    }
                });
                r2Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Looper l2=Looper.myLooper();
                        l2.quit();
                    }
                });
                onBackPressed();
            }

            });
        messageHandler = new Handler(getMainLooper()){
            @SuppressLint("HandlerLeak")
            public void handleMessage(Message msg){
                //displaying the messages from handlers of the player status
                int what = msg.what;
                switch(what){
                    case p1:
                        Log.i("Arg1",Integer.toString(msg.arg1));
                        if(msg.arg1!=0)
                            player1_status.setText(result_status[msg.arg1]);
                        break;
                    case p2:
                        Log.i("Arg1",Integer.toString(msg.arg1));
                        if(msg.arg1!=0)
                            player2_status.setText(result_status[msg.arg1]);
                        break;
                }
            }
        };
        Thread player1 = new Thread (new p1Runnable());
        player1.start();
        Thread player2 = new Thread  (new p2Runnable() );
        player2.start();
    }

    boolean foundgopher = false;
    //Thread1
    class p1Runnable implements Runnable {
        public Message msg;
        int p1pos;
        public void testfunc(int x){
            msg.arg1=x;
            messageHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (holearray_1[p1pos-1] ==0 ) {
                        setobject(p1pos, p1);
                    }
                }
            });
        }
        @Override
        public void run() {
            Looper.prepare();

            r1Handler=new Handler(Looper.myLooper());

            while (!foundgopher){
                msg=Message.obtain();
                msg.what=p1;
                p1pos = (int)(Math.random()*100)%100+1;

                Log.i("Anjani",Integer.toString(p1pos));
                Log.i("Anjani Gopher",Integer.toString(g_position));
                int result_distance = calc_distance(p1pos);
                Log.i("Anjani dist",Integer.toString(result_distance));
                if (result_distance == success){
                    foundgopher = true;
                    msg.arg1=0;
                    messageHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            player1_status.setText("Player 1 wins!!");
                            TextView t = (TextView) findViewById(holes_player1[p1pos-1]);
                            t.setText(Integer.toString(p1_guesses));
                            setobject(p1pos, gopher);
                            p1_guesses++;
                        }
                    });
                }
                else {
                    testfunc(result_distance);
                }
                messageHandler.sendMessage(msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Looper.loop();
            Looper.myLooper().quit();
        }


    }
    //Thread2
    class p2Runnable implements Runnable {
        public Message msg;
        int p2pos;
        public void testfunc(int x){
            msg.arg1=x;
            messageHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (holearray_2[p2pos-1] ==0 ) {
                        setobject(p2pos, p2);
                    }
                }
            });
        }

        @Override
        public void run() {
            Looper.prepare();
            r2Handler=new Handler(Looper.myLooper());
            while (!foundgopher){
                msg=Message.obtain();
                msg.what=p2;
                p2pos = (int)(((Math.random() *100)%100) +1);
                Log.i("Anjani",Integer.toString(p2pos));
                Log.i("Anjani Gopher",Integer.toString(g_position));
                int result_distance = calc_distance(p2pos);
                Log.i("Anjani dist",Integer.toString(result_distance));
                if (result_distance == success){
                    foundgopher = true;
                    msg.arg1=0;
                    messageHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            player2_status.setText("Player 2 wins!!");
                            TextView t = (TextView) findViewById(holes_player2[p2pos-1]);
                            t.setText(Integer.toString(p2_guesses));
                            setobject(p2pos, gopher);
                            p2_guesses++;
                        }
                    });
                }
                else {
                    testfunc(result_distance);
                }
                messageHandler.sendMessage(msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Looper.loop();
            Looper.myLooper().quit();

        }}



    //sets the item - player1 , player2 and gopher with the respective colour.
    private void setobject(int position, int object) {
        switch(object){
            case p1: {
                Log.i("Anjani setobject",Integer.toString(position));
                TextView t = (TextView) findViewById(holes_player1[position-1]);
                int p1_display = ContextCompat.getColor(this, R.color.orange_player1);
                t.setBackgroundColor(p1_display);
                t.setText(Integer.toString(p1_guesses));
                p1_guesses=p1_guesses+1;
                holearray_1[position-1] = p1;
                break;
            }
            case p2: {
                TextView t = (TextView) findViewById(holes_player2[position-1]);
                int p2_display = ContextCompat.getColor(this, R.color.pink_player2);
                t.setBackgroundColor(p2_display);
                t.setText(Integer.toString(p2_guesses));
                p2_guesses=p2_guesses+1;
                holearray_2[position-1] = p2;
                break;
            }
            case gopher: {
                TextView t1 = (TextView) findViewById(holes_player1[position-1]);
                int g_display = ContextCompat.getColor(this, R.color.gopher);
                t1.setBackgroundColor(g_display);
                holearray_1[position-1] = gopher;
                TextView t2 = (TextView) findViewById(holes_player2[position-1]);
                int g1_display = ContextCompat.getColor(this, R.color.gopher);
                t2.setBackgroundColor(g1_display);
                holearray_2[position-1] = gopher;
                break;
            }
        }
    }
    //calculates the distance of the players and returns the respective distance
    public int calc_distance(int position){
        int pRow = position / 10;
        int pCol = position % 10;
        int gRow = g_position / 10;
        int gCol = g_position % 10;
        int cdist = Math.abs(pCol - gCol);
        int rdist = Math.abs(pRow - gRow);


        if (g_position == position) {
            return success;
        }
        else if (position >= g_position - 8 && position <= g_position + 8) {
            return nearmiss;
        }
        //Reference : Stack Overflow
        else if ((pRow == gRow && cdist == 2) || (pCol == gCol && rdist == 2) || cdist == 2 && rdist == 2) {
            return closeguess;
        }
        else {
            return completemiss;
        }
    }

}
