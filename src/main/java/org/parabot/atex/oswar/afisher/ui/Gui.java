package org.parabot.atex.oswar.afisher.ui;

import org.parabot.atex.oswar.afisher.data.Fish;
import org.parabot.atex.oswar.afisher.data.Settings;
import org.parabot.environment.api.utils.Time;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private Settings settings;

    private JPanel contentPane;
    private JButton startButton;
    private JComboBox cmbSelectFish;
    private JCheckBox chkPowerlevel;
    private JCheckBox chkAutoProgress;

    /**
     * Method for testing purposes
     *
     * @param args
     */
    public static void main(String[] args) {
        Gui gui = new Gui();
        while(gui.isVisible()) {
            Time.sleep(100);
        }
    }

    public Gui() throws HeadlessException {
        setTitle("Configure script");
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(startButton);

        for(Fish fish : Fish.values()) {
            cmbSelectFish.addItem(fish);
        }

        pack();
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings = new Settings((Fish)cmbSelectFish.getSelectedItem(), chkPowerlevel.isSelected(), chkAutoProgress.isSelected());
                dispose();
            }
        });
        chkAutoProgress.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cmbSelectFish.setEnabled(!((JCheckBox)e.getSource()).isSelected());
            }
        });
    }

    public Settings getSettings() {
        return settings;
    }
}
