/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
//import java.awt.event.KeyEvent;
//import java.io.IOException;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.imageio.ImageIO;

/**
 *
 * @author cspanw74
 */
public class LightFrame extends JFrame  {
    public int height = 3;
    public int width = 4;
    LightGame thisGame = new LightGame (this, height, width);
    JPanel gameWindow = new JPanel();
    MenuPanel thisMenu = new MenuPanel(this);
    BoardPanel thisBoard;
    
    
    public LightFrame() {
        super("lightsOut!");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(thisMenu.menuBar);
        
        BorderLayout windowLayout = new BorderLayout();
        gameWindow.setLayout(windowLayout);
        gameWindow.add(thisMenu, BorderLayout.PAGE_START);
        newBoard();
        add(gameWindow);
        
        setVisible(true);
    }
    
    public void newBoard()   {
        thisBoard = new BoardPanel (height, width, thisGame.btnStates);
        for (int i = 0; i < thisGame.height; i++)   {
            for (int j = 0; j < thisGame.width; j++)    {
                thisBoard.buttons[i][j].addActionListener(thisGame);
            }
        gameWindow.add(thisBoard, BorderLayout.CENTER);
        }
    }
    
    public void restart()   {
//        LightFrame newGame = new LightFrame();
//        gameWindow.remove(thisBoard);
        thisGame = new LightGame (this, height, width);
        newBoard();
//        thisFrame.thisBoard = new BoardPanel (height, width, thisFrame.thisGame.btnStates);
//        gameWindow.add(thisFrame.thisBoard, BorderLayout.CENTER);
    }
}

class MenuPanel extends JPanel implements ActionListener  {
    LightFrame thisFrame;
    JMenuBar menuBar = new JMenuBar();
    String[] boardSizes = new String[] {
        "3x2", "4x3", "6x4", "8x5", "10x6"
    };
    
    public MenuPanel(LightFrame frame) {
        super();
        thisFrame = frame;
        FlowLayout menuLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(menuLayout);
        
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        gameMenu.add(menuItem);

        JMenu subMenu = new JMenu("Board size");
        subMenu.setMnemonic(KeyEvent.VK_B);
        for (int i = 0; i < boardSizes.length; i++) {
            menuItem = new JMenuItem(boardSizes[i]);
            menuItem.setAccelerator(KeyStroke.getKeyStroke((KeyEvent.VK_1 + i), ActionEvent.ALT_MASK));
            menuItem.addActionListener(this);
            subMenu.add(menuItem);
        }
        gameMenu.add(subMenu);

//        subMenu = new JMenu("Game style", KeyEvent.VK_G);

        add(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String selection = event.getActionCommand();
        if (selection.equals("New game"))
                thisFrame.restart();
        else    {
            String[] dimensions = selection.split("x");
            thisFrame.height = parseInt(dimensions[1]);
            thisFrame.width = parseInt(dimensions[0]);
System.out.println(selection);
            thisFrame.gameWindow.remove(thisFrame.thisBoard);
//            thisFrame.restart();
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class BoardPanel extends JPanel {
    JButton[][] buttons;
    final Random rand = new Random();
    boolean onOff;
    
    public BoardPanel(int height, int width, boolean btnStates[][])    {
        super();
        buttons = new JButton[height][width];
        
        GridLayout buttonLayout = new GridLayout(height, width);
        setLayout(buttonLayout);

        for (int i = 0; i < height; i++)    {
            for (int j = 0; j < width; j++)    {
                onOff = rand.nextBoolean();
                btnStates[i][j] = onOff;
                JButton nextBtn = new JButton();
                nextBtn.setBackground((onOff ? Color.RED : Color.LIGHT_GRAY));
                nextBtn.putClientProperty("row", i);
                nextBtn.putClientProperty("column", j);
                nextBtn.setEnabled(true);
                add(nextBtn);
                buttons[i][j] = nextBtn;
            }
        }
    }
}