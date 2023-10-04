import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ThemeFactory {
    JPanel createBackground();
    JButton createButton();
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
        frame.setSize(300, 500);

        // Centrar la ventana en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);

        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel backgroundPanel = themeFactory.createBackground();
        JButton themeButton = themeFactory.createButton();

        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTheme();
            }
        });

        contentPane.add(backgroundPanel, BorderLayout.CENTER);
        contentPane.add(themeButton, BorderLayout.SOUTH);

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
