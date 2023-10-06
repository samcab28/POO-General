import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definir una interfaz que declare métodos para crear componentes de interfaz de usuario relacionados.
interface ThemeFactory {
    JPanel createBackground();  // Crear el panel de fondo.
    JButton createButton();      // Crear un botón.
    JLabel createLabel();        // Crear una etiqueta de texto.
    JPanel createCirclePanel();  // Crear un panel circular.
    Font createFont();           // Crear una fuente personalizada.
}

// Implementación concreta de ThemeFactory para un tema de luz.
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
        label.setOpaque(false);
        return label;
    }

    public JPanel createCirclePanel() {
        return new CirclePanel();
    }

    public Font createFont() {
        Font font = UIManager.getFont("Label.font");
        return font.deriveFont(Font.PLAIN, 24f);
    }
}

// Implementación concreta de ThemeFactory para un tema oscuro.
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
        label.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        label.setOpaque(false);
        return label;
    }

    public JPanel createCirclePanel() {
        return new CirclePanel();
    }

    public Font createFont() {
        Font font = UIManager.getFont("Label.font");
        return font.deriveFont(Font.PLAIN, 24f);
    }
}

// Clase que representa un panel circular personalizado.
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

// Clase principal que configura la interfaz de usuario.
public class Main {
    private JFrame frame;
    private ThemeFactory themeFactory;

    // Constructor que recibe una fábrica de temas (ThemeFactory).
    public Main(ThemeFactory themeFactory) {
        this.themeFactory = themeFactory;
        initialize();
    }

    // Configura la interfaz de usuario.
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

        // Agregar un ActionListener al botón para cambiar el tema.
        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTheme();
            }
        });

        // Configurar un panel personalizado con un gradiente.
        JPanel rectanglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.MAGENTA, 0, getHeight(), Color.PINK);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            };
        };

        JPanel labelContainer = new JPanel(new BorderLayout());
        labelContainer.setOpaque(false);
        rectanglePanel.setPreferredSize(new Dimension(200, 50));
        rectanglePanel.add(instagramLabel);
        labelContainer.add(rectanglePanel, BorderLayout.WEST);
        labelContainer.add(circlePanel, BorderLayout.CENTER);

        contentPane.add(backgroundPanel, BorderLayout.CENTER);
        contentPane.add(themeButton, BorderLayout.SOUTH);
        contentPane.add(labelContainer, BorderLayout.NORTH);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    // Cambiar entre temas en tiempo de ejecución.
    private void switchTheme() {
        if (themeFactory instanceof LightThemeFactory) {
            themeFactory = new DarkThemeFactory();
        } else {
            themeFactory = new LightThemeFactory();
        }

        // Eliminar todos los componentes actuales y volver a inicializar la interfaz con el nuevo tema.
        frame.getContentPane().removeAll();
        initialize();
        frame.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Inicializar la aplicación con un tema de luz.
                ThemeFactory initialThemeFactory = new LightThemeFactory();
                new Main(initialThemeFactory);
            }
        });
    }
}
