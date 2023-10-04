import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ThemeFactory {
    JPanel createBackground();
    JButton createButton();
    JLabel createLabel();
    JPanel createCirclePanel();
    Font createFont();
}

class LightThemeFactory implements ThemeFactory {
    public JPanel createBackground() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        return panel;
    }

    public JButton createButton() {
        JButton button = new JButton("Cambiar a Tema Oscuro");
        button.setForeground(Color.BLACK);
        return button;
    }

    public JLabel createLabel() {
        JLabel label = new JLabel("Instagram");
        label.setFont(createFont());
        label.setForeground(Color.BLACK);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        return label;
    }

    public JPanel createCirclePanel() {
        return new CirclePanel();
    }

    public Font createFont() {
        Font font = UIManager.getFont("Label.font"); // Obtiene la fuente predeterminada del sistema
        return font.deriveFont(Font.PLAIN, 24f);
    }
}

class DarkThemeFactory implements ThemeFactory {
    public JPanel createBackground() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        return panel;
    }

    public JButton createButton() {
        JButton button = new JButton("Cambiar a Tema Claro");
        button.setForeground(Color.WHITE);
        return button;
    }

    public JLabel createLabel() {
        JLabel label = new JLabel("Instagram");
        label.setFont(createFont());
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }

    public JPanel createCirclePanel() {
        return new CirclePanel();
    }

    public Font createFont() {
        Font font = UIManager.getFont("Label.font"); // Obtiene la fuente predeterminada del sistema
        return font.deriveFont(Font.PLAIN, 24f);
    }
}

class CirclePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int circleCount = 10;
        int circleDiameter = 30;

        int y = (getHeight() - circleDiameter) / 2;

        for (int i = 0; i < circleCount; i++) {
            Color color = getRandomColor();
            g.setColor(color);
            g.fillOval(30 + i * (circleDiameter + 5), y, circleDiameter, circleDiameter);
        }
    }

    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }
}

class ThemeSwitcherApp {
    private JFrame frame;
    private ThemeFactory themeFactory;

    public ThemeSwitcherApp(ThemeFactory themeFactory) {
        this.themeFactory = themeFactory;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Abstract Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);

        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel backgroundPanel = themeFactory.createBackground();
        JButton themeButton = themeFactory.createButton();
        JLabel instagramLabel = themeFactory.createLabel();
        JPanel circlePanel = themeFactory.createCirclePanel();

        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTheme();
            }
        });

        JPanel labelContainer = new JPanel(new BorderLayout());
        labelContainer.add(instagramLabel, BorderLayout.WEST);
        labelContainer.add(circlePanel, BorderLayout.CENTER);

        contentPane.add(backgroundPanel, BorderLayout.CENTER);
        contentPane.add(themeButton, BorderLayout.SOUTH);
        contentPane.add(labelContainer, BorderLayout.NORTH);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void switchTheme() {
        if (themeFactory instanceof LightThemeFactory) {
            themeFactory = new DarkThemeFactory();
        } else {
            themeFactory = new LightThemeFactory();
        }

        frame.getContentPane().removeAll();
        initialize();
        frame.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ThemeFactory initialThemeFactory = new LightThemeFactory();
                new ThemeSwitcherApp(initialThemeFactory);
            }
        });
    }
}
