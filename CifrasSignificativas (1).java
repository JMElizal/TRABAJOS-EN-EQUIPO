import javax.swing.*;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CifrasSignificativas extends JFrame {
    private JTextField entradaTextField;
    private JLabel cifrasSignificativasLabel;
    private JLabel cifrasNoSignificativasLabel;
    private JLabel mensajeLabel;

    public CifrasSignificativas() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cifras Significativas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        entradaTextField = new JTextField();
        cifrasSignificativasLabel = new JLabel("Cifras Significativas: ");
        cifrasNoSignificativasLabel = new JLabel("Cifras No Significativas: ");
        mensajeLabel = new JLabel("Presione Enter para calcular");
        mensajeLabel.setForeground(Color.RED);

        entradaTextField.addActionListener(e -> calcularCifras());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(entradaTextField)
                                        .addComponent(cifrasSignificativasLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cifrasNoSignificativasLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mensajeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(entradaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cifrasSignificativasLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cifrasNoSignificativasLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mensajeLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

 private void calcularCifras() {
    String entrada = entradaTextField.getText().trim();
    int cifrasSignificativas = 0;
    int cifrasNoSignificativas = 0;

    // Extraer la parte numérica antes de la notación científica
    String parteNumerica = extraerParteNumerica(entrada);

    // Regla 3: Eliminar ceros a la izquierda
    parteNumerica = parteNumerica.replaceFirst("^0+(?!$)", "");

    // Regla 1: Contar dígitos distintos de cero, considerando el cero a la derecha
    char[] parteNumericaArray = parteNumerica.toCharArray();
    boolean digitFound = false;
    boolean ceroFound = false;
    for (int i = 0; i < parteNumericaArray.length; i++) {
        char c = parteNumericaArray[i];
        if (Character.isDigit(c) && c != '0') {
            cifrasSignificativas++;
            digitFound = true;
            // Reiniciar el indicador de cero si se encuentra un dígito significativo
            ceroFound = false;
        } else if (c == '0' && digitFound) {
            // El cero está a la derecha de un dígito significativo
            ceroFound = true;
        } else if (c == '0' && ceroFound) {
            // Contar solo el primer cero consecutivo entre cifras significativas
            cifrasSignificativas++;
            ceroFound = false;
        } else if (c == '0') {
            cifrasNoSignificativas++;
        }
    }

    // Regla 2: Contar ceros entre dígitos distintos de cero
    cifrasSignificativas += contarCerosEntreDigitos(parteNumerica);

    cifrasSignificativasLabel.setText("Cifras Significativas: " + cifrasSignificativas);
    if (cifrasNoSignificativas > 0) {
        cifrasNoSignificativasLabel.setText("Cifras No Significativas: " + cifrasNoSignificativas);
    } else {
        cifrasNoSignificativasLabel.setText("No hay cifras no significativas.");
    }
}


private String extraerParteNumerica(String entrada) {
    // Utilizar expresión regular para extraer la parte numérica antes de la notación científica
    Pattern pattern = Pattern.compile("([0-9]+\\.?[0-9]*)");
    Matcher matcher = pattern.matcher(entrada);

    if (matcher.find()) {
        return matcher.group(1);
    } else {
        return "";
    }
}

    private int contarCerosEntreDigitos(String entrada) {
        int count = 0;
        boolean digitFound = false;

        for (char c : entrada.toCharArray()) {
            if (Character.isDigit(c) && c != '0') {
                digitFound = true;
            } else if (c == '0' && digitFound) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CifrasSignificativas().setVisible(true));
    }
}

