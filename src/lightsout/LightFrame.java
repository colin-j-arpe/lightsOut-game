/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import java.awt.*;
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
    final int WIDTH = 10;
    final int HEIGHT = 6;
    JButton[][] buttons = new JButton[WIDTH][HEIGHT];
    final Random rand = new Random();
    public String btnText = "";
    
    Icon imgOn  = new ImageIcon(getClass().getResource("/images/button-on.jpg"));
    Icon imgOff = new ImageIcon(getClass().getResource("/images/button-off.jpg"));
    
    public LightFrame() {
        super("lightsOut!");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel gameWindow = new JPanel();
        GridLayout buttonLayout = new GridLayout(HEIGHT, WIDTH);
        gameWindow.setLayout(buttonLayout);
        add(gameWindow);
        
        for (int i = 0; i < WIDTH; i++)    {
            for (int j = 0; j < HEIGHT; j++)    {
                btnText = rand.nextBoolean() ? "X" : "O";
                JButton nextBtn = new JButton(btnText);
//                nextBtn.setContentAreaFilled(false);
//                nextBtn.setIcon(imgOn);
    //            nextBtn.addActionListener(thisGame);
                gameWindow.add(nextBtn);
                buttons[i][j] = nextBtn;
            }
        }
        
        setVisible(true);
    }
}
