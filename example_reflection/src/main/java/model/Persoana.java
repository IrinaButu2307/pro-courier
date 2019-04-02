package model;

import adnotari.InjectValue;

public class Persoana {
    @InjectValue(numeP="Anthony")
    private String nume;
    private float salariu;

    private Persoana() {
    }

    private Persoana(String nume, float salariu) {
        this.nume = nume;
        this.salariu = salariu;
    }

    private void maresteSalariu() {
        this.salariu = 1.2f * salariu;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", salariu=" + salariu +
                '}';
    }
}
