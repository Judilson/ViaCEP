/*
 * PARG Desenvolvimento de Sistemas
 * Pablo Alexander - pablo@parg.com.br
 * 
 * Obtem um CEP no ViaCEP
 */
package viacep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author PABLO
 */
public class ViaCEP {

    private String CEP;
    private String Logradouro;
    private String Complemento;
    private String Bairro;
    private String Localidade;
    private String Uf;

    /**
     * Constrói uma nova classe
     *
     * @param cep
     */
    public ViaCEP(String cep) throws Exception {
        this.CEP = cep;

        // define a url
        String url = "http://viacep.com.br/ws/" + cep + "/json/";

        // define os dados
        JSONObject obj = new JSONObject(this.get(url));

        if (!obj.has("erro")) {
            this.CEP = obj.getString("cep");
            this.Logradouro = obj.getString("logradouro");
            this.Complemento = obj.getString("complemento");
            this.Bairro = obj.getString("bairro");
            this.Localidade = obj.getString("localidade");
            this.Uf = obj.getString("uf");
        } else {
            throw new Exception("Não foi possível encontrar o CEP");
        }
    }

    /**
     * Retonar o CEP
     *
     * @return
     */
    public String getCep() {
        return this.CEP;
    }

    /**
     * Retorna o nome da rua, avenida, travessa, ...
     *
     * @return
     */
    public String getLogradouro() {
        return this.Logradouro;
    }

    /**
     * Retorna se tem algum complemento Ex: lado impar
     *
     * @return
     */
    public String getComplemento() {
        return this.Complemento;
    }

    /**
     * Retorna o Bairro
     *
     * @return
     */
    public String getBairro() {
        return this.Bairro;
    }

    /**
     * Retorna a Cidade
     *
     * @return
     */
    public String getLocalidade() {
        return this.Localidade;
    }

    /**
     * Retorna o UF
     *
     * @return
     */
    public String getUf() {
        return this.Uf;
    }

    /**
     * Procedimento para obtem dados via GET
     *
     * @param urlToRead endereço
     * @return conteúdo remoto
     * @throws Exception
     */
    public final String get(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } finally {
            return result.toString();
        }
    }
}
