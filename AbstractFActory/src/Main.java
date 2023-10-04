import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ThemeFactory {
    JPanel createBackground();
    JButton createButton();
    JLabel createLabel();
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
        label.setForeground(Color.BLACK); // Color del texto (letras)
        label.setBackground(Color.WHITE); // Color de fondo
        label.setOpaque(true);
        return label;
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
        label.setForeground(Color.WHITE); // Color del texto (letras)
        label.setBackground(Color.BLACK); // Color de fondo
        label.setOpaque(true);
        return label;
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
        frame = new JFrame("Cambiar Tema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        // Centrar la ventana en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);

        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel backgroundPanel = themeFactory.createBackground();
        JButton themeButton = themeFactory.createButton();
        JLabel instagramLabel = themeFactory.createLabel();
        instagramLabel.setBounds(0, 0, 80, 30); // Establece la posición (0,0) y el tamaño (ancho 80, alto 30)
        instagramLabel.setPreferredSize(new Dimension(80, 30)); // Establece el tamaño preferido

        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTheme();
            }
        });

        // Agregar el JLabel en la esquina superior derecha
        JPanel labelContainer = new JPanel(new BorderLayout());
        labelContainer.add(instagramLabel);

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
