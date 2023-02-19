/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.com.edjuniorcoder.exemplolistaconcorrente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jerry
 */
public class ExemploListaConcorrente {

    public static void main(String[] args) {
        
         // Instanciação da lista concorrente
         List<String> temp_list = new ArrayList<String>();

        // Adição de elementos na lista concorrente
        for (int i = 1; i < 100000; i++) {
            temp_list.add("linha " + i);
        }
        
        // Instanciação da lista concorrente e adição elementos na list
        // CopyOnWriteArrayList: essa lista é otimizada para leitura, o que significa que as operações de leitura são mais rápidas do que as operações de escrita
        CopyOnWriteArrayList<String> listaConcorrente = new CopyOnWriteArrayList<String>(temp_list);

        int totalThreads = 10;

        System.out.println("totalThreads: " + totalThreads);

        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

        for (int i = 1; i <= totalThreads; i++) {
            int target = i;

            executor.submit(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {

                        if (temp_list.size() > 0) {
                            String linha = listaConcorrente.get(0);
                            listaConcorrente.remove(0);

                            System.out.println(linha + " : " + target + ":" + target);
                        }

                    }

                }
            });

        }

    }
}
