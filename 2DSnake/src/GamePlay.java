import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];

    private  int lengthofsnake = 3;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private int score = 0;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    //for maintaining speed of snake
    private Timer timer;
    private int delay = 100;

    private int moves = 0;

    private ImageIcon snakeImage;
    private int [] enemyXpos = {25,50,75,100,125,150,175, 200, 225, 250, 275, 300,325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600,625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int [] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300,325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600,625};

    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private ImageIcon titleImage;
    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();

    }
    public void paint(Graphics g)
    {
        if (moves == 0)
        {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;

            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }
        //draw title image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        //draw the title image
        titleImage = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\title.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //draw the gameplay border
        g.setColor(Color.white);
        g.drawRect(24,74, 851, 577);

        //draw background for gameplay
        g.setColor(Color.black);
        g.fillRect(25,75,850, 575);

        //set score
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: " + score, 780, 30);

        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: " + lengthofsnake, 780, 50);

        rightmouth = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\right.png");
        rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

        for(int i=0; i< lengthofsnake; i++)
        {
            if(i == 0 && right)
            {
                rightmouth = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\right.png");
                rightmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && left)
            {
                leftmouth = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\left.png");
                leftmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && up)
            {
                upmouth = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\up.png");
                upmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && down)
            {
                downmouth = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\down.png");
                downmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i!=0)
            {
                snakeImage = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\snake.png");
                snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
        }
        enemyimage = new ImageIcon("D:\\Programs\\2DSnake\\src\\Resources\\enemy.png");
        if((enemyXpos[xpos] == snakeXlength[0]) && (enemyYpos[ypos] == snakeYlength[0]))
        {
            score++;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);

        for(int j = 1; j < lengthofsnake; j++)
        {
            if((snakeXlength[j] == snakeXlength[0]) && snakeYlength[j] == snakeYlength[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to Restart", 350, 340);

            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right)
        {
            for(int i = lengthofsnake; i>=0; i--)
            {
                snakeYlength[i+1] = snakeYlength[i];
            }
            for(int i = lengthofsnake; i>=0; i--)
            {
                if(i == 0)
                    snakeXlength[i] = snakeXlength[i] + 25;
                else
                    snakeXlength[i] = snakeXlength[i-1];

                //check if the snake hits border and comes out of other side
                if(snakeXlength[i] > 850)
                {
                    snakeXlength[i] = 25;
                }
            }
        }
        repaint();
        if(left)
        {
            for(int i = lengthofsnake; i>=0; i--)
            {
                snakeYlength[i+1] = snakeYlength[i];
            }
            for(int i = lengthofsnake; i>=0; i--)
            {
                if(i == 0)
                    snakeXlength[i] = snakeXlength[i] - 25;
                else
                    snakeXlength[i] = snakeXlength[i-1];

                //check if the snake hits border and comes out of other side
                if(snakeXlength[i] < 25)
                {
                    snakeXlength[i] = 850;
                }
            }
        }
        repaint();
        if(up)
        {
                for(int i = lengthofsnake; i>=0; i--)
                {
                    snakeXlength[i+1] = snakeXlength[i];
                }
                for(int i = lengthofsnake; i>=0; i--)
                {
                    if(i == 0)
                        snakeYlength[i] = snakeYlength[i] - 25;
                    else
                        snakeYlength[i] = snakeYlength[i-1];

                    //check if the snake hits border and comes out of other side
                    if(snakeYlength[i] < 75)
                    {
                        snakeYlength[i] = 625;
                    }
            }
            repaint();
        }
        if(down)
        {
            for(int i = lengthofsnake; i>=0; i--)
            {
                snakeXlength[i+1] = snakeXlength[i];
            }
            for(int i = lengthofsnake; i>=0; i--)
            {
                if(i == 0)
                    snakeYlength[i] = snakeYlength[i] + 25;
                else
                    snakeYlength[i] = snakeYlength[i-1];

                //check if the snake hits border and comes out of other side
                if(snakeYlength[i] > 625)
                {
                    snakeYlength[i] = 75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moves++;
            right = true;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            left = false;
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            left = true;
            if(!right)
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }

            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            up = true;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }

            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            down = true;
            if(!up)
            {
                down = true;
            }
            else
            {
                up = true;
                down = false;
            }
            left = false;
            right = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
