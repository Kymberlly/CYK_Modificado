public class CYK_mod{

    public List<String> criarFechoUnitario(String a){
        List<String> fecho = new ArrayList<String>();
        fecho.add(a);
        List<String> tmp = new ArrayList<String>();
        tmp.add(""+a);
        while(tmp.size() != 0){
            String var = tmp.get(0);
            tmp.remove(0);
            for(String s : relacaoUnitariaReversa){
                if((s.charAt(1)+"").equals(var) ){
                    if(!fecho.contains(s.charAt(3)+"")){
                        tmp.add(s.charAt(3)+"");
                        fecho.add(s.charAt(3)+"");
                    }
                }
            }
        }
        return fecho;
    }

    
    public  void formaNormalBinaria(){
        List<Regra> regrasBinarias = new ArrayList<Regra>();
        for(Regra r : regras){
            regrasBinarias.addAll(r.formaBinaria(naoTerminaisDisponiveis));
        }

        if(regrasBinarias.size() > this.regras.size()){
            this.setRegras(regrasBinarias);
            for(Regra r : regras)
                this.naoTerminais.add(r.simboloInicial);
        }
    }

    /**
     * Metodo para coletar a relacao unitaria entre as regras e suas producoes
     * para quando uma regra X -> A onde A e' unitario. sendo Unitario pela unidade
     * ou por ser unico nao anulavel na producao.
     * Relacao unitaria reversa A -> X para utilizacao do grafo posteriormente.
     */
    public void relacaoUnitaria(){
        List<String> relacaoUnitaria = new ArrayList<String>();
        List<String> relacaoUnitariaReversa = new ArrayList<String>();
        for(Regra r : regras){
            for(String s : r.producoes){
                /*Se for um unico elemento e nao for lambda e nem anulavel, crio a relacao unitaria e a reversa*/
                if(s.length() == 1 && !s.equals("?") && !anulaveis.contains(s)){
                    String novaRelacao = "("+r.getSimboloInicial() + "," + s + ")";
                    if(!relacaoUnitaria.contains(novaRelacao))
                        relacaoUnitaria.add(novaRelacao);
                    String novaRelacaoReversa = "("+ s + "," + r.getSimboloInicial() + ")";
                    if(!relacaoUnitariaReversa.contains(novaRelacaoReversa))
                        relacaoUnitariaReversa.add(novaRelacaoReversa);
                    /*se for maior que um tenho que testar as possibilidades*/
                }else if(s.length() > 1){
                    if(!anulaveis.contains(s.charAt(0)+"") && anulaveis.contains(s.charAt(1)+"")){
                        String novaRelacao = "("+r.getSimboloInicial() + "," + s.charAt(0) + ")";
                        if(!relacaoUnitaria.contains(novaRelacao))
                            relacaoUnitaria.add(novaRelacao);
                        String novaRelacaoReversa = "("+ s.charAt(0) + "," + r.getSimboloInicial() + ")";
                        if(!relacaoUnitariaReversa.contains(novaRelacaoReversa))
                            relacaoUnitariaReversa.add(novaRelacaoReversa);
                    }else if(anulaveis.contains(s.charAt(0)+"")){
                        String novaRelacao = "("+r.getSimboloInicial() + "," + s.charAt(1) + ")";
                        if(!relacaoUnitaria.contains(novaRelacao))
                            relacaoUnitaria.add(novaRelacao);
                        String novaRelacaoReversa = "("+ s.charAt(1) + "," + r.getSimboloInicial() + ")";
                        if(!relacaoUnitariaReversa.contains(novaRelacaoReversa))
                            relacaoUnitariaReversa.add(novaRelacaoReversa);
                    }
                }
            }
        }

        System.out.println("Ug: " + relacaoUnitaria);
        this.relacaoUnitaria = relacaoUnitaria;
        System.out.println("Ûg: " + relacaoUnitariaReversa);
        this.relacaoUnitariaReversa = relacaoUnitariaReversa;
    }

    public String fechoUnitario(String s){
        String[] split = s.split(" ");
        String resposta = "";
        for(String t : split){
            resposta += listToString(criarFechoUnitario(t));
        }

        return resposta;
    }


    public String listToString(List<String> str ){
        String tmp = "";
        for(String s : str){
            tmp += s + ",";
        }
        return tmp.substring(0, tmp.length()-1);
    }

    public void CYK(String palavra) {
        String[][] T = new String[palavra.length()][palavra.length()];
        String[][] Tt = new String[palavra.length()][palavra.length()];

        for(int i = 0; i < palavra.length(); i++){
            for(int j = 0; j < palavra.length(); j++){
                T[i][j] = "";
                Tt[i][j] = "";
            }
        }

        for(int i = 0; i < palavra.length(); i++) {
            //T[i][i] =  listToString(criarFechoUnitario(palavra.charAt(i)+""));
            T[i][i] =  fechoUnitario(palavra.charAt(i)+"");
        }

        for(int j = 1; j <= palavra.length()-1; j++) {
            for(int i = j-1; i >= 0; i--) {
                for(int h = i; h <= j-1; h++) {
                    for (Regra r : regras) {
                        List<String> producoes = r.getProducoes();
                        for (String s : producoes) {
                            if(s.length() >= 2){
                                String c1 = s.charAt(0) + "";
                                String c2 = s.charAt(1) + "";
                                if (T[i][h].contains(c1) && T[h + 1][j].contains(c2)) {
                                    Tt[i][j] += r.getSimboloInicial() + " ";
                                }
                        }
                    }
                    }
                    T[i][j] = fechoUnitario(Tt[i][j]);
                }
            }
        }

        for(int i = 0; i < palavra.length(); i++){
            for(int j = 0; j < palavra.length();j++){
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        if((T[0][palavra.length()-1]).contains(this.simboloPartida)) {
            System.out.println("sim");
        }else{
            System.out.println("nao");
        }
    }
}
}
