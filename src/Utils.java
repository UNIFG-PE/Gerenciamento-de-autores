import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    public static String[] json_to_string_array(String json) {
        String[] livros;
        try {
            // Se o campo estiver vazio, cria um array vazio
            if (json.isEmpty()) {
                livros = new String[0];
            } else {
                // Tenta fazer o parse do JSON para String[]
                //
                ObjectMapper objectMapper = new ObjectMapper();
                livros = objectMapper.readValue(json, String[].class);
            }
        } catch (Exception e) {
            throw new RuntimeException("error parsing to string array");
            // JOptionPane.showMessageDialog(this,
            // "Formato JSON inválido para livros. Use formato: [\"Livro 1\", \"Livro 2\"]",
            // "Erro de formato", JOptionPane.ERROR_MESSAGE);
            // return;
        }

        return livros;

    }

}
