import com.sun.net.httpserver.Headers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {
    private ManipulaArquivo manipuladorArquivo;
    private List<List<String>> data;
    private List<String> headers;

    public CsvParser() {
        manipuladorArquivo = new ManipulaArquivo();
    }

    public boolean readCsv(String caminho) {
        try {
            List<List<String>> data = new ArrayList<>();

            List<String> csvArquivo = manipuladorArquivo.abrirArquivo(caminho);

            if (csvArquivo.size() < 2) {
                return false;
            }

            this.setHeaders(Arrays.asList(csvArquivo.get(0).split(";")));
            csvArquivo.remove(0);

            for (String s : csvArquivo) {
                List<String> algo = new ArrayList<>();
                String[] vetor = s.split(";");
                for (int i = 0; i < vetor.length; i++) {
                    algo.add(vetor[i]);
    //                System.out.println(vetor[i]);
                }
                if (algo.size() > 0)
                    data.add(algo);
            }
            this.setData(data);

            return true;
        
        } catch (Exception e) {
            return false;
        }
    }

    public int getDataSize() {
        return this.data.size();
    }

    public String getElement(int linha, String coluna) {
//        System.out.println(this.data);
        int colunaIndex = this.findHeaderIndex(coluna);
//        System.out.println(colunaIndex);

        if (this.data.get(linha).get(colunaIndex).equals(""))
            return "8";
        return this.data.get(linha).get(colunaIndex);
    }

    private int findHeaderIndex(String header) {
//        System.out.println(this.headers);
        for (int i = 0; i < this.headers.size(); i++) {
            if (this.headers.get(i).equals(header)) {
                return i;
            }
        }
        return -1;
    }

    private void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    private void setData(List<List<String>> data) {
        this.data = data;
    }
}
