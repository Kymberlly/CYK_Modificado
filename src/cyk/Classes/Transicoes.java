/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyk.Classes;

/**
 *
 * @author Kym
 */
public class Transicoes {
    private String variavel;
    private String transicao;
    
    public Transicoes(){}
    public Transicoes(String variavel, String transicao){
        this.variavel = variavel;
        this.transicao = transicao;
    }
        
    
    //Setando valor as variaveis da classe transicao
    public void setVariavel(String variavel){
        this.variavel = variavel;
    }
    
    public void setTransicao(String transicao){
        this.transicao = transicao;
    }
    
    //Pegando valor das variaveis da classe transicao
    public String getVariavel(){
        return this.variavel;
    }
    
    public String getTransicao(){
        return this.transicao;
    }
}
