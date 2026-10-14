import org.iesalandalus.programacion.utilidades.Entrada;

import java.io.*;


public class Lector {
    public static void main(String[] args) {
        System.out.print("Escribe el numero a sumar 10 veces: ");
        int numero = Entrada.entero();
        ProcessBuilder escritor = new ProcessBuilder("java","-cp",System.getProperty("java.class.path"),"C:\\Users\\usuario\\IdeaProjects\\Melero_Garcia_Lucas_PSP01_TareaComunicacionProcesos\\src\\SubProcesoEscritor.java");

        escritor.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        escritor.redirectError(new File("C:\\Users\\usuario\\Music\\errores\\JarError.txt"));
        try {
            Process escritorHijo = escritor.start();
            BufferedWriter BFescritor = new BufferedWriter(new OutputStreamWriter(escritorHijo.getOutputStream()));
            BFescritor.write(numero);
            BFescritor.newLine();
            BufferedReader BFlector = new BufferedReader(new InputStreamReader(escritorHijo.getInputStream()));
            String linea;
            while ((linea = BFlector.readLine()) != null){
                System.out.println(linea);
            }
            try {
                escritorHijo.waitFor();
            } catch (InterruptedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }


    }
}
