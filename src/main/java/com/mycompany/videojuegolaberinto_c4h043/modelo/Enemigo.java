/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author mcalf
 */
public class Enemigo {
    // Clase que representa un enemigo en el juego del laberinto.
    // Incluye propiedades como posición, imagen asociada y dirección de movimiento.

    private int x; // Coordenada X en píxeles (posición gráfica en el área del juego).
    private int y; // Coordenada Y en píxeles (posición gráfica en el área del juego).
    private int fila; // Fila en la matriz del laberinto (posición lógica en la estructura del juego).
    private int columna; // Columna en la matriz del laberinto (posición lógica en la estructura del juego).
    private ImageIcon imagen; // Imagen que representa visualmente al enemigo.
    private int direccion; // Dirección actual del enemigo (0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha).

    // Constructor principal que inicializa al enemigo con una posición, coordenadas en píxeles e imagen específica.
    public Enemigo(int fila, int columna, int x, int y, ImageIcon imagen) {
        this.fila = fila; // Asigna la fila inicial del enemigo en la matriz del laberinto.
        this.columna = columna; // Asigna la columna inicial del enemigo en la matriz del laberinto.
        this.x = x; // Asigna la coordenada X en píxeles (posición gráfica).
        this.y = y; // Asigna la coordenada Y en píxeles (posición gráfica).
        this.imagen = imagen; // Asocia la imagen al enemigo.
    }

    // Constructor adicional que inicializa al enemigo con posición lógica y mapa.
    // Calcula automáticamente las coordenadas en píxeles en base a la posición en la matriz.
    public Enemigo(int fila, int columna, int[][] mapa) {
        this.fila = fila; // Asigna la fila inicial en la matriz del laberinto.
        this.columna = columna; // Asigna la columna inicial en la matriz del laberinto.
        this.x = columna * 25; // Calcula la coordenada X en píxeles (multiplicando la columna por el tamaño de celda).
        this.y = fila * 25; // Calcula la coordenada Y en píxeles (multiplicando la fila por el tamaño de celda).
        this.imagen = new ImageIcon("resources/DETRÁS-3.gif"); // Asigna una imagen predeterminada al enemigo.
    }

    // Métodos getter y setter para la coordenada X en píxeles.

    public int getX() {
        return x; // Devuelve la coordenada X en píxeles del enemigo.
    }

    public void setX(int x) {
        this.x = x; // Establece una nueva coordenada X en píxeles para el enemigo.
    }

    // Métodos getter y setter para la coordenada Y en píxeles.

    public int getY() {
        return y; // Devuelve la coordenada Y en píxeles del enemigo.
    }

    public void setY(int y) {
        this.y = y; // Establece una nueva coordenada Y en píxeles para el enemigo.
    }

    // Métodos getter y setter para la dirección del enemigo.

    public int getDireccion() {
        return direccion; // Devuelve la dirección actual del enemigo.
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion; // Establece una nueva dirección para el enemigo.
    }

    // Métodos getter y setter para la fila en la matriz del laberinto.

    public int getFila() {
        return fila; // Devuelve la fila actual del enemigo en la matriz.
    }

    public void setFila(int fila) {
        this.fila = fila; // Establece una nueva fila para el enemigo en la matriz.
    }

    // Métodos getter y setter para la columna en la matriz del laberinto.

    public int getColumna() {
        return columna; // Devuelve la columna actual del enemigo en la matriz.
    }

    public void setColumna(int columna) {
        this.columna = columna; // Establece una nueva columna para el enemigo en la matriz.
    }

    // Métodos getter y setter para la imagen del enemigo.

    public ImageIcon getImagen() {
        return imagen; // Devuelve la imagen asociada al enemigo.
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen; // Asocia una nueva imagen al enemigo.
    }

    // Métodos getter para obtener las coordenadas en píxeles (movimiento).
    // Se utilizan para verificar colisiones o calcular desplazamientos.

    public int getMovX() {
        return x; // Devuelve la coordenada X en píxeles del enemigo.
    }

    public int getMovY() {
        return y; // Devuelve la coordenada Y en píxeles del enemigo.
    }

    // Método para mostrar al enemigo en la pantalla.
    // Dibuja la imagen del enemigo en las coordenadas X e Y actuales.

    public void show(Graphics g) {
        if (imagen != null) { // Verifica si el enemigo tiene una imagen asociada.
            imagen.paintIcon(null, g, x, y); // Dibuja la imagen del enemigo en las coordenadas especificadas.
        }
    }
}

