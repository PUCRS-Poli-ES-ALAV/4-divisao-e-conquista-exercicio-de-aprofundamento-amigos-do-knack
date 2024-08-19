package br.pucrs;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int [] vetor = geraVetor(524288, 524288);
        vetor = merge_sort(vetor);
        System.out.println("Vetor gerado: ");
        for (int i = 0; i < vetor.length; i++){
            System.out.print(vetor[i] + " ");
        }
    }

    public static int[] merge_sort (int[] lista){
        if (lista.length == 1){
            return lista;
        }

        int meio = lista.length / 2;

        int[] esquerda = new int[meio];

        for (int i=0; i<meio; i++){
            esquerda[i] = lista[i];
        }
        
        int[] direita = new int[lista.length - meio];

        for (int i=meio; i<lista.length; i++){
            direita[i - meio] = lista[i];
        }

        esquerda = merge_sort(esquerda);
        direita = merge_sort(direita);
        lista = merge(esquerda, direita);

        return lista;
    }

    public static int[] merge (int[] esquerda, int[] direita){
        int[] lista = new int[esquerda.length + direita.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < esquerda.length && j < direita.length){
            if (esquerda[i] < direita[j]){
                lista[k] = esquerda[i];
                i++;
            } else {
                lista[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < esquerda.length){
            lista[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < direita.length){
            lista[k] = direita[j];
            j++;
            k++;
        }

        return lista;
    }

    private static int[] geraVetor(int nroPares, int nroImpares){
        int [] res = null;
        int contPar = 0, contImpar = 0, novoNum;
        Random rnd = new Random();

        if ((nroPares >= 0) ||
                (nroImpares >= 0) &&
                (nroPares + nroImpares > 0)) {

            res = new int[nroPares + nroImpares];

            while ((contPar < nroPares) || (contImpar < nroImpares)) {
                novoNum = rnd.nextInt(998)+1;

                if ((novoNum % 2 == 0) && (contPar < nroPares)) {
                    res[contPar+contImpar] = novoNum;
                    contPar++;
                }
                else if ((novoNum % 2 == 1) && (contImpar < nroImpares)) {
                    res[contPar+contImpar] = novoNum;
                    contImpar++;
                }
            }
        }

        return res;
    }

    public static long maxVal1(long A[], int n) {  
        long max = A[0];
        for (int i = 1; i < n; i++) {  
            if( A[i] > max ) 
               max = A[i];
        }
        return max;
    }
}
