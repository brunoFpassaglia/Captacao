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
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Configuracao implements Cloneable {

    private ArrayList<ConfiguracaoHora> cfgH;
    private Reservatorio reservatorio;
    private float fitness;
    private final ArrayList<Double> mediaConsumo;

    public Configuracao(ArrayList<Double> mediaConsumo) {
        cfgH = new ArrayList<ConfiguracaoHora>();
        reservatorio = new Reservatorio();
        this.mediaConsumo = mediaConsumo;

    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public void addCfgH(ArrayList<ConfiguracaoHora> cfgHh) {
        this.cfgH.addAll(cfgHh);
    }

    public ArrayList<ConfiguracaoHora> getCfgH() {
        return cfgH;
    }

    public Configuracao clone() {
        Configuracao clone = new Configuracao(mediaConsumo);
        clone.addCfgH(this.getCfgH());
        return clone;
    }

    public void geraCfg() {
        for (int i = 0; i < 24; i++) {
            Random r1 = new Random();
            double t1 = 0;
            double t2 = 0;
            double t3 = 0;
            ConfiguracaoHora c = new ConfiguracaoHora();
            do {
                if (i >= 18 && i < 21) {
                    do {
                        t1 = r1.nextInt(60);
                        t2 = r1.nextInt(60);
                        t3 = r1.nextInt(60);
                    } while ((t1 != 0 && t2 != 0 && t3 != 0) || (t1 == 0 && t2 == 0 && t3 == 0));
                    c.setTempo1(t1/60);
                    c.setTempo2(t2/60);
                    c.setTempo3(t3/60);
                }
                else{
                    do {
                        t1 = r1.nextInt(10);
                        t2 = r1.nextInt(10);
                        t3 = r1.nextInt(10);
                    } while ((t1 != 0 && t2 != 0 && t3 != 0) || (t1 == 0 && t2 == 0) || (t1 == 0 && t3 == 0) || (t2 == 0 && t3 == 0));
                    
                    if (t1 != 0) {
                        c.setTempo1(1);
                    } else {
                        c.setTempo1(0);
                    }
                    if (t2 != 0) {
                        c.setTempo2(1);
                    } else {
                        c.setTempo2(0);
                    }
                    if (t3 != 0) {
                        c.setTempo3(1);
                    } else {
                        c.setTempo3(0);
                    }
                }
                c.calculaVazao();
            } while (!c.validaVazao());
            cfgH.add(c);
        }
    }

    public boolean validaCfg() {
        for (int i = 0; i < cfgH.size(); i++) {
            double base = 4600;
            base = base + cfgH.get(i).getVazao() - mediaConsumo.get(i);
            if (base < 3066 | base > 9200) {
                return false;
            }
        }
        return true;
    }

    public void calculoFitness() {
        float acumulado = 0;
        for (int i = 0; i < 24; i++) {
            double tarifa;
            if (i >= 18 && i < 21) {
                tarifa = 0.59;
            } else {
                tarifa = 0.14;
            }

            acumulado += (441 * cfgH.get(i).getTempo1() * tarifa) + (331 * cfgH.get(i).getTempo2() * tarifa) + (441 * cfgH.get(i).getTempo3() * tarifa);

        }
        setFitness(acumulado);
    }

    public void mutate() {
        int a;
        int b;
        do {
            Random r = new Random();
            Random rr = new Random();
            int high = 24;
            int low = 21;
            a = r.nextInt(18);
            b = rr.nextInt(high - low) + low;
        } while (a == b);
        Collections.swap(cfgH, a, b);
    }

    public void cross() {
        //cruzar 2 pontos iguais de individuos, gerar 2 filhos//
    }

    public void printCfg() {
        for (int i = 0; i < 24; i++) {
            System.out.println("hora: " + i);
            System.out.println(cfgH.get(i).getTempo1() + " | " + cfgH.get(i).getTempo2() + " | " + cfgH.get(i).getTempo3() + " ---> " + cfgH.get(i).getTempo1()*60 + " | " + cfgH.get(i).getTempo2()*60 + " | " + cfgH.get(i).getTempo3()*60);
        }
        System.out.println("fitness " + fitness);
    }
}
