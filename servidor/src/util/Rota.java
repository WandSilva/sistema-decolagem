package util;

/**
 * Created by wanderson on 14/08/17.
 */
public class Rota {
    private String local;
    private int peso;

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

}
