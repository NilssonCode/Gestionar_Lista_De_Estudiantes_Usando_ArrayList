
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    static ArrayList<String> codigo = new ArrayList<>();
    static ArrayList<String> estudiantes = new ArrayList<>();
    static ArrayList<Integer> edad = new ArrayList<>();

    // Cada estudiante tendrá su propia lista de cursos
    static ArrayList<ArrayList<String>> cursosEstudiantes = new ArrayList<>();

    // Cursos disponibles
    static String[] cursos = {"Fundamentos de Programación", "Matemática", "Comunicación", "Base de Datos", "Inglés"};

    static int opcion;

    // FUNCIÓN 1: MENÚ PRINCIPAL
    public static void menuPrincipal() {

        String menu = "SISTEMA DE ESTUDIANTES\n\n" + "1. Agregar estudiante\n" + "2. Mostrar estudiantes\n" + "3. Actualizar estudiante\n" + "4. Eliminar estudiante\n" + "5. Mostrar cursos disponibles\n" + "6. Matricular cursos\n" + "7. Consultar cursos del estudiante\n" + "8. Retirar curso\n" + "9. Salir\n\n" + "Seleccione una opción:";

        String respuesta = JOptionPane.showInputDialog(menu);

        if (respuesta == null) {
            opcion = 9;
        } else {
            try {
                opcion = Integer.parseInt(respuesta);
            } catch (NumberFormatException e) {
                opcion = -1;
            }
        }
    }

    // FUNCIÓN 2: AGREGAR ESTUDIANTE
    public static void agregarEstudiante() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        cod = cod.trim();

        if (cod.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El código no puede estar vacío.");
            return;
        }

        if (codigo.contains(cod)) {
            JOptionPane.showMessageDialog(null, "Este código ya existe.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");

        if (nombre == null) {
            return;
        }

        nombre = nombre.trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }

        String textoEdad = JOptionPane.showInputDialog("Ingrese la edad:");

        if (textoEdad == null) {
            return;
        }

        int edadd;

        try {
            edadd = Integer.parseInt(textoEdad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una edad numérica.");
            return;
        }

        if (edadd <= 0 || edadd > 120) {
            JOptionPane.showMessageDialog(null, "Ingrese una edad válida.");
            return;
        }

        codigo.add(cod);
        estudiantes.add(nombre);
        edad.add(edadd);

        // Crear una lista vacía de cursos para el estudiante
        cursosEstudiantes.add(new ArrayList<String>());

        JOptionPane.showMessageDialog(null, "Estudiante agregado correctamente.");
    }

    // FUNCIÓN 3: MOSTRAR ESTUDIANTES
    public static void mostrarEstudiante() {

        if (estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
            return;
        }

        String lista = "LISTA DE ESTUDIANTES\n\n";

        for (int i = 0; i < estudiantes.size(); i++) {
            lista += (i + 1) + ". " + estudiantes.get(i) + "\nCódigo: " + codigo.get(i) + "\nEdad: " + edad.get(i) + "\nCursos: " + cursosEstudiantes.get(i).size() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    // FUNCIÓN 4: BUSCAR ESTUDIANTE
    public static int buscarEstudiante(String cod) {

        for (int i = 0; i < codigo.size(); i++) {
            if (codigo.get(i).equals(cod)) {
                return i;
            }
        }
        return -1;
    }

    // FUNCIÓN 5: ACTUALIZAR ESTUDIANTE
    public static void actualizarEstudiante() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        int posicion = buscarEstudiante(cod.trim());

        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", estudiantes.get(posicion));

        if (nuevoNombre == null) {
            return;
        }

        nuevoNombre = nuevoNombre.trim();

        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }

        String textoEdad = JOptionPane.showInputDialog("Ingrese la nueva edad:", edad.get(posicion));

        if (textoEdad == null) {
            return;
        }

        int nuevaEdad;

        try {
            nuevaEdad = Integer.parseInt(textoEdad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad incorrecta.");
            return;
        }

        if (nuevaEdad <= 0 || nuevaEdad > 120) {
            JOptionPane.showMessageDialog(null, "Ingrese una edad válida.");
            return;
        }

        estudiantes.set(posicion, nuevoNombre);
        edad.set(posicion, nuevaEdad);

        JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente.");
    }

    // FUNCIÓN 6: ELIMINAR ESTUDIANTE
    public static void eliminarEstudiante() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        int posicion = buscarEstudiante(cod.trim());

        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar a " + estudiantes.get(posicion) + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            codigo.remove(posicion);
            estudiantes.remove(posicion);
            edad.remove(posicion);
            cursosEstudiantes.remove(posicion);

            JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
        }
    }

    // FUNCIÓN 7: MOSTRAR CURSOS
    public static void mostrarCursos() {

        String lista = "CURSOS DISPONIBLES\n\n";

        for (int i = 0; i < cursos.length; i++) {
            lista += (i + 1) + ". " + cursos[i] + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    // FUNCIÓN 8: MATRICULAR CURSOS
    public static void matricularCursos() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        int posicion = buscarEstudiante(cod.trim());

        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        int seleccion;

        do {
            String lista = "MATRÍCULA DE CURSOS\n\n" + "Estudiante: " + estudiantes.get(posicion) + "\n\n";

            for (int i = 0; i < cursos.length; i++) {
                lista += (i + 1) + ". " + cursos[i] + "\n";
            }

            lista += "\n0. Terminar matrícula";

            String respuesta = JOptionPane.showInputDialog(lista);

            if (respuesta == null) {
                return;
            }

            try {
                seleccion = Integer.parseInt(respuesta);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese un número válido.");
                seleccion = -1;
            }

            if (seleccion >= 1 && seleccion <= cursos.length) {

                String cursoElegido = cursos[seleccion - 1];

                if (cursosEstudiantes.get(posicion).contains(cursoElegido)) {
                    JOptionPane.showMessageDialog(null, "El estudiante ya está matriculado en este curso.");
                } else {
                    cursosEstudiantes.get(posicion).add(cursoElegido);
                    JOptionPane.showMessageDialog(null, "Curso matriculado correctamente:\n" + cursoElegido);
                }

            } else if (seleccion != 0 && seleccion != -1) {

                JOptionPane.showMessageDialog(null, "Curso incorrecto.");
            }

        } while (seleccion != 0);
    }

    // FUNCIÓN 9: CONSULTAR CURSOS
    public static void consultarCursos() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        int posicion = buscarEstudiante(cod.trim());

        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        String lista = "ESTUDIANTE: " + estudiantes.get(posicion) + "\nCÓDIGO: " + codigo.get(posicion) + "\n\nCURSOS MATRICULADOS:\n\n";

        if (cursosEstudiantes.get(posicion).isEmpty()) {
            lista += "No tiene cursos matriculados.";
        } else {
            for (int i = 0; i < cursosEstudiantes.get(posicion).size(); i++) {
                lista += (i + 1) + ". " + cursosEstudiantes.get(posicion).get(i) + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    // FUNCIÓN 10: RETIRAR CURSO
    public static void retirarCurso() {

        String cod = JOptionPane.showInputDialog("Ingrese el código del estudiante:");

        if (cod == null) {
            return;
        }

        int posicion = buscarEstudiante(cod.trim());

        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        ArrayList<String> matriculados = cursosEstudiantes.get(posicion);

        if (matriculados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene cursos matriculados.");
            return;
        }

        String lista = "CURSOS MATRICULADOS\n\n";

        for (int i = 0; i < matriculados.size(); i++) {
            lista += (i + 1) + ". " + matriculados.get(i) + "\n";
        }

        lista += "\nIngrese el número del curso a retirar:";

        String respuesta = JOptionPane.showInputDialog(lista);

        if (respuesta == null) {
            return;
        }

        int numero;

        try {
            numero = Integer.parseInt(respuesta);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            return;
        }

        if (numero >= 1 && numero <= matriculados.size()) {
            String curso = matriculados.get(numero - 1);
            matriculados.remove(numero - 1);
            JOptionPane.showMessageDialog(null, "Curso retirado correctamente:\n" + curso);
        } else {
            JOptionPane.showMessageDialog(null, "Número de curso incorrecto.");
        }
    }

    // FUNCIÓN PRINCIPAL
    public static void main(String[] args) {

        do {
            menuPrincipal();
            switch (opcion) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    mostrarEstudiante();
                    break;
                case 3:
                    actualizarEstudiante();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    mostrarCursos();
                    break;
                case 6:
                    matricularCursos();
                    break;
                case 7:
                    consultarCursos();
                    break;
                case 8:
                    retirarCurso();
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }
        } while (opcion != 9);
    }
}

