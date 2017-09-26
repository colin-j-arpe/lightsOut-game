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
import java.util.Random;

/**
 *
 * @author cspanw74
 */
public class LightFrame extends JFrame  {
    public int height = 3;
    public int width = 4;
    LightGame thisGame;
    JPanel gameWindow;
    MenuPanel thisMenu;
    BoardPanel thisBoard;
    
    
    public LightFrame() {
        super("lightsOut!");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newBoard();
    }
    
    public void newBoard()   {
        thisGame = new LightGame (this, height, width);
        gameWindow = new JPanel();

        thisMenu = new MenuPanel(this);
        setJMenuBar(thisMenu.menuBar);

        thisBoard = new BoardPanel (height, width, thisGame.btnStates);
        for (int i = 0; i < thisGame.height; i++)   {
            for (int j = 0; j < thisGame.width; j++)    {
                thisBoard.buttons[i][j].addActionListener(thisGame);
            }
        }

        BorderLayout windowLayout = new BorderLayout();
        gameWindow.setLayout(windowLayout);
        gameWindow.add(thisMenu, BorderLayout.PAGE_START);
        gameWindow.add(thisBoard, BorderLayout.CENTER);
        add(gameWindow);
        setVisible(true);
    }
    
    public void restart()   {
        remove(gameWindow);
        newBoard();
    }
}

class MenuPanel extends JPanel implements ActionListener  {
    LightFrame thisFrame;
    JMenuBar menuBar = new JMenuBar();
    JLabel showMoves;
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

//      to come: game style selection, help menu
        subMenu = new JMenu("Game style");
        subMenu.setMnemonic(KeyEvent.VK_S);
        gameMenu = new JMenu("Help");
        gameMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(gameMenu);
        
        showMoves = new JLabel("Number of moves: ");
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(showMoves);
        showMoves = new JLabel("0");
        menuBar.add(showMoves);

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
            thisFrame.restart();
        }
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