import javax.swing.*;

public class ConversorUnidades {

    public static void main(String[] args) {
        // Solicitar al usuario que ingrese un número
        String input = JOptionPane.showInputDialog("Ingrese el valor a convertir:");

        // Verificar que el input sea un número válido
        try {
            double valor = Double.parseDouble(input);

            // Solicitar al usuario que seleccione la unidad de medida
            String[] opciones = {"Metro", "Kilogramo", "Segundo", "Kelvin", "Amperio", "Mole", "Candela",
                    "Pulgada", "Pie", "Yarda", "Milla", "Onza", "Libras", "Centímetro"};
            String unidadOrigen = (String) JOptionPane.showInputDialog(null, "Seleccione la unidad de medida:",
                    "Selección de unidad", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            // Solicitar al usuario que seleccione la unidad a la que se convertirá
            String unidadDestino = (String) JOptionPane.showInputDialog(null, "Seleccione la unidad a la que desea convertir:",
                    "Selección de unidad", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            // Verificar si la conversión es permitida
            if (!esConversionPermitida(unidadOrigen, unidadDestino)) {
                JOptionPane.showMessageDialog(null, "No es posible convertir de " + unidadOrigen + " a " + unidadDestino);
            } else {
                // Realizar la conversión
                double resultado = convertirUnidades(valor, unidadOrigen, unidadDestino);

                // Crear un botón "Ver Todo"
                JButton verTodoButton = new JButton("Ver Todo");
                verTodoButton.addActionListener(e -> mostrarConversionesPosibles(unidadOrigen, unidadDestino, valor, resultado));

                // Crear un panel con la respuesta y el botón
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel(valor + " " + unidadOrigen + " es equivalente a " + resultado + " " + unidadDestino));
                panel.add(verTodoButton);

                // Mostrar el panel con la respuesta y el botón
                JOptionPane.showOptionDialog(null, panel, "Resultado de la conversión",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
        }
    }

    // Método para verificar si la conversión es permitida
    private static boolean esConversionPermitida(String unidadOrigen, String unidadDestino) {
        // Definir las conversiones permitidas
        String[] conversionesPermitidas = {"Metro->Pulgada", "Metro->Centímetro", "Pulgada->Metro", "Pulgada->Centímetro",
                "Centímetro->Metro", "Centímetro->Pulgada"};
        // Añadir más conversiones permitidas según sea necesario

        // Verificar si la conversión es permitida
        return java.util.Arrays.asList(conversionesPermitidas).contains(unidadOrigen + "->" + unidadDestino);
    }

    // Método para mostrar otras conversiones posibles con la misma medida
    private static void mostrarConversionesPosibles(String unidadOrigen, String unidadDestino, double valorOriginal, double resultado) {
        // Definir las conversiones posibles para la unidad seleccionada
        String[] conversionesPosibles = {"Metro->Pulgada", "Metro->Centímetro", "Pulgada->Metro", "Pulgada->Centímetro",
                "Centímetro->Metro", "Centímetro->Pulgada"};
        // Añadir más conversiones posibles según sea necesario

        // Crear un mensaje con las conversiones posibles
        StringBuilder mensaje = new StringBuilder("Conversiones posibles con " + unidadOrigen + ":\n");
        for (String conversion : conversionesPosibles) {
            String[] partes = conversion.split("->");
            String unidadDesde = partes[0];
            String unidadHacia = partes[1];

            double resultadoConversion = convertirUnidades(valorOriginal, unidadOrigen, unidadHacia);

            mensaje.append(valorOriginal).append(" ").append(unidadOrigen)
                    .append(" es equivalente a ").append(resultadoConversion).append(" ").append(unidadHacia)
                    .append(" (").append(resultadoConversion - resultado).append(" ").append(unidadDestino).append(")\n");
        }

        // Mostrar el mensaje
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    // Método para realizar la conversión entre unidades
    private static double convertirUnidades(double valor, String unidadOrigen, String unidadDestino) {
        // Definir las conversiones conocidas
        double metroAPulgada = 39.3701;
        double pulgadaAMetro = 0.0254;
        double centimetroAMetro = 0.01;
        double metroACentimetro = 100;
        // Añadir más conversiones según sea necesario

        // Realizar la conversión
        switch (unidadOrigen) {
            case "Metro":
                switch (unidadDestino) {
                    case "Pulgada":
                        return valor * metroAPulgada;
                    case "Centímetro":
                        return valor * metroACentimetro;
                    // Agregar más conversiones según sea necesario
                    default:
                        return valor; // Si la unidad de destino es la misma que la de origen, no hay conversión
                }
            case "Pulgada":
                switch (unidadDestino) {
                    case "Metro":
                        return valor * pulgadaAMetro;
                    case "Centímetro":
                        return valor * pulgadaAMetro * metroACentimetro;
                    // Agregar más conversiones según sea necesario
                    default:
                        return valor; // Si la unidad de destino es la misma que la de origen, no hay conversión
                }
            case "Centímetro":
                switch (unidadDestino) {
                    case "Metro":
                        return valor * centimetroAMetro;
                    case "Pulgada":
                        return valor * centimetroAMetro * metroAPulgada;
                    // Agregar más conversiones según sea necesario
                    default:
                        return valor; // Si la unidad de destino es la misma que la de origen, no hay conversión
                }
            // Agregar más casos según sea necesario
            default:
                return valor; // Si la unidad de origen no es reconocida, devolver el mismo valor
        }
    }
}

