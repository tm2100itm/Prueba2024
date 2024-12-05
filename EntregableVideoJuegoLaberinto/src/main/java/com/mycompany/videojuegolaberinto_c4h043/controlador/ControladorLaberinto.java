/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.controlador;

/**
 *
 * @author mcalf
 */
import java.awt.event.KeyListener;
import com.mycompany.videojuegolaberinto_c4h043.modelo.AreaJuego;
import com.mycompany.videojuegolaberinto_c4h043.modelo.Enemigo;
import com.mycompany.videojuegolaberinto_c4h043.modelo.Personaje;
import com.mycompany.videojuegolaberinto_c4h043.vista.GUILaberinto;
import com.mycompany.videojuegolaberinto_c4h043.vista.PanelJuego;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class ControladorLaberinto implements KeyListener { 
    // Clase que actúa como controlador del juego del laberinto.
    // Implementa KeyListener para manejar eventos del teclado.

    private PanelJuego panelJuego; // Vista encargada de mostrar el área del juego al usuario.
    private AreaJuego areaJuego;   // Modelo que gestiona la lógica del juego y los datos del laberinto.
    private GUILaberinto guiLaberinto; // Referencia a la interfaz gráfica principal del laberinto.
    private Timer timer; // Temporizador para manejar el tiempo restante del juego.
    private int tiempo;  // Tiempo inicial del juego.
    private int tiempoRestante; // Tiempo que queda antes de que se acabe el juego.
    private boolean juegoTerminado = false; // Indica si el juego ha terminado.
    private int vidas = 5; // Cantidad inicial de vidas del jugador.

    // Constructor de la clase
    public ControladorLaberinto(PanelJuego panelJuego, int[][] laberinto, GUILaberinto guiLaberinto) {
        // Inicializa el controlador, vinculando la vista y el modelo.
        if (panelJuego == null || laberinto == null) {
            throw new IllegalArgumentException("PanelJuego y laberinto no pueden ser nulos."); // Verifica que no sean nulos.
        }
        this.panelJuego = panelJuego;
        this.guiLaberinto = guiLaberinto;
        this.areaJuego = new AreaJuego(laberinto, guiLaberinto); // Crea el modelo con el laberinto.

        iniciarTemporizador(); // Inicia el temporizador al crear el controlador.
    }

    // Método para verificar si el jugador chocó con un enemigo.
    public void verificarColision() {
        if (areaJuego.collisionConEnemigos()) { // Comprueba si hubo colisión con un enemigo.
            areaJuego.restarVida(); // Resta una vida al jugador.
            panelJuego.actualizarVidasRestantes(areaJuego.getVidas()); // Actualiza las vidas en la interfaz gráfica.

            if (areaJuego.getVidas() <= 0) { // Si el jugador se queda sin vidas.
                detenerTemporizador(); // Detiene el temporizador.
                guiLaberinto.mostrarMensaje("¡Te has quedado sin vidas!"); // Muestra un mensaje al usuario.
            }
        }
    }

    // Método para inicializar el temporizador.
    private void iniciarTemporizador() {
        if (timer != null && timer.isRunning()) {
            System.out.println("El temporizador ya está en ejecución.");
            return; // Evita reiniciar el temporizador si ya está corriendo.
        }

        tiempo = 30; // Establece el tiempo inicial en 30 segundos.
        juegoTerminado = false; // Reinicia el estado del juego.

        // Crea un temporizador que se ejecuta cada segundo.
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juegoTerminado) { // Si el juego terminó, detiene el temporizador.
                    System.out.println("El temporizador detectó que el juego terminó. Deteniéndose...");
                    detenerTemporizador();
                    return;
                }

                tiempo--; // Reduce el tiempo restante en 1 segundo.
                panelJuego.actualizarTiempoRestante(tiempo); // Actualiza la interfaz con el tiempo restante.

                if (tiempo <= 0) { // Si el tiempo se agota.
                    System.out.println("Tiempo agotado.");
                    detenerTemporizador();
                    guiLaberinto.mostrarMensaje("¡Tiempo agotado! Intenta de nuevo."); // Muestra un mensaje al usuario.
                }
            }
        });

        timer.start(); // Inicia el temporizador.
        System.out.println("Temporizador iniciado.");
    }

    // Verifica si el jugador ha ganado.
    public void verificarVictoria() {
        if (juegoTerminado) { // Si el juego ya terminó, no verifica victoria.
            System.out.println("El juego ya terminó, no se verifica victoria nuevamente.");
            return;
        }

        if (areaJuego.verificarVictoria()) { // Comprueba si el jugador alcanzó la meta.
            System.out.println("¡Victoria detectada!");
            detenerTemporizador(); // Detiene el temporizador.
            guiLaberinto.mostrarMensaje("¡Felicidades, ganaste el juego!"); // Muestra un mensaje de victoria.
            juegoTerminado = true; // Marca el juego como terminado.
        }
    }

    // Detiene el temporizador.
    public void detenerTemporizador() {
        if (timer != null && timer.isRunning()) { // Si el temporizador está activo.
            timer.stop(); // Lo detiene.
            tiempo = 0; // Establece el tiempo en 0.
            panelJuego.actualizarTiempoRestante(tiempo); // Actualiza la interfaz.
        }
    }

    // Método para mover al jugador en una dirección específica.
    public void moverJugador(String direccion) {
        areaJuego.caminar(direccion); // Actualiza la posición del jugador en el modelo.
        panelJuego.repaint(); // Redibuja la interfaz gráfica.
        verificarColision(); // Verifica si el jugador chocó con un enemigo.
        verificarVictoria(); // Verifica si el jugador ganó.
    }

    // Dibuja los elementos del laberinto en el área de juego.
    public void dibujar(Graphics g) {
        areaJuego.dibujar(g); // Llama al modelo para dibujar el estado actual del juego.
    }

    // Mueve un enemigo en el área de juego.
    public void moverEnemigo(Enemigo enemigo) {
        areaJuego.moverEnemigo(enemigo); // Actualiza la posición del enemigo en el modelo.
        panelJuego.repaint(); // Redibuja la interfaz gráfica.
    }

    // Métodos de teclado.

    @Override
    public void keyPressed(KeyEvent e) {
        // Maneja las teclas presionadas para mover al jugador.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moverJugador("Arriba"); // Mueve al jugador hacia arriba.
                break;
            case KeyEvent.VK_DOWN:
                moverJugador("Abajo"); // Mueve al jugador hacia abajo.
                break;
            case KeyEvent.VK_RIGHT:
                moverJugador("Derecha"); // Mueve al jugador hacia la derecha.
                break;
            case KeyEvent.VK_LEFT:
                moverJugador("Izquierda"); // Mueve al jugador hacia la izquierda.
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se utiliza, pero es necesario implementarlo.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No se utiliza, pero es necesario implementarlo.
    }

    // Getters y setters necesarios para manipular los datos.

    public PanelJuego getPanelJuego() {
        return panelJuego; // Devuelve la referencia al panel de juego.
    }

    public void setPanelJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego; // Asigna un nuevo panel de juego.
    }

    public AreaJuego getAreaJuego() {
        return areaJuego; // Devuelve la referencia al modelo del área de juego.
    }

    public void setAreaJuego(AreaJuego areaJuego) {
        this.areaJuego = areaJuego; // Asigna un nuevo modelo del área de juego.
    }
}

