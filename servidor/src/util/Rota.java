package util;

import java.util.ArrayList;

/**
 * Created by wanderson on 14/08/17.
 */
public class Rota {
    
    private String local;
    private int peso;
    private ArrayList<String> interessados = new ArrayList();

    public Rota(String local, int peso) {
        this.local = local;
        this.peso = peso;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Rota) {
//            Rota rota = (Rota) obj;
//            if (this.local.equals(rota.local)) {
//                
//            } else {
//                return false;
//            }
//        }
//
//        return false;
//    }

    public ArrayList<String> getInteressados() {
        return interessados;
    }  
}
