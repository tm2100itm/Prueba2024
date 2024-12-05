/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.modelo;

/**
 *
 * @author mcalf
 */
import com.mycompany.videojuegolaberinto_c4h043.vista.GUILaberinto;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

public class AreaJuego {
    // Clase que representa el área del juego, incluyendo el personaje, los enemigos y la lógica del laberinto.

    private Personaje personaje; // El personaje controlado por el jugador.
    private ArrayList<Enemigo> enemigos; // Lista de enemigos en el área del juego.
    private final int[] coordenadasX = {27, 54, 76, 103, 126, 153, 176, 199, 223, 251, 273, 298, 323, 348, 373, 396, 423, 450, 471, 496, 520, 546};// Coordenadas X predefinidas para las casillas del laberinto.
    private final int[] coordenadasY = {28, 52, 75, 100, 123, 147, 169, 191, 216, 239, 265, 286, 311, 334, 358, 380, 402, 424, 452, 473, 497, 520};// Coordenadas Y predefinidas para las casillas del laberinto.
    private int[][] laberinto; // Representación del laberinto como matriz (0 = espacio libre, 1 = pared).
    private GUILaberinto guiLaberinto; // Referencia a la interfaz gráfica del juego.
    private int vidas = 5; // Número inicial de vidas del jugador.
    private static final int TAMANO_CELDA = 25; // Tamaño de cada celda en píxeles.


    // Constructor
    public AreaJuego(int[][] laberinto, GUILaberinto guiLaberinto) {
        this.laberinto = laberinto; // Inicializa el laberinto.
        this.guiLaberinto = guiLaberinto; // Vincula la interfaz gráfica.
        colocarPersonaje(); // Coloca al personaje en la posición inicial.
        colocarEnemigos(); // Coloca a los enemigos en posiciones iniciales.
        iniciarMovimientoEnemigos(); // Inicia el movimiento de los enemigos.
    }
    // Coloca al personaje en una posición inicial específica del laberinto.
    private void colocarPersonaje() {
        int fila = 17;
        int columna = 0;
        URL imgURL = getClass().getResource("/images/FRONTAL-2.gif");
        System.out.println("URL de la imagen: " + imgURL);
        if (imgURL == null) {
            throw new IllegalArgumentException("Error: Imagen 'FRONTAL-2.gif' no encontrada.");
        }
        personaje = new Personaje(fila, columna, coordenadasX[columna], coordenadasY[fila], new ImageIcon(imgURL));
    }
// Coloca a los enemigos en posiciones iniciales específicas del laberinto.
  
    private void colocarEnemigos() {
    enemigos = new ArrayList<>();
    int[][] posicionesEnemigos = {{1, 1}, {3, 5}, {10, 7}, {15, 3}, {20, 18}};

    for (int[] posicion : posicionesEnemigos) {
        int fila = posicion[0];
        int columna = posicion[1];
        URL imgURL = getClass().getResource("/images/frontalv2.gif");
        if (imgURL == null) {
            throw new IllegalArgumentException("Error: Imagen 'DETRÁS-2.gif' no encontrada.");
        }
        Enemigo enemigo = new Enemigo(fila, columna, coordenadasX[columna], coordenadasY[fila], new ImageIcon(imgURL));
        enemigo.setDireccion((int) (Math.random() * 4)); // Asignar dirección aleatoria inicial
        enemigos.add(enemigo);
    }
}


public void moverEnemigo(Enemigo enemigo) {
    int filaActual = enemigo.getFila();
    int columnaActual = enemigo.getColumna();

    // Posibles direcciones: {fila, columna}
    int[][] direcciones = {
        {-1, 0}, // Arriba
        {1, 0},  // Abajo
        {0, -1}, // Izquierda
        {0, 1}   // Derecha
    };

    // Obtener la dirección actual del enemigo
    int direccionActual = enemigo.getDireccion();
    int nuevaFila = filaActual + direcciones[direccionActual][0];
    int nuevaColumna = columnaActual + direcciones[direccionActual][1];

    // Verificar si la nueva posición es válida
    if (esPosicionValida(nuevaFila, nuevaColumna)) {
        // Mover al enemigo
        enemigo.setFila(nuevaFila);
        enemigo.setColumna(nuevaColumna);
        enemigo.setX(nuevaColumna * TAMANO_CELDA);
        enemigo.setY(nuevaFila * TAMANO_CELDA);
    } else {
        // Cambiar a una nueva dirección aleatoria si choca con un muro
        int nuevaDireccion = (int) (Math.random() * 4); // Nueva dirección aleatoria
        enemigo.setDireccion(nuevaDireccion);
    }
}

private boolean esPosicionValida(int fila, int columna) {
    // Validar si la posición está dentro de los límites del laberinto
    if (fila < 0 || fila >= laberinto.length || columna < 0 || columna >= laberinto[0].length) {
        return false; // Fuera de los límites
    }

    // Validar si la posición no es una pared (1 = pared, 0 = espacio libre)
    if (laberinto[fila][columna] == 1) {
        return false; // Es una pared
    }

    

    return true; // Posición válida
}

