/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author viber
 */

public class Navios {
    private int tam;
    private int vertIni;
    private int horIni;
    private boolean rotacao;
    private final int ocupado[][] = new int[tam][2];
    void pos(int tam, int vertIni, int horIni, boolean rotacao){
        ocupado[0][0] = vertIni;
        ocupado[0][1] = horIni;
        for(int i=1;i==tam-1;i++){
            if(!rotacao){
                ocupado[i][0] = vertIni;
                ocupado[i][1] = horIni+1;
            }
            else{
                ocupado[i][0] = vertIni+1;
                ocupado[i][1] = horIni;
            }
        }
    }
}
