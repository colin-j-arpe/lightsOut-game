/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author cspanw74
 */
public class LightFrame extends JFrame  {
    LightGame thisGame = new LightGame (this);
    JButton[] buttons = new JButton[60];
    
    public LightFrame() {
        super("lightsOut!");
        setSize(400, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel gameWindow = new JPanel();
        GridLayout buttonLayout = new GridLayout(6, 10);
        gameWindow.setLayout(buttonLayout);
        add(gameWindow);
        
        for (int i = 0; i < 60; i++)    {
            JButton nextBtn = new JButton("");
//            nextBtn.addActionListener(thisGame);
            gameWindow.add(nextBtn);
            buttons[i] = nextBtn;
        }
        
        setVisible(true);
    }
}
