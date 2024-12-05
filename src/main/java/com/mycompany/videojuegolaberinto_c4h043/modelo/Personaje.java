/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.modelo;

/**
 *
 * @author mcalf
 */
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Personaje {
    // Clase que representa al personaje controlado por el jugador dentro del juego.
    // Incluye atributos para su posición en el laberinto y la imagen que lo representa.

    private int x; // Coordenada X en píxeles para dibujar el personaje en la pantalla.
    private int y; // Coordenada Y en píxeles para dibujar el personaje en la pantalla.
    private int fila; // Fila del personaje en la matriz del laberinto (posición lógica).
    private int columna; // Columna del personaje en la matriz del laberinto (posición lógica).
    private ImageIcon imagen; // Imagen asociada al personaje para representarlo visualmente.

    // Constructor que inicializa las coordenadas en píxeles y la imagen asociada al personaje.
    public Personaje(int x, int y, ImageIcon imagen) {
        if (imagen == null) { // Verifica que la imagen no sea nula.
            throw new IllegalArgumentException("La imagen del personaje no puede ser nula.");
        }
        this.x = x; // Asigna la coordenada X inicial.
        this.y = y; // Asigna la coordenada Y inicial.
        this.imagen = imagen; // Asigna la imagen al personaje.
    }

    // Constructor que inicializa las coordenadas en la matriz, las coordenadas en píxeles y la imagen.
    public Personaje(int fila, int columna, int x, int y, ImageIcon imagen) {
        if (imagen == null) { // Verifica que la imagen no sea nula.
            throw new IllegalArgumentException("La imagen del personaje no puede ser nula.");
        }
        this.fila = fila; // Asigna la fila inicial en la matriz del laberinto.
        this.columna = columna; // Asigna la columna inicial en la matriz del laberinto.
        this.x = x; // Asigna la coordenada X inicial en píxeles.
        this.y = y; // Asigna la coordenada Y inicial en píxeles.
        this.imagen = imagen; // Asocia la imagen al personaje.
    }

    // Constructor para inicialización básica del personaje, sin imagen.
    public Personaje(int x, int y) {
        this.x = x; // Asigna la coordenada X inicial.
        this.y = y; // Asigna la coordenada Y inicial.
        this.fila = 0; // Fila predeterminada (por defecto).
        this.columna = 0; // Columna predeterminada (por defecto).
        this.imagen = null; // No hay imagen asociada por defecto.
    }

    // Getters y setters para las coordenadas en píxeles (X e Y).

    public int getX() {
        return x; // Devuelve la coordenada X en píxeles.
    }

    public void setX(int x) {
        this.x = x; // Establece una nueva coordenada X en píxeles.
    }

    public int getY() {
        return y; // Devuelve la coordenada Y en píxeles.
    }

    public void setY(int y) {
        this.y = y; // Establece una nueva coordenada Y en píxeles.
    }

    // Getters y setters para las coordenadas en la matriz del laberinto (fila y columna).

    public int getFila() {
        return fila; // Devuelve la fila actual del personaje en la matriz.
    }

    public boolean getFilas() {
        return true; // Método placeholder, parece no ser funcional en este contexto.
    }

    public void setFila(int fila) {
        this.fila = fila; // Establece una nueva fila para el personaje en la matriz.
    }

    public int getColumna() {
        return columna; // Devuelve la columna actual del personaje en la matriz.
    }

    public void setColumna(int columna) {
        this.columna = columna; // Establece una nueva columna para el personaje en la matriz.
    }

    // Getter y setter para la imagen asociada al personaje.

    public ImageIcon getImagen() {
        return imagen; // Devuelve la imagen del personaje.
    }

    public void setImagen(ImageIcon imagen) {
        if (imagen == null) { // Verifica que la imagen no sea nula antes de asignarla.
            throw new IllegalArgumentException("La imagen no puede ser nula.");
        }
        this.imagen = imagen; // Asocia una nueva imagen al personaje.
    }

    // Método para mostrar la imagen del personaje en la pantalla en las coordenadas actuales.
    public void show(Graphics g) {
        if (imagen != null && g != null) { // Verifica que tanto la imagen como el objeto Graphics sean válidos.
            imagen.paintIcon(null, g, x, y); // Dibuja la imagen del personaje en las coordenadas especificadas.
        } else if (g == null) { // Si Graphics es nulo, muestra un mensaje de error.
            System.err.println("El objeto Graphics es nulo.");
        }
    }

    // Método para mover al personaje basado en una dirección y las restricciones del laberinto.
    public void mover(String direccion, int[][] laberinto, int[] coordenadasX, int[] coordenadasY) {
        // Verifica que ninguno de los parámetros sea nulo.
        if (direccion == null || laberinto == null || coordenadasX == null || coordenadasY == null) {
            throw new IllegalArgumentException("Ningún parámetro puede ser nulo.");
        }

        int filaDestino = fila; // Inicializa la fila destino con la fila actual.
        int columnaDestino = columna; // Inicializa la columna destino con la columna actual.

        // Cambia la fila o columna destino dependiendo de la dirección especificada.
        switch (direccion) {
            case "Arriba":
                filaDestino--; // Mueve hacia arriba (reduce la fila).
                break;
            case "Abajo":
                filaDestino++; // Mueve hacia abajo (aumenta la fila).
                break;
            case "Izquierda":
                columnaDestino--; // Mueve hacia la izquierda (reduce la columna).
                break;
            case "Derecha":
                columnaDestino++; // Mueve hacia la derecha (aumenta la columna).
                break;
            default:
                System.err.println("Dirección inválida: " + direccion); // Muestra un error si la dirección no es válida.
                return; // Sale del método sin hacer nada.
        }

        // Valida si el movimiento es posible dentro de los límites del laberinto y no hacia una pared.
        if (filaDestino >= 0 && filaDestino < laberinto.length
                && columnaDestino >= 0 && columnaDestino < laberinto[0].length
                && laberinto[filaDestino][columnaDestino] == 0) {

            // Actualiza la posición en la matriz.
            fila = filaDestino;
            columna = columnaDestino;

            // Actualiza las coordenadas en píxeles.
            x = coordenadasX[columnaDestino];
            y = coordenadasY[filaDestino];
        } else {
            // Muestra un error si el movimiento no es válido.
            System.err.println("Movimiento inválido: fila=" + filaDestino + ", columna=" + columnaDestino);
        }
    }
}
