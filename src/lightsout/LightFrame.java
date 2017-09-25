/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    MenuPanel thisMenu = new MenuPanel();
    BoardPanel thisBoard = new BoardPanel (thisGame.HEIGHT, thisGame.WIDTH, thisGame.btnStates);
    
    public LightFrame() {
        super("lightsOut!");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel gameWindow = new JPanel();
        BorderLayout windowLayout = new BorderLayout();
        gameWindow.setLayout(windowLayout);
        gameWindow.add(thisMenu, BorderLayout.PAGE_START);
        gameWindow.add(thisBoard, BorderLayout.CENTER);
        add(gameWindow);
        
        for (int i = 0; i < thisGame.HEIGHT; i++)   {
            for (int j = 0; j < thisGame.WIDTH; j++)    {
                thisBoard.buttons[i][j].addActionListener(thisGame);
            }
        }
        
        setVisible(true);
    }
}

class MenuPanel extends JPanel  {
    JMenuBar menuBar = new JMenuBar();
    
    public MenuPanel() {
        super();
        FlowLayout menuLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(menuLayout);
        
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        gameMenu.add(menuItem);

        JMenu subMenu = new JMenu("Board size");
        subMenu.setMnemonic(KeyEvent.VK_B);
        menuItem = new JMenuItem("3x2");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        subMenu.add(menuItem);
        menuItem = new JMenuItem("4x3");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        subMenu.add(menuItem);
        menuItem = new JMenuItem("6x4");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        subMenu.add(menuItem);
        menuItem = new JMenuItem("8x5");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
        subMenu.add(menuItem);
        menuItem = new JMenuItem("10x6");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
        subMenu.add(menuItem);
        gameMenu.add(subMenu);

        subMenu = new JMenu("Game style");
        subMenu.setMnemonic(KeyEvent.VK_G);

        add(menuBar);
    }
}

class BoardPanel extends JPanel {
    JButton[][] buttons;
    final Random rand = new Random();
    boolean onOff;
    public String btnText = "";
    
    public BoardPanel(int height, int width, boolean btnStates[][])    {
        super();
        buttons = new JButton[height][width];
        
        GridLayout buttonLayout = new GridLayout(height, width);
        setLayout(buttonLayout);

        for (int i = 0; i < height; i++)    {
            for (int j = 0; j < width; j++)    {
                onOff = rand.nextBoolean();
//                btnText = onOff ? "+" : "";
                btnStates[i][j] = onOff;
                JButton nextBtn = new JButton();
                nextBtn.setBackground((onOff ? Color.RED : Color.LIGHT_GRAY));
//                nextBtn.setOpaque(onOff);
                nextBtn.putClientProperty("row", i);
                nextBtn.putClientProperty("column", j);
//                nextBtn.setContentAreaFilled(false);
//                nextBtn.setIcon(imgOn);
                add(nextBtn);
                buttons[i][j] = nextBtn;
            }
        }
    }
}