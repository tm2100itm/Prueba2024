/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.controlador;

import com.mycompany.videojuegolaberinto_c4h043.modelo.Laberinto;
import com.mycompany.videojuegolaberinto_c4h043.vista.GUIHistoria;
import com.mycompany.videojuegolaberinto_c4h043.vista.GUIInstrucciones;
import com.mycompany.videojuegolaberinto_c4h043.vista.GUILaberinto;
import com.mycompany.videojuegolaberinto_c4h043.vista.GUIPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mcalf
 */
public class ControladorPrincipal implements ActionListener {
    // Clase principal que actúa como controlador para manejar la interacción entre las diferentes ventanas del juego.
    // Implementa ActionListener para manejar eventos de acción generados por los botones.

    private GUIPrincipal guiPrincipal;        // Ventana principal del menú.
    private GUIHistoria guiHistoria;          // Ventana que muestra la historia del juego.
    private GUIInstrucciones guiInstrucciones;// Ventana que muestra las instrucciones del juego.
    private GUILaberinto guiLaberinto;        // Ventana del juego del laberinto.
    private Laberinto laberinto;              // Modelo que representa el laberinto.

    // Constructor que inicializa las ventanas y configura el controlador.
    public ControladorPrincipal() {
        // Inicializa las ventanas y les asigna la referencia del controlador actual.
        guiPrincipal = new GUIPrincipal(this);
        guiInstrucciones = new GUIInstrucciones(this);
        guiHistoria = new GUIHistoria(this);

        laberinto = new Laberinto(); // Crea una instancia del modelo del laberinto.

        guiPrincipal.escuchar(this); // Vincula el controlador con la ventana principal.

        // Hace visible la ventana principal al inicio del programa.
        guiPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Método que maneja las acciones de los botones en las diferentes ventanas.
        String comando = e.getActionCommand(); // Obtiene el comando asociado al botón presionado.

        switch (comando) {
            case "instrucciones":
                // Cambia de la ventana principal a la ventana de instrucciones.
                guiPrincipal.setVisible(false); // Oculta la ventana principal.
                guiInstrucciones.setVisible(true); // Muestra la ventana de instrucciones.
                break;

            case "salirInstrucciones":
                // Cambia de la ventana de instrucciones a la ventana principal.
                guiInstrucciones.setVisible(false); // Oculta la ventana de instrucciones.
                guiPrincipal.setVisible(true); // Muestra la ventana principal.
                break;

            case "historia":
                // Cambia de la ventana principal a la ventana de historia.
                guiPrincipal.setVisible(false); // Oculta la ventana principal.
                guiHistoria.setVisible(true); // Muestra la ventana de historia.
                break;

            case "salirHistoria":
                // Cambia de la ventana de historia a la ventana principal.
                guiHistoria.setVisible(false); // Oculta la ventana de historia.
                guiPrincipal.setVisible(true); // Muestra la ventana principal.
                break;

            case "jugar":
                // Cambia de la ventana principal al juego del laberinto.
                guiPrincipal.setVisible(false); // Oculta la ventana principal.
                try {
                    Laberinto laberinto = new Laberinto(); // Crea una nueva instancia del modelo del laberinto.
                    guiLaberinto = new GUILaberinto(laberinto); // Inicializa la ventana del juego del laberinto.
                    guiLaberinto.setVisible(true); // Muestra la ventana del juego.
                } catch (Exception ex) {
                    // Maneja posibles errores al inicializar el juego.
                    ex.printStackTrace(); // Imprime el error en la consola para depuración.
                    JOptionPane.showMessageDialog(
                        null,
                        "Ha ocurrido un error al iniciar el juego.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    ); // Muestra un mensaje de error al usuario.
                }
                break;

            case "salirJuego":
                // Regresa del juego del laberinto a la ventana principal.
                guiLaberinto.getPanelJuego().addEscuchador(this); // Agrega un escuchador para eventos del panel de juego.
                guiLaberinto.setVisible(false); // Oculta la ventana del juego.
                guiPrincipal.setVisible(true); // Muestra la ventana principal.
                break;

            case "salir":
                // Cierra completamente la aplicación.
                System.exit(0); // Termina la ejecución del programa.
                break;
        }
    }
}

