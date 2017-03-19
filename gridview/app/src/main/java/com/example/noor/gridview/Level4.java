package com.example.noor.gridview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;



public class Level4 extends AppCompatActivity {


    int turn1 = 1;
    int turn2=0;

    int currentposition1 = 0;
    int new_position1=0;

    int old_color1=0;
    int clicked_position1 = 0;
    int currentposition2 = 0;
    int new_position2=0;

    int old_color2=0;
    int clicked_position2 = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level4);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new Level1Adapter(this));
        final Level4Adapter im = new Level4Adapter(this);
        final Button button = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(turn1==1){

                    Random r = new Random(System.currentTimeMillis());
                    int i1 = r.nextInt(6) + 1;
                    new_position1 = currentposition1 + i1;
                    Toast.makeText(Level4.this, "Move to position " + new_position1,
                            Toast.LENGTH_SHORT).show();

                }
                else

                {
                    Toast.makeText(Level4.this, "Its not your turn",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (turn1 == 1) {
                    turn2=1;
                    turn1=0;

                    if(new_position1<=100) {
                        if (position + 1 != new_position1) {
                            Toast.makeText(Level4.this, "Wrong move, Move to " + new_position1,
                                    Toast.LENGTH_SHORT).show();

                        } else {


                            old_color1 = currentposition1;
                            clicked_position1 = position;
                            if ((clicked_position1 + 1) % 13 == 0) {
                                clicked_position1 -= 11;
                                Toast.makeText(Level4.this, "Moving up",
                                        Toast.LENGTH_SHORT).show();

                            }
                            if ((clicked_position1 + 1) % 17 == 0 || (clicked_position1 + 1) % 17 == 11) {
                                clicked_position1 += 11;
                                Toast.makeText(Level4.this, "Moving down",
                                        Toast.LENGTH_SHORT).show();
                            }
                            position = old_color1;
                            im.texts[position] = Integer.toString(old_color1 + 1);
                            im.notifyDataSetChanged();
                            gridview.setAdapter(im);
                            gridview.invalidateViews();
                            currentposition1 = clicked_position1;
                            position = clicked_position1;
                            im.texts[position] = "P1";
                            im.notifyDataSetChanged();
                            gridview.setAdapter(im);


                        }
                        if(new_position1==100){
                            new AlertDialog.Builder(Level4.this)
                                    .setTitle("HI!")
                                    .setMessage("You Won!")
                                    .setCancelable(false)
                                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Level4.this, Level5.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                        }
                    }
                    else{

                        Toast.makeText(Level4.this, "You cannot make this move",
                                Toast.LENGTH_SHORT).show();



                    }

                }
                if(turn2==1) {
                    Toast.makeText(Level4.this, "Computers Turn",
                            Toast.LENGTH_SHORT).show();
                    turn2 = 0;
                    turn1 = 1;
                    Random r1 = new Random(System.currentTimeMillis());
                    int i2 = r1.nextInt(6) + 1;
                    new_position2 = currentposition2 + i2;
                    if (new_position2 <= 99) {
                        Toast.makeText(Level4.this, "Moving to " + new_position2,
                                Toast.LENGTH_SHORT).show();

                        if ((new_position2 + 1) % 13 == 0 || (new_position2 + 1) % 17 == 11) {
                            new_position2 -= 11;
                            Toast.makeText(Level4.this, "Moving up",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if ((new_position2 + 1) % 17 == 0) {
                            new_position2 += 11;
                            Toast.makeText(Level4.this, "Moving down",
                                    Toast.LENGTH_SHORT).show();
                        }
                        old_color2 = currentposition2;
                        clicked_position2 = new_position2;
                        position = old_color2;
                        im.texts[position] = Integer.toString(old_color2 + 1);
                        im.notifyDataSetChanged();
                        gridview.setAdapter(im);
                        gridview.invalidateViews();
                        currentposition2 = clicked_position2;
                        position = clicked_position2;
                        im.texts[position] = "P2";
                        im.notifyDataSetChanged();
                        gridview.setAdapter(im);

                        if(new_position2==99){
                            new AlertDialog.Builder(Level4.this)
                                    .setTitle("HI!")
                                    .setMessage("You Lost!")
                                    .setCancelable(false)
                                    .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Level4.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                        }

                    }
                    else {
                        Toast.makeText(Level4.this, "Computer cannot make this move",
                                Toast.LENGTH_SHORT).show();


                    }
                }
            }

        });
    }
}

