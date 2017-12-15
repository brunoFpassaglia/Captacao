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
public class ConfiguracaoHora {    
    private double tempo1;
    private double tempo2;
    private double tempo3;

    /*vazao total da configuracao*/
    private double vazao;
    
    /*construtor*/
    //public ConfiguracaoHora(){
        
    //}
  
    
   
    
    public void calculaVazao(){
        double valor = 0;
        valor = (594 * tempo1) + (432 * tempo2) + (594 * tempo3);
        setVazao(valor);
    }
    
    public boolean validaVazao(){
        if(vazao <= 1188)
            return true;
        return false;
    }
    
    public void setVazao(double vazao){
        this.vazao = vazao;
    }
    
    /*retorna a vazão da configuração*/
    public double getVazao(){
        return vazao;
    }
    
    public double getTempo1() {
        return tempo1;
    }

    public void setTempo1(double tempo1) {
        this.tempo1 = tempo1;
    }

    public double getTempo2() {
        return tempo2;
    }

    public void setTempo2(double tempo2) {
        this.tempo2 = tempo2;
    }

    public double getTempo3() {
        return tempo3;
    }

    public void setTempo3(double tempo3) {
        this.tempo3 = tempo3;
    }
    
}
