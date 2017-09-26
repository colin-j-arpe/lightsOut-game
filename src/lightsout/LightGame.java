/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author colin-j-arpe
 */
public class LightGame implements ActionListener     {
    LightFrame gui;
    int height, width;
    JButton clicked;
    int btnRow, btnColumn;
    boolean[][] btnStates;
    int moves = 0;
    
    public LightGame (LightFrame in, int heightIn, int widthIn)    {
        this.gui = in;
        height = heightIn;
        width = widthIn;
        btnStates = new boolean[height][width];
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
        if (row < height - 1)   changeOneState(row + 1, column);
        if (column > 0)         changeOneState(row, column - 1);
        if (column < width - 1) changeOneState(row, column + 1);
    }
    
    private void changeOneState (int row, int column) {
        btnStates[row][column] = !btnStates[row][column];
        if (btnStates[row][column])
            gui.thisBoard.buttons[row][column].setBackground(Color.RED);
        else
            gui.thisBoard.buttons[row][column].setBackground(Color.LIGHT_GRAY);
    }
    
    private void checkWin ()    {
        for (int i = 0; i < height; i++)    {
            for (int j = 0; j < width; j++) {
                if (btnStates[i][j]) return;
            }
        }
        JOptionPane.showMessageDialog(gui, 
                "You solved the puzzle in " + moves + " moves.", 
                "Congratulations!", 
                JOptionPane.PLAIN_MESSAGE);
        for (int i = 0; i < height; i++)    {
            for (int j = 0; j < width; j++) {
                gui.thisBoard.buttons[i][j].setEnabled(false);
            }
        }
    }
}
