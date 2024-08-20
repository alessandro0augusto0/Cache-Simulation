package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teste {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Teste window = new Teste();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Teste() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 767, 463);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Selecione o arquivo de teste:");
        lblNewLabel.setBounds(33, 53, 151, 36);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "Teste 1", "Teste 2" }));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(227, 60, 107, 22);
        frame.getContentPane().add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("Selecione o tipo de mapeamento:");
        lblNewLabel_1.setBounds(33, 149, 169, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Direto", "Associativo FIFO", "Associativo LRU",
                "Associativo LFU", "Associativo Aleatório", "Associativo em Conjunto" }));
        comboBox_1.setBounds(227, 145, 212, 22);
        frame.getContentPane().add(comboBox_1);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "fsdf", "sdfg", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnNewButton.setBounds(33, 251, 89, 23);
        frame.getContentPane().add(btnNewButton);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
