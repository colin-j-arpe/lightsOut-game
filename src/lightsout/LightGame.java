/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

//import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author colin-j-arpe
 */
public class LightGame implements ActionListener     {
    LightFrame gui;
    final int HEIGHT = 2;
    final int WIDTH = 3;
    JButton clicked;
    int btnRow, btnColumn;
    boolean[][] btnStates = new boolean[HEIGHT][WIDTH];
    int moves = 0;
    
    public LightGame (LightFrame in)    {
        this.gui = in;
//        final int WIDTH = gui.WIDTH;
//        final int HEIGHT = gui.HEIGHT;
//        for (int i = 0; i < gui.WIDTH; i++) {
//            for (int j = 0; j < gui.HEIGHT; j++)    {
//                btnStates[i][j] = gui.buttons[i][j].getText().equals("+");
//            }
//        }
    }
    
    public void actionPerformed(ActionEvent event)  {
        moves++;
        clicked = (JButton) event.getSource();
        btnRow = (int)clicked.getClientProperty("row");
        btnColumn = (int)clicked.getClientProperty("column");
        changeStates(btnRow, btnColumn);
        checkWin();
    }
    
    private void changeStates (int row, int column) {
        changeOneState(row, column);
        if (row > 0)            changeOneState(row - 1, column);
        if (row < HEIGHT - 1)   changeOneState(row + 1, column);
        if (column > 0)         changeOneState(row, column - 1);
        if (column < WIDTH - 1) changeOneState(row, column + 1);
    }
    
    private void changeOneState (int row, int column) {
        btnStates[row][column] = !btnStates[row][column];
//        System.out.println("C" + column + ", R" + row + ": " + btnStates[row][column]);
        if (btnStates[row][column])
            gui.thisBoard.buttons[row][column].setBackground(Color.RED);
        else
            gui.thisBoard.buttons[row][column].setBackground(Color.LIGHT_GRAY);
    }
    
    private void checkWin ()    {
        for (int i = 0; i < HEIGHT; i++)    {
            for (int j = 0; j < WIDTH; j++) {
                if (btnStates[i][j]) return;
            }
        }
        System.out.println ("Game won in " + moves + " moves");
        JOptionPane.showMessageDialog(gui, 
                "You solved the puzzle in " + moves + " moves.", 
                "Congratulations!", 
                JOptionPane.PLAIN_MESSAGE);
    }
}
