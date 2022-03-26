package views;

import controller.Game;
import controller.GameLoop;
import model.Direction;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class GameView extends JFrame {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 500;
    public static final int P1 = 1;
    public static final int P2 = 2;
    private final Canvas canvas = new Canvas();
    private final Game game;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
    }

    public void launch() {
        // GUI Stuff
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.movePlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.movePlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.movePlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.movePlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_E:
                        game.attack(P1);
                        break;
                    case KeyEvent.VK_I:
                        game.movePlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.movePlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.movePlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.movePlayer(P2, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_U:
                        game.attack(P2);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.stopPlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.stopPlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.stopPlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopPlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        game.stopPlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.stopPlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.stopPlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.stopPlayer(P2, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    static class ImagePanel extends JPanel {
        private Image img;
        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }
        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, 500,1000,null);
        }
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;
        private Image img;

        ImagePanel panel = new ImagePanel(new ImageIcon(getClass()
                .getResource("/assets/background/back.png"))
                .getImage());

        @Override
        public void render(World world) {
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            panel.paintComponent(g);
            world.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
