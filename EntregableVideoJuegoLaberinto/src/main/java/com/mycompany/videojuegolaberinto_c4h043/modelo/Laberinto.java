/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.modelo;

/**
 *
 * @author mcalf
 */
public class Laberinto {
    // Clase que representa el laberinto del juego, incluyendo el mapa, el jugador y un adversario.

    private int[][] mapa; // Matriz que representa el mapa del laberinto (1 = pared, 0 = espacio libre).
    private Personaje jugador; // Objeto que representa al jugador en el laberinto.
    private Enemigo adversario; // Objeto que representa al enemigo (adversario) en el laberinto.

    // Constructor que inicializa el laberinto.
    public Laberinto() {
        // Inicializa el mapa del laberinto con una matriz predefinida.
        this.mapa = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Fila 0
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // Fila 1
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // Fila 2
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1}, // Fila 3
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // Fila 4
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // Fila 5
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1}, // Fila 6
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1}, // Fila 7
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1}, // Fila 8
            {1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1}, // Fila 9
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 2}, // Fila 10
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 2}, // Fila 11
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // Fila 12
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // Fila 13
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // Fila 14
            {1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1}, // Fila 15
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // Fila 16
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // Fila 17
            {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // Fila 18
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1}, // Fila 19
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1}, // Fila 20
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}  // Fila 21
        };

        jugador = new Personaje(1, 1); // Inicializa al jugador en la posición (1, 1).
        adversario = new Enemigo(8, 10, mapa); // Inicializa al adversario en la posición (8, 10).
    }

    // Método que verifica si un movimiento es válido.
    public boolean esMovimientoValido(int fila, int columna) {
        // Comprueba que la posición esté dentro de los límites del laberinto.
        if (fila >= 0 && fila < mapa.length && columna >= 0 && columna < mapa[0].length) {
            return mapa[fila][columna] == 0; // Devuelve true si la casilla no es una pared.
        }
        return false; // Devuelve false si está fuera de los límites o es una pared.
    }

    // Getter para obtener el mapa del laberinto.
    public int[][] getMapa() {
        return mapa; // Devuelve la matriz que representa el laberinto.
    }

    // Getter para obtener el jugador.
    public Personaje getJugador() {
        return jugador; // Devuelve el objeto que representa al jugador.
    }

    // Getter para obtener al adversario.
    public Enemigo getAdversario() {
        return adversario; // Devuelve el objeto que representa al adversario.
    }

    // Método que verifica si hay una colisión entre el jugador y el adversario.
    public boolean hayColision() {
        return jugador.getX() == adversario.getMovX() && jugador.getY() == adversario.getMovY();
        // Devuelve true si las coordenadas del jugador y el adversario coinciden.
    }
}

