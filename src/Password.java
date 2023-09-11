import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Password extends JFrame {
    static int width = 800;
    static int height = 600;

    public Password() {
        super("Password");
    }

    private void initGui() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(Password.width, Password.height));
        this.setLocation(d.width / 2 - Password.width / 2, d.height / 2 - Password.height / 2);
        this.getContentPane().setBackground(Color.lightGray);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText", "OK");

        int result = JOptionPane.showConfirmDialog(null,
                "Перед использованием программы нужно пройти регистрацию. Продолжить?",
                "Регистрация в системе", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        boolean isValidLogin = false;
        while (!isValidLogin) {
            String login = JOptionPane.showInputDialog(null, "Укажите логин",
                    "Ввод логина", JOptionPane.PLAIN_MESSAGE);
            if (login == null) {
                return;
            }
            isValidLogin = validateLogin(login);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Укажите пароль");
        JPasswordField pass = new JPasswordField(20);
        panel.add(label);
        panel.add(pass);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        pass.setAlignmentX(Component.LEFT_ALIGNMENT);
        String[] options = {"OK", "Отмена"};
        StringBuilder password;

        boolean isValidPassword = false;
        while (!isValidPassword) {
            pass.setText("");
            int option = JOptionPane.showOptionDialog(null, panel, "Ввод пароля",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (option != 0) {
                return;
            }
            password = new StringBuilder();
            for (char c : pass.getPassword()) {
                password.append(c);
            }
            isValidPassword = validatePassword(password.toString());
        }

        JOptionPane.showMessageDialog(null,  "Вы успешно прошли регистрацию!",
                "Регистрация", JOptionPane.PLAIN_MESSAGE);

        Password frame = new Password();
        frame.initGui();
        frame.pack();
        frame.setVisible(true);
    }

    private static boolean validatePassword(String pass) {
        if (pass.length() < 8) {
            return false;
        }
        if (pass.contains(" ")) {
            return false;
        }
        if (!pass.matches(".*\\d+.*")) {
            return false;
        }
        return pass.matches(".*[a-zA-Z]+.*");
    }

    private static boolean validateLogin(String login) {
        if (login.length() < 5) {
            return false;
        }
        return !login.contains(" ");
    }
}
