package com.example.simonsays;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int numIndex = 0;
    int delayTile = 0;
    int goal = 0;
    MediaPlayer mediaPlayer = new MediaPlayer();
    MediaPlayer derrota = new MediaPlayer();
    MediaPlayer victoria = new MediaPlayer();

    Button buttonStart, buttonNormal, buttonFrenzy, buttonZen, b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    int dificultad;

    ArrayList<Button> listaBotones = new ArrayList<>();
    ArrayList<Button> listaBotonesPulsando = new ArrayList<>();
    ArrayList<Button> listaBotonesJuego = new ArrayList<>();
    int aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonNormal = findViewById(R.id.buttonStart2);
        buttonFrenzy = findViewById(R.id.buttonStart3);
        buttonZen = findViewById(R.id.buttonStart4);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);

        b0.setTextSize(50);
        b1.setTextSize(50);
        b2.setTextSize(50);
        b3.setTextSize(50);
        b4.setTextSize(50);
        b5.setTextSize(50);
        b6.setTextSize(50);
        b7.setTextSize(50);
        b8.setTextSize(50);
        b9.setTextSize(50);

        listaBotones.add(b0);
        listaBotones.add(b1);
        listaBotones.add(b2);
        listaBotones.add(b3);
        listaBotones.add(b4);
        listaBotones.add(b5);
        listaBotones.add(b6);
        listaBotones.add(b7);
        listaBotones.add(b8);
        listaBotones.add(b9);


        for (int i = 0; i<listaBotones.toArray().length; i++) {
            listaBotones.get(i).setVisibility(View.INVISIBLE);
        }

        buttonStart.setOnClickListener(view -> {
        /*
        1-fácil
        2-normal
        3-frenzy
        4-Zen
         */
            dificultad = 1;
            delayTile = 500;
            goal = 8;


            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

                for (int i = 0; i<listaBotones.toArray().length; i++) {
                    listaBotones.get(i).setVisibility((View.VISIBLE));
                }

                new Handler().postDelayed(() -> {
                    try {
                        generarPatron();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, ((2000)));

        });

        buttonNormal.setOnClickListener(view -> {
        /*
        1-fácil
        2-normal
        3-frenzy
        4-Zen
         */
            dificultad = 2;
            goal = 14;
            delayTile = 250;

            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            for (int i = 0; i<listaBotones.toArray().length; i++) {
                listaBotones.get(i).setVisibility((View.VISIBLE));
            }

            new Handler().postDelayed(() -> {
                try {
                    generarPatron();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, ((2000)));

        });

        buttonFrenzy.setOnClickListener(view -> {
        /*
        1-fácil
        2-normal
        3-frenzy
        4-Zen
         */
            dificultad = 3;
            goal = 20;
            delayTile = 100;


            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            for (int i = 0; i<listaBotones.toArray().length; i++) {
                listaBotones.get(i).setVisibility((View.VISIBLE));
            }

            new Handler().postDelayed(() -> {
                try {
                    generarPatron();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, ((2000)));

        });

        buttonZen.setOnClickListener(view -> {
        /*
        1-fácil
        2-normal
        3-frenzy
        4-Zen
         */
            dificultad = 4;
            goal = -1;
            delayTile = 320;


            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            for (int i = 0; i<listaBotones.toArray().length; i++) {
                listaBotones.get(i).setVisibility((View.VISIBLE));
            }

            new Handler().postDelayed(() -> {
                try {
                    generarPatron();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, ((2000)));

        });

        b0.setOnClickListener(view -> {
            listaBotonesPulsando.add(b0);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b1.setOnClickListener(view -> {

             mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.one);
            mediaPlayer.start();

            listaBotonesPulsando.add(b1);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b2.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.two);
            mediaPlayer.start();

            listaBotonesPulsando.add(b2);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b3.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.three);
            mediaPlayer.start();

            listaBotonesPulsando.add(b3);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b4.setOnClickListener(view -> {

           mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.four);
           mediaPlayer.start();

            listaBotonesPulsando.add(b4);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b5.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.five);
            mediaPlayer.start();

            listaBotonesPulsando.add(b5);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b6.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.six);
            mediaPlayer.start();

            listaBotonesPulsando.add(b6);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b7.setOnClickListener(view -> {

           mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.seven);
            mediaPlayer.start();

            listaBotonesPulsando.add(b7);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b8.setOnClickListener(view -> {

           mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.eight);
            mediaPlayer.start();

            listaBotonesPulsando.add(b8);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b9.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.nine);
            mediaPlayer.start();

            listaBotonesPulsando.add(b9);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        AnimatorSet animadorBoton = new AnimatorSet();

        ObjectAnimator trasladar = ObjectAnimator.ofFloat(b0, "translationX", -800f, 0f);

        trasladar.setDuration(3980);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b1, "translationX", -800f, 0f);

        trasladar.setDuration(5000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b2, "translationX", -800f, 0f);

        trasladar.setDuration(2500);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b3, "translationX", -800f, 0f);

        trasladar.setDuration(2500);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b4, "translationX", -800f, 0f);

        trasladar.setDuration(5000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b5, "translationX", -800f, 0f);

        trasladar.setDuration(3989);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b6, "translationX", -800f, 0f);

        trasladar.setDuration(1999);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b7, "translationX", -800f, 0f);

        trasladar.setDuration(4000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b8, "translationX", -800f, 0f);

        trasladar.setDuration(2300);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b9, "translationX", -800f, 0f);

        trasladar.setDuration(5000);
        animadorBoton.play(trasladar);
        animadorBoton.start();



    }

    void comprobar() throws InterruptedException {

            if (listaBotonesPulsando.get(numIndex).getText()==listaBotonesJuego.get(numIndex).getText()) {

                if (numIndex+1==listaBotonesJuego.toArray().length) {

                    aciertos++;
                    generarPatron();

                } else {
                    numIndex++;
                }

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Has perdido!");
                derrota = MediaPlayer.create(MainActivity.this, R.raw.hisworld);
                derrota.start();
                builder.setMessage("Derrota!")
                        .setPositiveButton("Repetir", (dialog, id) -> {
                           recreate();
                           derrota.stop();
                        });
                AlertDialog dialog = builder.create();

                dialog.show();
            }

    }

    void generarPatron() throws InterruptedException {

        if (aciertos==goal) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¡Felicitaciones!");
            builder.setMessage("Has ganado, Enhorabuena!")
                    .setPositiveButton("Repetir", (dialog, id) -> recreate())
                    .setNegativeButton("Salir", (dialog, id) -> {



                    });

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ring2);
            mediaPlayer.start();

            AlertDialog dialog = builder.create();
            dialog.show();

        }

        listaBotonesPulsando.clear();
            numIndex = 0;

            Random randomInt = new Random();
            listaBotonesJuego.add(listaBotones.get((randomInt.nextInt(10))));

        int i = 0;

        while (i<listaBotonesJuego.toArray().length) {
            AnimatorSet animadorBoton = new AnimatorSet();
            ObjectAnimator trasladar = ObjectAnimator.ofFloat(listaBotonesJuego.get(i), "alpha", 1f, 0f);
            ObjectAnimator trasladar2 = ObjectAnimator.ofFloat(listaBotonesJuego.get(i), "alpha", 0f, 1f);

            trasladar.setDuration(300);
            trasladar2.setDuration(300);
            animadorBoton.play(trasladar);

            i++;
            new Handler().postDelayed(() -> {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ring);
                mediaPlayer.start();
                animadorBoton.start();
                animadorBoton.play(trasladar2);
                animadorBoton.start();

            }, (((long) i *delayTile)));
        }

    }
}