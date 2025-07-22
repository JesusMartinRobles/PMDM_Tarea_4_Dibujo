package com.martinrobles.dibujando;

// Clase para crear una cuádrupla
public class Quadruple<A, B, C, D> {
    private A first; // Se crea el primer elemento
    private B second; // Se crea el segundo elemento
    private C third; // Se crea el tercer elemento
    private D fourth; // Se crea el cuarto elemento

    // Constructor de la clase con atributos
    public Quadruple(A first, B second, C third, D fourth) {
        this.first = first; // Establece el primer elemento
        this.second = second; // Establece el segundo elemento
        this.third = third; // Establece el tercer elemento
        this.fourth = fourth; // Establece el cuarto elemento
    }

    // Método para obtener el primer elemento
    public A getFirst() {
        return first;
    }

    // Método para obtener el segundo elemento
    public B getSecond() {
        return second;
    }

    // Método para obtener el tercer elemento
    public C getThird() {
        return third;
    }

    // Método para obtener el cuarto elemento
    public D getFourth() {
        return fourth;
    }
}
