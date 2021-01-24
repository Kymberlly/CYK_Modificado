/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyk.Classes;

import java.util.ArrayList;

/**
 *
 * @author Kym
 */
public class Gramatica {
  
    private int i=0;
    private ArrayList<Transicoes> transicoes = new ArrayList<>();
    private String[]alfabeto;
    private String partida;
    private String[]nao_terminais;
    private String[]terminais;
    private String[] anulaveis;
    private String[] relacaoUnitaria;
    private String[] naoTerminaisDisponiveis;
    private String[] relacaoUnitariaReversa;
    
    public Gramatica(){}
    public Gramatica(ArrayList<Transicoes> transicoes, String[]alfabeto, String partida){
        this.transicoes = transicoes;
        this.alfabeto = alfabeto;
        this.partida = partida;
    }
    
    //Setando valor as variaveis da classe Gramatica
    public void setTransicao(Transicoes t){
        this.transicoes.add(t);
    }
    
    public void setTamAlfabeto(int tam){
        this.alfabeto = new String[tam];
    }
    
    public void setAlfabeto(String alfa){
        this.alfabeto[i] = alfa;
        i++;
    }
    
    public void setPartida(String partida){
        this.partida = partida;   
    }
    
    //Pegando valor das variaveis da classe Gramatica
    public Transicoes getTransicao(int j){
        return this.transicoes.get(j);
    }
    
    public String[] getAlfabeto(){
        return this.alfabeto;
    }
    
    public String getPartida(){
        return this.partida;
    }
    
    
    /**
     * mostrarTransicoes()
     * Mostra todas as transicoes existentes
     * no ArrayList de Transicoes
     */
    public void mostrarTransicoes(){
        for(int j=0; j<transicoes.size(); j++){
            System.out.println(transicoes.get(j).getVariavel()+" -> "+transicoes.get(j).getTransicao());
        }
    }
    
    
    /**
     * mostrarGramatica()
     * Mostra toda a estrutura da classe 
     * gramatica preenchida atraves da leitura do arquivo
     */
    public void mostrarGramatica(){
        System.out.println("Partida: "+partida);
        
        System.out.println("Alfabeto: ");
        for(String alfabeto1 : alfabeto){
            System.out.println(alfabeto1);
        }
        
        System.out.println("Transicoes: (Variavel -> Transicao)");
        for(int j=0; j<transicoes.size(); j++){
            System.out.println(transicoes.get(j).getVariavel()+" -> "+transicoes.get(j).getTransicao());
        }
    }
    
}
