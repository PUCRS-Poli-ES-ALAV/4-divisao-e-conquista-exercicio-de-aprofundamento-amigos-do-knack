package br.pucrs;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
    //     int [] vetor = geraVetor(50000000, 50000000);
    //     long t = System.nanoTime();
    //     int maior = maxVal1(vetor, vetor.length);
    //     System.out.println("\nmaior: " + maior + "\ntempo: " + (System.nanoTime() - t)/1000000);
    //     t = System.nanoTime();
    //     int maior1 = maxVal2(vetor, 0, vetor.length - 1);
    //     System.out.println("\noutro maior: " + maior1 + "\ntempo: " + (System.nanoTime() - t)/1000000);
        System.out.println(multiply("0101", "1101"));
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
                novoNum = rnd.nextInt(9999)+1;

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

    public static int maxVal1(int A[], int n) {  
        int max = A[0];
        for (int i = 1; i < n; i++) {  
            if( A[i] > max ) 
               max = A[i];
        }
        return max;
    }

    public static int maxVal2(int A[], int init, int end) {  
        if (end - init <= 1)
            return max(A[init], A[end]);  
        else {
              int m = (init + end)/2;
              int v1 = maxVal2(A,init,m);   
              int v2 = maxVal2(A,m+1,end);  
              return max(v1,v2);
             }
    }

    public static int max(int a, int b) { 
        if (a > b) 
            return a;
        else 
            return b;
    }

    public static long multiply(String X, String Y) {
        
        int tam = Math.max(X.length(), Y.length());

        int bits = 1;
        while (bits < tam) {
            bits *= 2;
        }
        
        X = tamanhoString(X, bits);
        Y = tamanhoString(Y, bits);
        
        return multiplyAux(X, Y, bits);
    }
    
    private static long multiplyAux(String X, String Y, int tam) {
        // Base do algoritmo: se o comprimento for 1, apenas multiplique os bits
        if (tam == 1) {
            return Long.parseLong(String.valueOf(X.charAt(0))) * Long.parseLong(String.valueOf(Y.charAt(0)));
        }
        
        // Divida as strings em duas partes
        int m = tam / 2;
        String a = X.substring(0, m);
        String b = X.substring(m);
        String c = Y.substring(0, m);
        String d = Y.substring(m);
        
        // Recursivamente calcule os produtos
        long e = multiplyAux(a, c, m);
        long f = multiplyAux(b, d, m);
        long g = multiplyAux(b, c, m);
        long h = multiplyAux(a, d, m);
        
        // Combine os resultados para obter o produto final
        return (1L << (2 * m)) * e + (1L << m) * (g + h) + f;
    }
    
    // Função auxiliar para preencher a string de bits até o comprimento desejado
    private static String tamanhoString(String str, int length) {
        while (str.length() < length) {
            str = "0" + str;
        }
        return str;
    }
}
