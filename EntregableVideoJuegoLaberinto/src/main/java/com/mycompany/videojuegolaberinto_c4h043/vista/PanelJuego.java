/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.videojuegolaberinto_c4h043.vista;

import com.mycompany.videojuegolaberinto_c4h043.controlador.ControladorLaberinto;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author mcalf
 */
public class PanelJuego extends JPanel {
    // Clase que representa el panel donde se dibuja el laberinto y se maneja la interacción del jugador.
    // Extiende JPanel para permitir personalización de gráficos e interacción.

    private ControladorLaberinto controlador; // Controlador encargado de manejar la lógica del laberinto.
    private Image laberintoImage; // Imagen de fondo que representa el laberinto.
    private int tiempoRestante = 30; // Tiempo inicial restante en segundos.
    private int vidasRestantes = 5; // Número de vidas restantes del jugador.

    /**
     * Constructor de la clase PanelJuego.
     * @param controlador Objeto de la clase ControladorLaberinto encargado de manejar la lógica del juego.
     */
    public PanelJuego(ControladorLaberinto controlador) {
        this.controlador = controlador; // Asocia el controlador con este panel.

        // Carga la imagen del laberinto desde los recursos.
        laberintoImage = new ImageIcon(getClass().getResource("/Laberinto1.jpg")).getImage();

        setFocusable(true); // Hace el panel enfocable para recibir eventos de teclado.
        addKeyListener(controlador); // Vincula el controlador para manejar eventos de teclado.

        // Agrega un MouseListener para detectar clics en el panel.
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtiene las coordenadas del clic del mouse.
                int x = e.getX();
                int y = e.getY();

                // Define márgenes y tamaños de las celdas del laberinto lógico.
                int inicioX = 25; // Margen izquierdo.
                int inicioY = 25; // Margen superior.
                int cuadroWidth = 25; // Ancho de una celda.
                int cuadroHeight = 25; // Alto de una celda.

                // Calcula la fila y columna basadas en el clic.
                int columna = (x - inicioX) / cuadroWidth;
                int fila = (y - inicioY) / cuadroHeight;

                // Verifica si el clic está dentro del área lógica del laberinto.
                if (fila >= 0 && fila < 22 && columna >= 0 && columna < 22) {
                    System.out.println("Clic detectado: Fila " + fila + ", Columna " + columna);
                    System.out.println("Coordenadas reales: X = " + x + ", Y = " + y);
                } else {
                    System.out.println("Clic fuera del área del laberinto.");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Método no utilizado.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Método no utilizado.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Método no utilizado.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Método no utilizado.
            }
        });
    }

// Asocia un nuevo controlador al panel.

    public void setControlador(ControladorLaberinto controlador) {
        this.controlador = controlador; // Actualiza el controlador.
        addKeyListener(controlador); // Vincula el nuevo controlador con el panel.
    }

//Método que se encarga de dibujar el contenido del panel.

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método base para limpiar el panel.

        // Dibuja la imagen del laberinto como fondo.
        if (laberintoImage != null) {
            g.drawImage(laberintoImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Dibuja personajes y enemigos mediante el controlador.
        if (controlador != null) {
            controlador.dibujar(g);
        }

        // Dibuja el tiempo restante en la esquina superior derecha.
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Tiempo: " + tiempoRestante + "s", getWidth() - 165, 200);

        // Dibuja las vidas restantes en el centro del borde derecho.
        g.drawString("Vidas: " + vidasRestantes, getWidth() - 165, 240);
    }

// Actualiza el tiempo restante mostrado en el panel.

    public void actualizarTiempoRestante(int tiempo) {
        this.tiempoRestante = tiempo; // Actualiza el tiempo restante.
        repaint(); // Fuerza el repintado del panel para reflejar el cambio.
    }

  // Actualiza el número de vidas restantes mostrado en el panel.
 
    public void actualizarVidasRestantes(int vidas) {
        vidasRestantes = vidas; // Actualiza las vidas restantes.
        repaint(); // Fuerza el repintado del panel para reflejar el cambio.
    }


    @Override
    public boolean isFocusable() {
        return true;
    }


    @Override
    public void addNotify() {
        super.addNotify(); // Llama al método base.
        requestFocus(); // Solicita el foco para recibir eventos de teclado.
    }

 
    public void addEscuchador(ActionListener listener) {
        btnSalir.addActionListener(listener); // Vincula el listener con el botón "Salir".
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btnSalirX.png"))); // NOI18N
        btnSalir.setActionCommand("salirJugar");
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setDefaultCapable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    // End of variables declaration//GEN-END:variables
}
