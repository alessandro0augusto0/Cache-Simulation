package telas;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TelaInicial extends JFrame {

    BufferedImage img = null;
    Font pixeledFont;
    File music;
    AudioInputStream audioStream;
    Clip clip;

    public TelaInicial() {

        try {
            Image imgIconImage = ImageIO.read(new File("./img/icone.png"));
            setIconImage(imgIconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            music = new File("./sounds/saurontelainicial.wav");
            audioStream = AudioSystem.getAudioInputStream(music);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            img = ImageIO.read(new File("./img/fundo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pixeledFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonte/PixelMplus10-Regular.ttf"))
                    .deriveFont(20f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        setSize(800, 500);
        setTitle("Simulador de memória cache");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel fundo = new JLabel();

        if (img != null) {
            Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            fundo.setIcon(imageIcon);
        }

        JButton iniciar = new JButton("INICIAR JORNADA");
        iniciar.setBounds(325, 350, 200, 50);
        iniciar.setFont(pixeledFont);
        iniciar.setForeground(Color.BLACK);
        iniciar.setBackground(Color.WHITE);
        iniciar.setOpaque(true);
        iniciar.addActionListener(this::rodar);
        iniciar.setBorder(null);
        iniciar.setFocusable(false);
        fundo.add(iniciar);

        add(fundo);

        setVisible(true);

        clip.start();
    }

    private void rodar(ActionEvent e) {
        clip.stop();
        this.dispose();
        new Configuracoes();
    }
}
