import javax.swing.*;
import java.awt.*;

public class Password extends JFrame {
    static int width = 800;
    static int height = 600;

    public Password() {
        super("Resolution");
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

        int result = JOptionPane.showConfirmDialog(null, "Перед использованием программы нужно пройти регистрацию. Продолжить?",
                "Регистрация в системе", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        boolean isValidLogin = false;
        while (!isValidLogin) {
            String login = JOptionPane.showInputDialog(null, "Укажите логин", "Ввод логина", JOptionPane.PLAIN_MESSAGE);
            if (login == null) {
                return;
            }
            isValidLogin = validateLogin(login);
        }

        Password frame = new Password();
        frame.initGui();
        frame.pack();
        frame.setVisible(true);
    }

    private static boolean validateLogin(String login) {
        if (login.length() < 5) {
            return false;
        }
        if (login.contains(" ")) {
            return false;
        }
        return true;
    }
}
