/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyk;

import cyk.Classes.Gramatica;
import cyk.Classes.Transicoes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kym
 */
public class CYK{

    public static Gramatica g = new Gramatica();
    public static String entrada;
    
    /**
     * lerArquivo()
     * Realiza a leitura e conversao das linhas do arquivo
     * para adicionar todos os valores a variaveis
     * da classe Gramatica
     */
    public static void lerArquivo(){
        
        int var;
        String[]simb;
        
        try{
            FileReader arq = new FileReader("entrada.txt");
            BufferedReader ler = new BufferedReader(arq);
            String linha = ler.readLine();
            
            //Recebe a quantidade de (Variaveis -> Regras) a seguir
            var = Integer.parseInt(linha);
            linha = ler.readLine();
            
            //Pegando valor de cada variavel 
            //e separando variaveis de trancisoes
            for(int i=0; i<var; i++){
                separar(linha);
                linha = ler.readLine();
            }
            
            linha = ler.readLine();
            
            //separando cada simbolo e adicionando
            //ao array de simbolos na classe Gramatica
            simb = linha.split(",");    
            g.setTamAlfabeto(simb.length);
            
            for(String simb1 : simb){
                g.setAlfabeto(simb1);
            }
            
            //gravando variavel de partida
            g.setPartida(ler.readLine());
            linha = ler.readLine();
            
            //determinando entrada
            entrada = ler.readLine();
            arq.close();
        }catch(IOException e){
            System.out.println("Não foi possivel abrir o arquivo. "+e.getMessage());
        }
        
    }
    
    /**
     * separar()
     * @param linha - Linha do arquivo contendo a variavel e a transicao
     * Metodo adiciona no ArrayList da classe Gramatica
     * a variavel e a transicao de cada variavel de forma separada
     */
    public static void separar(String linha){
        String[] separa1, separa2;

        separa1 = linha.split("=");
        separa2 = separa1[1].split(" | ");
        
        for(String separa22 : separa2){
            if(!separa22.equals("|") && !separa22.equals("")){
                g.setTransicao(new Transicoes(separa1[0], separa22));
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        lerArquivo();
        System.out.println("Entrada: "+entrada);
        System.out.println("---------------");
        g.mostrarGramatica();
        
    }
    
}
