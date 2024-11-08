import java.util.*;
import java.util.stream.Collectors;

// Clase principal que administra la colección de mensajes y proporciona métodos de gestión.
public class SistemaMensajes {
    private static SistemaMensajes instancia;
    private ArrayList<Mensaje> misMensajes;

    private SistemaMensajes() {
        misMensajes = new ArrayList<>();
    }

    public static SistemaMensajes getInstance() {
        if (instancia == null) {
            instancia = new SistemaMensajes();
        }
        return instancia;
    }

    public void inMensaje(Mensaje m) {
        misMensajes.add(m);
    }

    public void reproducirTodosMisMensajes() {
        for (Mensaje mensaje : misMensajes) {
            System.out.println(mensaje.toString());
            mensaje.reproducir();
        }
    }

    public void borrarMensajesPorRemitente(String remitente) {
        misMensajes.removeIf(mensaje -> mensaje.getRemitente().equals(remitente));
        System.out.println("Mensajes de " + remitente + " eliminados.");
    }

    public Map<String, List<Mensaje>> buscarMensajesPorRemitente() {
        return misMensajes.stream()
                .collect(Collectors.groupingBy(Mensaje::getRemitente));
    }

    public ArrayList<Mensaje> buscarMensajesPorTipo(Class<?> tipoMensaje) {
        return misMensajes.stream()
                .filter(mensaje -> mensaje.getClass().equals(tipoMensaje))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void ordenarMensajesPorRemitente() {
        misMensajes.sort(Comparator.comparing(Mensaje::getRemitente));
    }

    public void reproducirMensajePorIndice(int indice) {
        if (indice >= 0 && indice < misMensajes.size()) {
            misMensajes.get(indice).reproducir();
        } else {
            System.out.println("Índice no válido.");
        }
    }

    // Método main con opciones de menú para la interacción del usuario.
    public static void main(String[] args) {
        SistemaMensajes sistema = SistemaMensajes.getInstance();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Bienvenido");
            System.out.println("1. Insertar Mensaje");
            System.out.println("2. Leer Todos los Mensajes");
            System.out.println("3. Borrar mensajes de un remitente");
            System.out.println("4. Ver todos los mensajes de un remitente");
            System.out.println("5. Ver mensajes por tipo");
            System.out.println("6. Ordenar mensajes por remitente");
            System.out.println("7. Reproducir mensaje por índice");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el tipo de mensaje (texto, fax, audio):");
                    String tipo = sc.nextLine();
                    System.out.println("Ingrese el remitente:");
                    String remitente = sc.nextLine();
                    System.out.println("Ingrese el destinatario:");
                    String destinatario = sc.nextLine();
                    System.out.println("Ingrese el contenido:");
                    String contenido = sc.nextLine();
                    sistema.inMensaje(MensajeFactory.crearMensaje(tipo, remitente, destinatario, contenido));
                    break;
                case 2:
                    sistema.reproducirTodosMisMensajes();
                    break;
                case 3:
                    System.out.println("Ingrese el remitente de los mensajes a borrar:");
                    String remitenteBorrar = sc.nextLine();
                    sistema.borrarMensajesPorRemitente(remitenteBorrar);
                    break;
                case 4:
                    Map<String, List<Mensaje>> mensajesPorRemitente = sistema.buscarMensajesPorRemitente();
                    mensajesPorRemitente.forEach((rem, mensajes) -> {
                        System.out.println("Remitente: " + rem);
                        mensajes.forEach(Mensaje::reproducir);
                    });
                    break;
                case 5:
                    System.out.println("Ingrese el tipo de mensaje (Texto, Fax, Audio):");
                    String tipoBuscar = sc.nextLine();
                    ArrayList<Mensaje> mensajesTipo = new ArrayList<>();
                    if (tipoBuscar.equalsIgnoreCase("Texto")) {
                        mensajesTipo = sistema.buscarMensajesPorTipo(MensajeTexto.class);
                    } else if (tipoBuscar.equalsIgnoreCase("Fax")) {
                        mensajesTipo = sistema.buscarMensajesPorTipo(MensajeFax.class);
                    } else if (tipoBuscar.equalsIgnoreCase("Audio")) {
                        mensajesTipo = sistema.buscarMensajesPorTipo(MensajeAudio.class);
                    } else {
                        System.out.println("Tipo no válido.");
                        break;
                    }
                    mensajesTipo.forEach(Mensaje::reproducir);
                    break;
                case 6:
                    sistema.ordenarMensajesPorRemitente();
                    System.out.println("Mensajes ordenados por remitente.");
                    break;
                case 7:
                    System.out.println("Ingrese el índice del mensaje a reproducir:");
                    int indice = sc.nextInt();
                    sistema.reproducirMensajePorIndice(indice);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);

        sc.close();
    }
}
