import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCAN {
    public static void main(String[] args) {
        // Solicitudes de acceso al disco
        List<Integer> solicitudes = List.of(82, 170, 43, 140, 24, 16, 190);
        int inicio = 50; // Posición inicial del cabezal
        int direccion = 1; // 1 para hacia adelante, -1 para hacia atrás
        int maxCilindro = 200; // Número máximo de cilindros

        ejecutarSCAN(solicitudes, inicio, direccion, maxCilindro);
    }

    public static void ejecutarSCAN(List<Integer> solicitudes, int inicio, int direccion, int maxCilindro) {
        List<Integer> izquierda = new ArrayList<>();
        List<Integer> derecha = new ArrayList<>();
        List<Integer> recorrido = new ArrayList<>();

        // Dividir las solicitudes en dos listas: hacia adelante y hacia atrás
        for (int solicitud : solicitudes) {
            if (solicitud < inicio) {
                izquierda.add(solicitud);
            } else {
                derecha.add(solicitud);
            }
        }

        // Ordenar ambas listas
        Collections.sort(izquierda);
        Collections.sort(derecha);

        // Mover el cabezal según la dirección
        if (direccion == 1) {
            recorrido.addAll(derecha); // Atender solicitudes hacia adelante
            recorrido.add(maxCilindro); // Ir hasta el final del disco
            Collections.reverse(izquierda); // Invertir el orden para atender solicitudes al regresar
            recorrido.addAll(izquierda);
        } else {
            recorrido.addAll(izquierda); // Atender solicitudes hacia atrás
            recorrido.add(0); // Ir hasta el inicio del disco
            Collections.reverse(derecha); // Invertir el orden para atender solicitudes al regresar
            recorrido.addAll(derecha);
        }

        // Calcular el tiempo total de movimiento
        int tiempoMovimiento = calcularTiempoMovimiento(recorrido, inicio);

        // Imprimir resultados
        System.out.println("Orden de acceso del cabezal:");
        System.out.println(recorrido);
        System.out.println("Tiempo total de movimiento: " + tiempoMovimiento);
    }

    public static int calcularTiempoMovimiento(List<Integer> recorrido, int inicio) {
        int tiempo = 0;
        int posicionActual = inicio;

        for (int cilindro : recorrido) {
            tiempo += Math.abs(cilindro - posicionActual);
            posicionActual = cilindro;
        }

        return tiempo;
    }
}

