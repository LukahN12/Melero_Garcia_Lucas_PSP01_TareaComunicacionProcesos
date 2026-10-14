import org.iesalandalus.programacion.utilidades.Entrada;

public class SubProcesoEscritor {
    public static void main(String[] args) {
        System.out.print("Ingresa el valor a sumar: ");
        int valor = Entrada.entero();
        for (int i = 1; i <= 10; i++){
            int resultado = i + valor;
            System.out.println("Resultado: " + resultado);
        }
    }
}
