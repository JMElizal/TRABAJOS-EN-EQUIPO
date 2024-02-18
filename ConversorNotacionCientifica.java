import javax.swing.JOptionPane;

public class ConversorNotacionCientifica {
    public static void main(String[] args) {
        // Pedir al usuario que ingrese un número
        String input = JOptionPane.showInputDialog("Ingrese un número en decimales o notación científica:");

        // Verificar si el número ingresado está en notación científica
        if (input.toLowerCase().contains("e") || input.toLowerCase().contains("x")) {
            // Convertir de notación científica a decimal
            try {
                double decimalNumber = convertirANotacionDecimal(input);
                JOptionPane.showMessageDialog(null, "Número en decimal: " + decimalNumber);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
            }
        } else {
            // Convertir de decimal a notación científica con formato "x10"
            try {
                double decimalNumber = Double.parseDouble(input);
                int exponente = 0;

                // Calcular el exponente para representar en notación científica
                while (decimalNumber >= 10.0 || decimalNumber <= -10.0) {
                    decimalNumber /= 10.0;
                    exponente++;
                }

                while (decimalNumber < 1.0 && decimalNumber > -1.0) {
                    decimalNumber *= 10.0;
                    exponente--;
                }

                String scientificNotation = String.format("%.2fx10^%d", decimalNumber, exponente);
                JOptionPane.showMessageDialog(null, "Número en notación científica: " + scientificNotation);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }

    // Método para convertir notación científica a decimal
    private static double convertirANotacionDecimal(String input) {
        // Verificar si la notación científica es con "e" o "x10"
        if (input.toLowerCase().contains("e")) {
            // Convertir notación científica con "e" a decimal
            String[] parts = input.split("e|E");
            double base = Double.parseDouble(parts[0]);
            int exponente = Integer.parseInt(parts[1]);
            return base * Math.pow(10, exponente);
        } else if (input.toLowerCase().contains("x")) {
            // Convertir notación científica con "x" a decimal
            String[] parts = input.split("x|X");
            double base = Double.parseDouble(parts[0]);
            int exponente = Integer.parseInt(parts[1]);
            return base * Math.pow(10, exponente);
        } else {
            throw new NumberFormatException();
        }
    }
}




