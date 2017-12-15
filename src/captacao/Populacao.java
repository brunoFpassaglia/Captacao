/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Populacao {

    private ArrayList<Configuracao> cromossomos;
    private ArrayList<Double> mediaConsumo;
    private int tamanho;
    private ArrayList<Configuracao> melhores;

    public Populacao(int tamanho) {
        cromossomos = new ArrayList<Configuracao>();
        melhores = new ArrayList<Configuracao>();
        mediaConsumo = new ArrayList<Double>();
        this.tamanho = tamanho;
    }

    public void geraMedia() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:/Faculdade/IC_Angelita/Captacao/medias.txt"));
        while (scanner.hasNextDouble()) {
            double value = scanner.nextDouble();
            mediaConsumo.add(value);
        }
    }

    public void geraPopulacao() throws FileNotFoundException {
        for (int i = 0; i < tamanho; i++) {
            Configuracao c;
            do {
                c = new Configuracao(mediaConsumo);
                c.geraCfg();
            } while (!c.validaCfg());
            cromossomos.add(c);
        }
    }

    public void calcAptidao() {
        for (Configuracao cromossomo : cromossomos) {
            cromossomo.calculoFitness();
        }
    }

    public void sortPopulacao() {
        Collections.sort(cromossomos, new Comparator<Configuracao>() {
            @Override
            public int compare(Configuracao p1, Configuracao p2) {
                if (p1.getFitness() < p2.getFitness()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public void printBest() {
        cromossomos.get(tamanho - 1).printCfg();
    }

    public void mutate() {
        for (Configuracao cromossomo : cromossomos) {
            cromossomo.mutate();
        }
    }
    
    // selecionar também os piores
    public void selecaoCross(double taxa) {
        melhores = new ArrayList<Configuracao>();
        for (int i = tamanho - (int) (tamanho * (taxa / 100)); i < tamanho; i++) {
            melhores.add(cromossomos.get(i));
        }
    }
    
    
    //testar 10 por cento dos melhores com 10 por cento dos piores
    public void cross() {
        Random r = new Random();
        ArrayList<Configuracao> novos;
        for (int i = 0; i < melhores.size() - 1; i += 2) {
            novos = new ArrayList<Configuracao>();
            Configuracao filho1;
            Configuracao filho2;
            filho1 = new Configuracao(mediaConsumo);
            filho2 = new Configuracao(mediaConsumo);
            filho1 = melhores.get(i).clone();
            filho2 = melhores.get(i + 1).clone();
            do {
                int n = r.nextInt(24);
                filho1.getCfgH().set(n, melhores.get(i + 1).getCfgH().get(n));
                filho2.getCfgH().set(n, melhores.get(i).getCfgH().get(n));
            } while (!filho1.validaCfg() || !filho2.validaCfg());
            filho1.calculoFitness();
            filho2.calculoFitness();
            novos.add(filho1);
            novos.add(filho2);
            Collections.sort(novos, new Comparator<Configuracao>() {
                @Override
                public int compare(Configuracao p1, Configuracao p2) {
                    if (p1.getFitness() < p2.getFitness()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            cromossomos.set(cromossomos.indexOf(melhores.get(i)), novos.get(0));
            cromossomos.set(cromossomos.indexOf(melhores.get(i+1)), novos.get(1));
        }
    }

    public void printPopulacao() {
        cromossomos.forEach((cromossomo) -> {
            System.out.println("fitness:" + cromossomo.getFitness());
        });
    }
}
