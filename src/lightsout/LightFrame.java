/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import java.awt.*;
import static java.awt.Color.RED;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author cspanw74
 */
public class LightFrame extends JFrame  {
    LightGame thisGame = new LightGame (this);
    JButton[][] buttons = new JButton[thisGame.HEIGHT][thisGame.WIDTH];
    final Random rand = new Random();
    boolean onOff;
    public String btnText = "";
    
    Icon imgOn  = new ImageIcon(getClass().getResource("/images/button-on.jpg"));
    Icon imgOff = new ImageIcon(getClass().getResource("/images/button-off.jpg"));
    
    public LightFrame() {
        super("lightsOut!");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel gameWindow = new JPanel();
        GridLayout buttonLayout = new GridLayout(thisGame.HEIGHT, thisGame.WIDTH);
        gameWindow.setLayout(buttonLayout);
        add(gameWindow);
        
        for (int i = 0; i < thisGame.HEIGHT; i++)    {
            for (int j = 0; j < thisGame.WIDTH; j++)    {
                onOff = rand.nextBoolean();
                btnText = onOff ? "+" : "";
                thisGame.btnStates[i][j] = onOff;
                JButton nextBtn = new JButton(btnText);
//                nextBtn.setBackground(RED);
//                nextBtn.setOpaque(onOff);
                nextBtn.putClientProperty("row", i);
                nextBtn.putClientProperty("column", j);
//                nextBtn.setContentAreaFilled(false);
//                nextBtn.setIcon(imgOn);
                nextBtn.addActionListener(thisGame);
                gameWindow.add(nextBtn);
                buttons[i][j] = nextBtn;
            }
        }
        
        setVisible(true);
    }
}
