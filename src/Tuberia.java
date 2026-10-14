import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tuberia {
    public static void main(String[] args) {
        String nombreApp = "notepad";
        long PID = creaProcesoNotepad(nombreApp);
        System.out.println("------");
        try {
            Thread.sleep(5000);
            System.out.println("Han pasado 5 segundos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        creaProcesoTaskkill(PID);
    }

    private static long creaProcesoNotepad(String app) {
        long PID = -1;
        ProcessBuilder pbNotepad = new ProcessBuilder( "cmd.exe","/c","for /f \"tokens=2\" %a in ('tasklist /fi \"imagename eq " + app + ".exe\" /nh') do @echo %a");
        try {
            Process procesoNotepad = pbNotepad.start();
            System.out.println("Notepad alcanzado");
            BufferedReader br = new BufferedReader(new InputStreamReader(procesoNotepad.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("PID: " + linea);
                PID = Long.parseLong(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
        return PID;
    }

    private static void creaProcesoTaskkill(long PID){
        ProcessBuilder pbTK = new ProcessBuilder("cmd.exe","/c","taskkill /PID " + PID);
        try {
            Process procesoTK = pbTK.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(procesoTK.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("OUTPUT: " + linea);
            }
            br.close();

            BufferedReader err = new BufferedReader(new InputStreamReader(procesoTK.getErrorStream()));
            while ((linea = err.readLine()) != null) {
                System.out.println("ERROR: " + linea);
            }
            err.close();
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
    }
}