    private void iniciarMovimientoEnemigos() {// Inicia un hilo separado para mover cada enemigo automáticamente.
        for (Enemigo enemigo : enemigos) {
            new Thread(() -> {
                try {
                    while (true) {
                        moverEnemigo(enemigo);// Mueve al enemigo.
                        Thread.sleep(1000); // Espera 1 segundo entre movimientos.
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public boolean validar(int filaDestino, int columnaDestino) {
        // Validar que las coordenadas estén dentro de los límites del laberinto
        if (filaDestino >= 0 && filaDestino < laberinto.length
                && columnaDestino >= 0 && columnaDestino < laberinto[0].length
                && laberinto[filaDestino][columnaDestino] == 0) {
            return true;
        }
        return false;
    }

    public void caminar(String direccion) { // Mueve al personaje en la dirección indicada, si la posición destino es válida.
        int filaDestino = personaje.getFila();
        int columnaDestino = personaje.getColumna();

        switch (direccion) {
            case "Arriba":
                filaDestino--;
                break;
            case "Abajo":
                filaDestino++;
                break;
            case "Izquierda":
                columnaDestino--;
                break;
            case "Derecha":
                columnaDestino++;
                break;
        }

        if (validar(filaDestino, columnaDestino)) {
            personaje.setFila(filaDestino);
            personaje.setColumna(columnaDestino);
            personaje.setX(coordenadasX[columnaDestino]);
            personaje.setY(coordenadasY[filaDestino]);

            // Verificar si alcanzó una casilla de victoria
            verificarVictoria();
        }
    }

    public boolean verificarVictoria() {
        // Definir las coordenadas de la posición de victoria
        int filaVictoria = 9;
        int columnaVictoria = 20;

        // Verificar si el personaje está en la posición de victoria
        boolean victoria = personaje.getFila() == filaVictoria && personaje.getColumna() == columnaVictoria;

        System.out.println("Verificando victoria: " + victoria + " (Fila: " + personaje.getFila() + ", Columna: " + personaje.getColumna() + ")");

        return victoria;
    }

    // Dibuja al personaje y los enemigos en la interfaz gráfica.
    public void dibujar(Graphics g) {
        if (personaje != null && personaje.getImagen() != null) {
            personaje.show(g); // Dibuja al personaje.
        }
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.getImagen() != null) {
                enemigo.show(g); // Dibuja a cada enemigo.
            }
        }
    }
    public Personaje getPersonaje() {
        return personaje;
    }

    public boolean verificarColisionConEnemigo() {
        for (Enemigo enemigo : enemigos) {
            if (personaje.getFila() == enemigo.getFila() && personaje.getColumna() == enemigo.getColumna()) {
                return true; // El personaje colisionó con un enemigo
            }
        }
        return false;
    }

    // Devuelve el número de vidas restantes.
    public int getVidas() {
        return vidas;
    }

    // Resta una vida al jugador, si aún le quedan vidas.
    public void restarVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public boolean collisionConEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (personaje.getFila() == enemigo.getFila() && personaje.getColumna() == enemigo.getColumna()) {
                return true; // Hay una colisión
            }
        }
        return false; // No hay colisión
    }
}
