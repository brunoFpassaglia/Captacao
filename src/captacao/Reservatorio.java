/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captacao;

/**
 *
 * @author bruno
 */
public class Reservatorio {

    /*reservatorio inicia com 4600*/
    /*ponto de corte, em qual alelo vai ocorrer a mutação e cruzamento*/
    /*usar fração de hora na conta de fitness*/

 /*todas as variaveis representadas em m³*/
    private static final double capacidadeTotal = 9200;
    private static final double volumeMinimo = 3066;
    private double volumeAtual;

    public Reservatorio() {
        this.volumeAtual = 4600;
    }

    public boolean validaVolume(double vazao) {
        if((volumeAtual + vazao - 1188) > volumeMinimo ){
            System.out.println("vazao: "+ vazao + "   novo volume" + (volumeAtual + vazao - 1188));
            return true;
        }
        return false;
    }

    public void setVolumeAtual(double volume) {
        volumeAtual = volume;
    }

    public double getVolumeAtual() {
        return volumeAtual;
    }
}
