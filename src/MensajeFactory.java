// Clase MensajeFactory que centraliza la creación de mensajes de diferentes tipos.
public class MensajeFactory {
    public static Mensaje crearMensaje(String tipo, String remitente, String destinatario, String contenido) {
        switch (tipo.toLowerCase()) {
            case "texto":
                return new MensajeTexto(remitente, destinatario, contenido);
            case "fax":
                return new MensajeFax(remitente, destinatario, contenido);
            case "audio":
                return new MensajeAudio(remitente, destinatario, contenido);
            default:
                throw new IllegalArgumentException("Tipo de mensaje desconocido: " + tipo);
        }
    }
}
