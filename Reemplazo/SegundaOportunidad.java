import java.util.LinkedList;

class Pagina {
    int numeroPagina;
    boolean referenciada;

    public Pagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
        this.referenciada = false;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public boolean isReferenciada() {
        return referenciada;
    }

    public void setReferenciada(boolean referenciada) {
        this.referenciada = referenciada;
    }
}

public class SegundaOportunidad {
    private LinkedList<Pagina> cola;
    private int indiceActual;
    private int numMarcos;

    public SegundaOportunidad(int numMarcos) {
        this.numMarcos = numMarcos;
        cola = new LinkedList<>();
        indiceActual = 0;
    }

    public boolean agregarPagina(int pagina) {
        if (cola.size() < numMarcos) {
            cola.add(new Pagina(pagina));
            return true; // Página agregada exitosamente
        } else {
            // Reemplazar página
            reemplazarPagina();
            cola.add(new Pagina(pagina));
            return true;
        }
    }

    public int reemplazarPagina() {
        int indice = indiceActual;
        do {
            Pagina pagina = cola.get(indice);
            if (!pagina.isReferenciada()) {
                cola.remove(indice);
                return pagina.getNumeroPagina();
            }
            pagina.setReferenciada(false);
            indice = (indice + 1) % numMarcos;
        } while (indice != indiceActual);

        // Si se recorrió toda la cola sin encontrar una página no referenciada,
        // se ha producido una falla de página debido a thrashing
        return -1;
    }

    public void marcarPaginaComoReferenciada(int pagina) {
        for (Pagina p : cola) {
            if (p.getNumeroPagina() == pagina) {
                p.setReferenciada(true);
                break;
            }
        }
    }
    public static void main(String[] args) {
        SegundaOportunidad algoritmo = new SegundaOportunidad(3); // 3 marcos de página
        // Simular referencias a páginas
        algoritmo.agregarPagina(1);
        algoritmo.agregarPagina(2);
        algoritmo.agregarPagina(3);
        algoritmo.agregarPagina(4); // Reemplaza una página

        algoritmo.marcarPaginaComoReferenciada(2); // Marcar la página 2 como referenciada
        algoritmo.agregarPagina(5); // Reemplaza una página
    }
}