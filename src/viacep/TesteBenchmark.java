/*
 * TesteBenchmark
 * https://github.com/gilberto-torrezan
 */
package viacep;

/**
 * Teste do ViaCEP basedo em https://github.com/parg-programador/ViaCEP/issues/1
 * @author Gilberto Torrezan Filho
 */
public class TesteBenchmark {

    public static void main(String[] args) {
        System.out.println("Starting BenchmarkViaCEPClient...");

        ViaCEP client = new ViaCEP();

        String[] ceps = new String[]{"20930-040", "01311-000", "30140-010", "40026-040", "90010-273", "50030-000", "65010-970", "69900-094", "70050-000", "80010-150"};

        int numSteps = 1000;
        long sleep = 100;

        for (int step = 0; step < numSteps; step++) {
            for (int i = 0; i < ceps.length; i++) {
                String cep = ceps[i];
                try {
                    client.buscar(cep);
                    System.out.println(client.getLocalidade());

                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }

        System.out.println("BenchmarkViaCEPClient finished.");
    }
    
}
