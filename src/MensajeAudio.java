// Clase que representa un mensaje de audio, extiende Mensaje y simula la reproducción del archivo de audio.
public class MensajeAudio extends Mensaje {
    private String nombreArchivoAudio;

    public MensajeAudio(String remitente, String destinatario, String nombreArchivoAudio) {
        super(remitente, destinatario);
        this.nombreArchivoAudio = nombreArchivoAudio;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo mensaje de audio desde el archivo: " + nombreArchivoAudio);
    }

    @Override
    public String toString() {
        return "Mensaje de Audio de " + remitente + " a " + destinatario;
    }
}
