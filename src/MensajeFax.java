import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Clase que representa un mensaje de fax, extiende Mensaje y reproduce su contenido desde un archivo.
public class MensajeFax extends Mensaje {
    private String nombreArchivo;

    public MensajeFax(String remitente, String destinatario, String nombreArchivo) {
        super(remitente, destinatario);
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void reproducir() {
        System.out.println("Imprimiendo fax de " + remitente);
        File archivo = new File(nombreArchivo);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
        }
    }

    @Override
    public String toString() {
        return "Mensaje de Fax de " + remitente + " a " + destinatario;
    }
}
