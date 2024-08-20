package telas;

import abstracts.Mappings;
import mappings.Associative;
import mappings.Direct;
import mappings.SetAssociative;
import trabalhopratico.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Resultados extends JFrame {

    public Resultados(String configFile, String testFile, String mapeamento, String algoritmoSubstituicao) {
        try {
            Image imgIconImage = ImageIO.read(new File("./img/icone.png"));
            setIconImage(imgIconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> configData = FileManager.stringReader(configFile);
        ArrayList<String> testData = FileManager.stringReader(testFile);

        Mappings cache = iniciarCache(testData, configData, mapeamento, algoritmoSubstituicao);

        cache.readMemory();

        exibirResultados(cache);
    }

    private Mappings iniciarCache(ArrayList<String> memoryData, ArrayList<String> configData, String mapeamento,
            String algoritmoSubstituicao) {
        Mappings cache = null;

        switch (mapeamento) {
            case "Direto":
                cache = new Direct(memoryData, configData);
                break;
            case "Associativo":
                cache = new Associative(memoryData, configData, algoritmoSubstituicao);
                break;
            case "Associativo em Conjunto":
                cache = new SetAssociative(memoryData, configData, algoritmoSubstituicao);
                break;
        }

        return cache;
    }

    private void exibirResultados(Mappings cache) {
        try {
            BufferedImage img = ImageIO.read(new File("./img/fundoResultados.jpg"));
            Font pixeledFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonte/PixelMplus10-Regular.ttf"))
                    .deriveFont(25f);

            setSize(800, 500);
            setTitle("Resultados");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);

            JLabel fundo = new JLabel();
            Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            fundo.setIcon(imageIcon);

            JLabel mostrarAcertos = new JLabel("Acertos:");
            mostrarAcertos.setFont(pixeledFont);
            mostrarAcertos.setForeground(Color.BLACK);
            mostrarAcertos.setBackground(Color.WHITE);
            mostrarAcertos.setOpaque(true);
            mostrarAcertos.setHorizontalAlignment(SwingConstants.CENTER);
            mostrarAcertos.setBounds(350, 73, 150, 30);
            fundo.add(mostrarAcertos);

            JLabel acertos = new JLabel(String.valueOf(cache.getHits()));
            acertos.setFont(pixeledFont);
            acertos.setForeground(Color.BLACK);
            acertos.setBackground(Color.WHITE);
            acertos.setOpaque(true);
            acertos.setHorizontalAlignment(SwingConstants.CENTER);
            acertos.setBounds(505, 73, 80, 30);
            fundo.add(acertos);

            JLabel mostrarFalhas = new JLabel("Falhas:");
            mostrarFalhas.setFont(pixeledFont);
            mostrarFalhas.setForeground(Color.BLACK);
            mostrarFalhas.setBackground(Color.WHITE);
            mostrarFalhas.setOpaque(true);
            mostrarFalhas.setHorizontalAlignment(SwingConstants.CENTER);
            mostrarFalhas.setBounds(350, 153, 150, 30);
            fundo.add(mostrarFalhas);

            JLabel falhas = new JLabel(String.valueOf(cache.getMiss()));
            falhas.setFont(pixeledFont);
            falhas.setForeground(Color.BLACK);
            falhas.setBackground(Color.WHITE);
            falhas.setOpaque(true);
            falhas.setHorizontalAlignment(SwingConstants.CENTER);
            falhas.setBounds(505, 153, 80, 30);
            fundo.add(falhas);

            JLabel mostrarPorcentagem = new JLabel("Resultado:");
            mostrarPorcentagem.setFont(pixeledFont);
            mostrarPorcentagem.setForeground(Color.BLACK);
            mostrarPorcentagem.setBackground(Color.WHITE);
            mostrarPorcentagem.setOpaque(true);
            mostrarPorcentagem.setHorizontalAlignment(SwingConstants.CENTER);
            mostrarPorcentagem.setBounds(350, 233, 150, 30);
            fundo.add(mostrarPorcentagem);

            JLabel porcentagem = new JLabel(
                    String.format("%.2f%%", (float) cache.getHits() / (cache.getHits() + cache.getMiss()) * 100));
            porcentagem.setFont(pixeledFont);
            porcentagem.setForeground(Color.BLACK);
            porcentagem.setBackground(Color.WHITE);
            porcentagem.setOpaque(true);
            porcentagem.setHorizontalAlignment(SwingConstants.CENTER);
            porcentagem.setBounds(505, 233, 100, 30);
            fundo.add(porcentagem);

            JButton voltar = new JButton("Voltar");
            voltar.setFont(pixeledFont);
            voltar.setForeground(Color.BLACK);
            voltar.setBackground(Color.WHITE);
            voltar.setOpaque(true);
            voltar.setBounds(490, 350, 130, 35);
            voltar.setFocusable(false);
            voltar.addActionListener(this::voltarTela);
            fundo.add(voltar);

            add(fundo);
            setVisible(true);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void voltarTela(ActionEvent e) {
        new Configuracoes();
        this.setVisible(false);
    }
}
