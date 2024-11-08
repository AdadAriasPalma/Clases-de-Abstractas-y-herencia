// Clase que representa un mensaje de texto, extiende Mensaje y define cómo se reproduce un mensaje de texto.
public class MensajeTexto extends Mensaje {
    private String texto;

    public MensajeTexto(String remitente, String destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo mensaje de texto de " + remitente + ": " + texto);
    }

    @Override
    public String toString() {
        return "Mensaje de Texto de " + remitente + " a " + destinatario;
    }
}
