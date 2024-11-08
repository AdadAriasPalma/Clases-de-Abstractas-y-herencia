// Clase abstracta Mensaje que define los atributos básicos de cualquier mensaje.
// Implementa Reproducible para garantizar que todos los mensajes se puedan reproducir.
public abstract class Mensaje implements Reproducible {
   protected String remitente;
   protected String destinatario;

   public Mensaje(String remitente, String destinatario) {
      this.remitente = remitente;
      this.destinatario = destinatario;
   }

   public String getRemitente() {
      return remitente;
   }

   public String getDestinatario() {
      return destinatario;
   }

   // Método abstracto para implementar la reproducción en subclases.
   public abstract void reproducir();
}
