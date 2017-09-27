/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void showInstructions()  {
        JOptionPane.showMessageDialog(this, 
                "lightsOut! is a simple and infuriating game.\n\n"
                        + "You begin with a grid of buttons, some turned on, some turned off.\n"
                        + "Red represents ON.  The object is to turn them all off.  Each time\n"
                        + "you click a button, the button will switch its state: if it was on,\n"
                        + "it will turn off, and vice versa.  In addition, all of the adjacent\n"
                        + "buttons above, below, to the left and right will also switch their\n"
                        + "current state.  Use the Game menu to choose from five different board\n"
                        + "sizes.  And try not to break your computer in a frustrated rage.", 
                "Instructions", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void showAbout() {
        JOptionPane.showMessageDialog(this, 
                new aboutText("<html><body>Colin J. Arpe made this game.<br>You should go look at his portfolio at<br>&emsp<a href=\"http://thegreenranger.us\">thegreenranger.us</a><br>then hire him.</body></html>"),
                "lightsAbOut!", JOptionPane.PLAIN_MESSAGE);
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

//      to come: game style selection
        subMenu = new JMenu("Game style");
        subMenu.setMnemonic(KeyEvent.VK_S);

        gameMenu = new JMenu("Help");
        gameMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(gameMenu);
        
        menuItem = new JMenuItem("Instructions", KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        gameMenu.add(menuItem);
        
        menuItem = new JMenuItem("About", KeyEvent.VK_A);
        menuItem.addActionListener(this);
        gameMenu.add(menuItem);
        
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
        else if (selection.equals("Instructions"))
                thisFrame.showInstructions();
        else if (selection.equals("About"))
                thisFrame.showAbout();
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
    
class aboutText extends JEditorPane  {

    public aboutText(String content)   {
        super("text/html", content);
        setEditable(false);
        addHyperlinkListener(new HyperlinkListener()    {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event)   {
                if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))    {
                    try {
                        URI address = new URI(event.getURL().toString());
                        Desktop.getDesktop().browse(address);
                    } catch (IOException ex) {
                        Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(aboutText.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}