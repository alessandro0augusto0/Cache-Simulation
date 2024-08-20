package telas;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Configuracoes extends JFrame {

    JComboBox<String> arquivoTeste;
    JComboBox<String> mapeamento;
    JComboBox<String> algoritmoSubstituicao;
    JComboBox<String> arquivoConfig;
    Font pixeledFont;
    BufferedImage img = null;
    File music;
    AudioInputStream audioStream;
    Clip clip;

    public Configuracoes() {
        try {
            Image imgIconImage = ImageIO.read(new File("./img/icone.png"));
            setIconImage(imgIconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            music = new File("./sounds/jrrtolkientrack1.wav");
            audioStream = AudioSystem.getAudioInputStream(music);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            img = ImageIO.read(new File("./img/fundoConfig.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pixeledFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonte/PixelMplus10-Regular.ttf"))
                    .deriveFont(25f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        setSize(800, 500);
        setTitle("Simulador de memória cache");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel fundo = new JLabel();
        Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        fundo.setIcon(imageIcon);

        JLabel opcoes_Mapeamento = new JLabel("Tipo de mapeamento:");
        opcoes_Mapeamento.setFont(pixeledFont);
        opcoes_Mapeamento.setBounds(130, 185, 280, 30);
        opcoes_Mapeamento.setOpaque(true);
        opcoes_Mapeamento.setHorizontalAlignment(SwingConstants.CENTER);
        opcoes_Mapeamento.setBackground(Color.WHITE);
        opcoes_Mapeamento.setForeground(Color.BLACK);
        fundo.add(opcoes_Mapeamento);

        mapeamento = new JComboBox<>(new String[] { "Direto", "Associativo", "Associativo em Conjunto" });
        mapeamento.setFont(pixeledFont);
        mapeamento.setBounds(425, 185, 225, 30);
        mapeamento.setBackground(Color.WHITE);
        mapeamento.setForeground(Color.BLACK);
        mapeamento.addActionListener(this::onMapeamentoChanged);
        fundo.add(mapeamento);

        algoritmoSubstituicao = new JComboBox<>(new String[] { "LRU", "LFU", "FIFO", "Aleatório" });
        algoritmoSubstituicao.setFont(pixeledFont);
        algoritmoSubstituicao.setBounds(425, 245, 225, 30);
        algoritmoSubstituicao.setBackground(Color.WHITE);
        algoritmoSubstituicao.setForeground(Color.BLACK);
        algoritmoSubstituicao.setVisible(false); // Inicialmente invisível até ser necessário
        fundo.add(algoritmoSubstituicao);

        JLabel opcoes_Teste = new JLabel("Escolha o arquivo de teste:");
        opcoes_Teste.setFont(pixeledFont);
        opcoes_Teste.setBounds(130, 125, 280, 30);
        opcoes_Teste.setOpaque(true);
        opcoes_Teste.setHorizontalAlignment(SwingConstants.CENTER);
        opcoes_Teste.setBackground(Color.WHITE);
        opcoes_Teste.setForeground(Color.BLACK);
        fundo.add(opcoes_Teste);

        arquivoTeste = new JComboBox<>(
                new String[] { "./dados/teste_1.txt", "./dados/teste_2.txt", "./dados/avaliacao.txt" });
        arquivoTeste.setFont(pixeledFont);
        arquivoTeste.setBounds(425, 125, 225, 30);
        arquivoTeste.setBackground(Color.WHITE);
        arquivoTeste.setForeground(Color.BLACK);
        fundo.add(arquivoTeste);

        JLabel opcoes_Config = new JLabel("Escolha o arquivo de configuração:");
        opcoes_Config.setFont(pixeledFont);
        opcoes_Config.setBounds(130, 65, 280, 30);
        opcoes_Config.setOpaque(true);
        opcoes_Config.setHorizontalAlignment(SwingConstants.CENTER);
        opcoes_Config.setBackground(Color.WHITE);
        opcoes_Config.setForeground(Color.BLACK);
        fundo.add(opcoes_Config);

        arquivoConfig = new JComboBox<>(new String[] { "./dados/config.txt", "./dados/config2.txt" });
        arquivoConfig.setFont(pixeledFont);
        arquivoConfig.setBounds(425, 65, 225, 30);
        arquivoConfig.setBackground(Color.WHITE);
        arquivoConfig.setForeground(Color.BLACK);
        fundo.add(arquivoConfig);

        JButton ok = new JButton("OK");
        ok.setFont(pixeledFont);
        ok.setBounds(330, 350, 100, 35);
        ok.setFocusable(false);
        ok.setBackground(Color.WHITE);
        ok.setForeground(Color.BLACK);
        ok.addActionListener(this::rodar);
        fundo.add(ok);

        add(fundo);
        setVisible(true);
        clip.start();
    }

    private void onMapeamentoChanged(ActionEvent e) {
        String selectedMapeamento = (String) mapeamento.getSelectedItem();
        if ("Associativo".equals(selectedMapeamento) || "Associativo em Conjunto".equals(selectedMapeamento)) {
            algoritmoSubstituicao.setVisible(true);
        } else {
            algoritmoSubstituicao.setVisible(false);
        }
    }

    private void rodar(ActionEvent e) {
        clip.stop();
        String configFile = (String) arquivoConfig.getSelectedItem();
        String testFile = (String) arquivoTeste.getSelectedItem();
        String selectedMapeamento = (String) mapeamento.getSelectedItem();
        String selectedAlgoritmo = (String) algoritmoSubstituicao.getSelectedItem();

        new Resultados(configFile, testFile, selectedMapeamento, selectedAlgoritmo);
        this.dispose();
    }
}
