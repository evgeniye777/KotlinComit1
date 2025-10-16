import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.Random;

public class Balls extends JFrame {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final int DIAMETER = 40; // Увеличиваем диаметр круга
    private static final Color BACKGROUND_COLOR = Color.white;

    private double xPos = 600; // Начальная позиция по X
    private double yPos = 200; // Начальная позиция по Y
    private Color ballColor = Color.RED; // Начальный цвет круга
    private JPanel ballPanel;
    private Random random;

    public Balls() {
        random = new Random();

        ///Панель для размещения области рисования
        ballPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(WIDTH, HEIGHT);
            }

            @Override
            public void paintComponent(Graphics g) {
                //отрисовка круга
                super.paintComponent(g);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(ballColor);
                g.fillOval((int) xPos, (int) yPos, DIAMETER, DIAMETER);
            }
        };

        ballPanel.setOpaque(true);
        ballPanel.setBackground(BACKGROUND_COLOR);

        JButton butStart = new JButton("Сменить позицию и цвет");
        butStart.setLocation(510, 510);
        butStart.setSize(180, 30);
        butStart.setBackground(Color.lightGray);
        butStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeBallPositionAndColor();
                ballPanel.repaint(); // Перерисовываем панель
            }
        });

        ballPanel.setLayout(null);
        ballPanel.add(butStart);

        JFrame mainFrame = new JFrame("Moving Ball");
        mainFrame.add(ballPanel);
        mainFrame.setSize(WIDTH + 16, HEIGHT + 40);
        mainFrame.setVisible(true);
        mainFrame.setLocation(100, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void changeBallPositionAndColor() {
        // Генерация случайных координат
        xPos = random.nextInt(WIDTH - DIAMETER); //вычитаем диаметр чтобы учесть размеры самого круга (иначе может случайно оказаться за пределами области рисования)
        yPos = random.nextInt(HEIGHT - DIAMETER);

        // Генерация случайного цвета
        ballColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Balls();
            }
        });
    }
}
