import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class PassportInputGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ввод данных паспорта");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JTextField textField = new JTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 16));

            JLabel label = new JLabel("Введите данные паспорта в формате XX XX XXXXXX:");
            frame.add(label, BorderLayout.NORTH);

            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);
            resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
            resultArea.setMinimumSize(new Dimension(0,400));

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(textField, BorderLayout.NORTH);
            bottomPanel.add(resultArea, BorderLayout.CENTER);
            frame.add(bottomPanel, BorderLayout.CENTER);

            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new PassportDocumentFilter(textField, resultArea));

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class PassportDocumentFilter extends DocumentFilter {
        private JTextField textField;
        private JTextArea resultArea;

        public PassportDocumentFilter(JTextField textField, JTextArea resultArea) {
            this.textField = textField;
            this.resultArea = resultArea;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

            if (text != null) {
                StringBuilder validText = new StringBuilder();
                for (char c : text.toCharArray()) {
                    if (isValidChar(fb.getDocument().getLength() - length + validText.length(), c)) {
                        validText.append(c);
                    } else {
                        JOptionPane pane = new JOptionPane("Недопустимый символ! Ожидается формат XX XX XXXXXX.", JOptionPane.WARNING_MESSAGE);
                        JDialog dialog = pane.createDialog(null, "Предупреждение");
                        dialog.setLocationRelativeTo(null);
                        Point loc = dialog.getLocation();
                        dialog.setLocation(loc.x, loc.y + 100);
                        dialog.setVisible(true);
                    }
                }
                super.replace(fb, offset, length, validText.toString(), attrs);
                checkComplete(fb.getDocument());
            }
        }

        private boolean isValidChar(int currentLength, char c) {
            if (currentLength < 2) {
                return Character.isDigit(c);
            } else if (currentLength == 2) {
                return c == ' ';
            } else if (currentLength < 5) {
                return Character.isDigit(c);
            } else if (currentLength == 5) {
                return c == ' ';
            } else if (currentLength < 12) {
                return Character.isDigit(c);
            }
            return false;
        }

        private void checkComplete(Document doc) throws BadLocationException {
            String text = doc.getText(0, doc.getLength());
            if (text.length() == 12) {
                String series = text.substring(0, 5);
                String number = text.substring(6);
                resultArea.setText("Серия: " + series + "\nНомер: " + number);
            } else {
                resultArea.setText("");
            }
        }
    }
}