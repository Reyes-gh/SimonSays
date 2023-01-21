package com.example.simonsays;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*
    las variables numIndex delayTile y goal servirán para los métodos situados al final del código, los cuales compondrán
    el funcionamiento del juego.
     */

    int numIndex = 0;
    int delayTile = 0;
    int goal = 0;

    /*
    Instanciamos 3 mediaPlayer, uno para la canción de victoria, otro para la de derrota y el último para los sonidos al
    pulsar. (Algunas veces MediaPlayer deja de funcionar con un error (1, -19).
     */

    MediaPlayer mediaPlayer = new MediaPlayer();
    MediaPlayer derrota = new MediaPlayer();
    MediaPlayer victoria = new MediaPlayer();

    /*
    Instanciamos el logo para poder trabajar con el, al igual que los botones.
     */
    ImageView logo;

    Button buttonStart, buttonNormal, buttonFrenzy, buttonZen, b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    /*
    Almacenaremos la dificultad en un entero para poder diferenciar los tipos de dificultad que existen y cambiar
    las variables anteriores (numIndex, delayTile...) acorde a cada dificultad.
     */
    int dificultad;

    /*
    Se crean 3 ArrayList, uno donde estarán todos los botones, que servirá para que el ArrayList botonesJuego pueda elegir alguno
    aleatorio, el ArrayList botonesJuego irá almacenando los botones que tenemos que pulsar para conseguir un acierto, de esto se
    encarga botonesPulsando, el cual es un ArrayList que va almacenando los botones que pulsamos y los compara con los botones que
    debemos pulsar (botonesJuego) así podemos controlar los fallos y podemos limpiar los ArrayList para hacer lo que queramos.
     */
    ArrayList<Button> listaBotones = new ArrayList<>();
    ArrayList<Button> listaBotonesPulsando = new ArrayList<>();
    ArrayList<Button> listaBotonesJuego = new ArrayList<>();
    int aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        nos aseguramos de establecer bien el logo con su id del xml, y nos aseguramos de que tanto las canciones de victoria como de
        derrota se paren (por si ocurre algún error y no se paran de manera predeterminada).
         */
        logo = findViewById(R.id.imageView);
        if (derrota.isPlaying()) derrota.stop();
        if (victoria.isPlaying()) victoria.stop();

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


        /*
        Al crear la aplicación, se harán los 10 botones del juego invisibles (e inactivos) para que podamos ver únicamente
        el menú.
         */
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
            goal = 7;

            /*
            Una vez pulsado el botón, se hará todo el menú invisible, y se verán solo las casillas de juego.
             */

            logo.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

                for (int i = 0; i<listaBotones.toArray().length; i++) {
                    listaBotones.get(i).setVisibility((View.VISIBLE));
                }

                /*
                El método botones animados se encarga de animar una entrada para los botones.
                 */
                botonesanimados();

                new Handler().postDelayed(() -> {
                    try {
                        /*
                        Y se llama al método generarPatrón.
                         */
                        generarPatron();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, ((2000)));

        });

        /*
        Los siguientes botones realizan las mismas funciones pero cada uno lleva una dificultad distinta al método.
         */

        buttonNormal.setOnClickListener(view -> {
        /*
        1-fácil
        2-normal
        3-frenzy
        4-Zen
         */
            dificultad = 2;
            goal = 10;
            delayTile = 250;

            logo.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            botonesanimados();

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
            goal = 14;
            delayTile = 100;

            logo.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            botonesanimados();

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
            goal = 999;
            delayTile = 320;

            logo.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            buttonNormal.setVisibility(View.INVISIBLE);
            buttonFrenzy.setVisibility(View.INVISIBLE);
            buttonZen.setVisibility(View.INVISIBLE);

            botonesanimados();

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

        /*
        Cada botón al ser pulsado se añade al ArrayList y llama al método comprobar (véase método comprobar).
         */

        b0.setOnClickListener(view -> {

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.zero);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

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
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

            listaBotonesPulsando.add(b9);
            try {
                comprobar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



    }

    /*
    Método que reproduce un sonido y anima los botones.
     */
    void botonesanimados() {

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.letsgo);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);

        AnimatorSet animadorBoton = new AnimatorSet();

        ObjectAnimator trasladar = ObjectAnimator.ofFloat(b0, "translationX", -800f, 0f);

        trasladar.setDuration(1980);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b1, "translationX", -800f, 0f);

        trasladar.setDuration(2000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b2, "translationX", -800f, 0f);

        trasladar.setDuration(1500);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b3, "translationX", -800f, 0f);

        trasladar.setDuration(1500);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b4, "translationX", -800f, 0f);

        trasladar.setDuration(2000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b5, "translationX", -800f, 0f);

        trasladar.setDuration(1989);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b6, "translationX", -800f, 0f);

        trasladar.setDuration(1200);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b7, "translationX", -800f, 0f);

        trasladar.setDuration(2000);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b8, "translationX", -800f, 0f);

        trasladar.setDuration(1300);
        animadorBoton.play(trasladar);
        animadorBoton.start();

        trasladar = ObjectAnimator.ofFloat(b9, "translationX", -800f, 0f);

        trasladar.setDuration(3000);
        animadorBoton.play(trasladar);
        animadorBoton.start();


    }

    void comprobar() throws InterruptedException {

        /*
        Este alertDialog se asegura de que al pulsar un botón y el patrón no estar definido, salte un mensaje de aviso y
        pare el código, en caso de no detener el código la aplicación crashearía.
         */

        if (listaBotonesJuego.toArray().length==0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hey hey, espera...");
            derrota = MediaPlayer.create(MainActivity.this, R.raw.hisworld);
            derrota.start();
            builder.setMessage("¡Has pulsado los botones antes de tiempo, espera a generar un patrón!")
                    .setPositiveButton("Reintentar", (dialog, id) -> {
                        try {
                            /*
                            Limpia las variables del juego y genera un nuevo patrón.
                             */
                            listaBotonesJuego.clear();
                            aciertos = 0;
                            derrota.stop();
                            generarPatron();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
            builder.setNegativeButton("Salir", (dialog, id) -> {
                derrota.stop();
                recreate();
            });
            AlertDialog dialog = builder.create();

            dialog.show();
            return;
        }

        /*
        Si detecta que has pulsado el botón correcto, llegará a este if
         */
            if (listaBotonesPulsando.get(numIndex).getText()==listaBotonesJuego.get(numIndex).getText()) {

                /*
                Si al haber pulsado el botón correcto se detecta que has llegado al final del patrón, se sumará un acierto
                y se reproducirá un sonido, además de volver a llamar al método generarPatrón().
                 */
                if (numIndex+1==listaBotonesJuego.toArray().length) {

                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.guessring);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(MediaPlayer::release);

                    aciertos++;
                    generarPatron();

                } else {
                    numIndex++;
                }

                /*
                En caso de no pulsar la casilla correcta saltará este Diálogo de derrota con su respectiva música y menú.
                 */
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("¡Derrota!");
                derrota = MediaPlayer.create(MainActivity.this, R.raw.hisworld);
                derrota.start();
                builder.setMessage("Inténtalo de nuevo ¡Seguro que a la próxima lo harás mejor!")
                        .setPositiveButton("Reintentar", (dialog, id) -> {
                            try {
                                listaBotonesJuego.clear();
                                aciertos = 0;
                                derrota.stop();
                                generarPatron();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        });
                builder.setNegativeButton("Salir", (dialog, id) -> {

                    derrota.stop();
                    recreate();
                });
                AlertDialog dialog = builder.create();

                dialog.show();
            }

    }

    void generarPatron() throws InterruptedException {

        /*
        Si al generar patrón el if detecta que tenemos los suficientes aciertos saltará el mensaje y la música de victoria.
         */

        if (aciertos>=goal) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            victoria = MediaPlayer.create(MainActivity.this, R.raw.liveandlearn);
            victoria.start();

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.feelinggood);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            builder.setTitle("¡Enhorabuena!");
            builder.setMessage("Lo has hecho bastante bien, felicidades ¡Has ganado!")
                    .setPositiveButton("Repetir", (dialog, id) -> {
                        try {
                            aciertos = 0;
                            listaBotonesJuego.clear();
                            generarPatron();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        victoria.stop();
            });
                    builder.setNegativeButton("Salir", (dialog, id) -> {
                        recreate();
                        victoria.stop();
                    });

            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ring2);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        /*
        Al generar el patrón, limpiamos el array de los botones que hemos pulsado, para así tener que repetir el patrón y replicar
        el nuevo (funcionamiento básico de Simón dice o Simon Says).
         */

            listaBotonesPulsando.clear();
            numIndex = 0;

            /*
            Se genera un int random para elegir un botón aleatorio del array de botones y añadirlo al del juego.
             */

            Random randomInt = new Random();
            listaBotonesJuego.add(listaBotones.get((randomInt.nextInt(10))));

        int i = 0;

        /*
        Por cada botón nuevo que se añada se repetirá una animación con un delay (el cual cambia con la dificultad) mostrando el
        patrón a seguir.
         */

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

            }, (((long) i * delayTile)));
        }

    }
}