/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

//import java.awt.event.*;

import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author colin-j-arpe
 */
public class LightGame implements ActionListener     {
    LightFrame gui;
    final int WIDTH = 10;
    final int HEIGHT = 6;
    JButton clicked;
    int btnColumn, btnRow;
    boolean[][] btnStates = new boolean[WIDTH][HEIGHT];
    
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
        clicked = (JButton) event.getSource();
        btnColumn = (int)clicked.getClientProperty("column");
        btnRow = (int)clicked.getClientProperty("row");
        changeStates(btnColumn, btnRow);
    }
    
    private void changeStates (int column, int row) {
        changeOneState(column, row);
        if (column > 0)             changeOneState(column - 1, row);
        if (column < gui.WIDTH - 1) changeOneState(column + 1, row);
        if (row > 0)                changeOneState(column, row - 1);
        if (row < gui.HEIGHT - 1)   changeOneState(column, row + 1);
    }
    
    private void changeOneState (int column, int row) {
        btnStates[column][row] = !btnStates[column][row];
        if (btnStates[column][row])
            gui.buttons[column][row].setText("+");
        else
            gui.buttons[column][row].setText("");
    }
}
