/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class CaptacaoAPP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        //ArrayList<Configuracao> populacao = new ArrayList<Configuracao>();
        //ArrayList<Double> mediaConsumo = new ArrayList<Double>(); 

        int qtdPop = 100;
        Populacao p = new Populacao(qtdPop);

        //cruzamento seleciona entre 2 pais e 2 filhos e seleciona os 2 melhores (independente de pai ou filho)
        
        // sempre 2 motores ligados, exceção no horario de ponta, que pode ter 1. nunca todos desligados
        p.geraMedia();
        p.geraPopulacao();
        int nGeracoes = 10;
        int i = 0;
        while (i < nGeracoes) {
            p.calcAptidao();
            p.sortPopulacao();
            p.printPopulacao();
            System.out.println("");
            p.printBest();
            System.out.println("");
            p.selecaoCross(50);
            p.cross();
            i++;
        }

    }

}
