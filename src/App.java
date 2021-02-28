import java.awt.Color;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {
    
    private JFrame frame;
    private JPanel controlPanel;
    private JPanel mazePanel;
    private GridBagConstraints gbc;

    int[][] maze;
    JPanel[][] mazeCells;

    int mazeSize = 10;

    int cellSize = 10;

    public App(){


        frame = new JFrame();
        controlPanel = new JPanel();

        //frame setup
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        //panel setup
        controlPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        controlPanel.setBackground(Color.darkGray);

        controlPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton btnEnter = new JButton("Create Maze");
        btnEnter.setBackground(Color.gray);
        controlPanel.add(btnEnter, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton btnSolve = new JButton("Solve Maze");
        btnSolve.setBackground(Color.gray);
        controlPanel.add(btnSolve, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mazePanel = new JPanel();
        mazePanel.setPreferredSize(new Dimension(mazeSize * (cellSize * 3 + 50),mazeSize * (cellSize * 3 + 50)));
        mazePanel.setBackground(Color.gray);
        controlPanel.add(mazePanel, gbc);


        btnEnter.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
        {frame.setVisible(true);
            
            prepareMaze(mazeSize);
            showMazeLayout();
            mazePanel.repaint();
        }
        });

        btnSolve.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
        {frame.setVisible(true);
            
            SolveMaze();
        }
        });
        

        frame.add(controlPanel);

        frame.setVisible(true);

    }


    public static void main(String[] args) {
        new App();
    }

    void showMazeLayout(){

        gbc.gridx = 0;
        gbc.gridy = 1;
        mazePanel = new JPanel();
        mazePanel.setLayout(new BorderLayout());
        mazePanel.setPreferredSize(new Dimension(mazeSize * (cellSize * 3 + 50),mazeSize * (cellSize * 3 + 50)));
        mazePanel.setBackground(Color.gray);
        controlPanel.add(mazePanel, gbc);


        JPanel holding = new JPanel();
        holding.setLayout(new GridLayout(maze.length, maze.length, 10,10));
        holding.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mazeCells = new JPanel[maze.length][maze.length];
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                
                RectPanel panel;
                if (maze[i][j] == 1){

                    panel = new RectPanel(Color.BLACK);
                }
                else if(maze[i][j] == 2){
                    panel = new RectPanel(Color.RED);
                }
                else{
                    panel = new RectPanel(Color.WHITE);
                }
                
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.setPreferredSize(new Dimension(cellSize, cellSize));
                holding.add(panel);
                mazeCells[i][j] = panel;
            }
        }
        mazePanel.add(holding);
        frame.setVisible(true);

    }



    void prepareMaze(int size){

        MazeGenerator generator = new MazeGenerator(size);
        generator.dfs();

        MazeProcessor proc = new MazeProcessor(generator.getList(), generator.getSize());
        proc.toBinary();

        maze = proc.getListBinary();
    }


    void SolveMaze(){
        
        MazeSolver mazeSolver = new MazeSolver(maze, 1, 1, maze.length-2, maze.length-2);

        if(mazeSolver.solve()){
            int[][] sol = mazeSolver.getSolution();

            for (int i = 0; i < sol.length; i++) {
                for (int j = 0; j < sol.length; j++) {
                    
                    if(sol[i][j] == 1){
                        maze[i][j] = 2;
                        
                    }

                }
            }

            showMazeLayout();

        }

    }

}


