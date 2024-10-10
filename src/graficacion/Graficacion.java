/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graficacion;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Ricardo
 */
public class Graficacion {

    ArrayList<Double> graficaX;
    ArrayList<Double> graficaY;
    ArrayList<LocalTime> ErrorAgregarValorX = new ArrayList<>();
    ArrayList<LocalTime> ErrorAgregarValorY = new ArrayList<>();
    ArrayList<LocalTime> ErrorModificarValor = new ArrayList<>();
    public static void main(String[] args) {
        Graficacion posicion = new Graficacion();
        posicion.graficaX = new ArrayList<>();
        posicion.graficaY = new ArrayList<>();
        posicion.Menu();
        
    }

    public void Menu() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Selecciona tus opciones");
            System.out.println("1. Agregar Valores en X y Y");
            System.out.println("2. Modificar Valores");
            System.out.println("3. Mostrar Graficos");
            System.out.println("4. Mostrar Lista de errores");
            System.out.println("5. Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    agregarValor();
                    break;
                case 2:
                    ModificarValores();
                    break;
                case 3:
                    MostrarGraficos();
                    break;
                case 4: 
                    ListaDeErrores();
                    break;
                default:
            }
        } while (opcion != 5);
    }

    public void agregarValor() {
        Scanner teclado = new Scanner(System.in);
        double X, Y;
        LocalTime Hora = LocalTime.now();
        System.out.println("Ingrese el valor de X");
        X = teclado.nextDouble();
        if (X >= -10 && X <= 10) {
        graficaX.add(X);    
        } else {
        ErrorAgregarValorX.add(Hora);
        }
        
        System.out.println("Ingrese el valor de Y");
        Y = teclado.nextDouble();
        if (Y >= -10 && Y <= 10) {
        graficaY.add(Y);
        } else{
        ErrorAgregarValorY.add(Hora);
        }
        
    }

    public void ModificarValores() {
        Scanner teclado = new Scanner(System.in);
        LocalTime Hora = LocalTime.now();

    if (graficaX.isEmpty() || graficaY.isEmpty()) {
        System.out.println("No hay valores disponibles para modificar.");
        ErrorModificarValor.add(Hora);
        return;
    }

    System.out.println("Valores actuales:");
    for (int i = 0; i < graficaX.size(); i++) {
        System.out.println("Índice " + i + " -> X: " + graficaX.get(i) + ", Y: " + graficaY.get(i));
    }

    System.out.println("Ingrese el índice que desea modificar (0 a " + (graficaX.size() - 1) + "):");
    int indice = teclado.nextInt();

    if (indice < 0 || indice >= graficaX.size()) {
        System.out.println("Índice inválido.");
        ErrorModificarValor.add(Hora);
        return;
    }
    
    System.out.println("¿Qué valor desea modificar?");
    System.out.println("1. Solo X");
    System.out.println("2. Solo Y");
    System.out.println("3. Ambos X e Y");
    int opcion = teclado.nextInt();

    switch (opcion) {
        case 1:
            System.out.println("Ingrese el nuevo valor para X:");
            double nuevoX = teclado.nextDouble();
            graficaX.set(indice, nuevoX);
            break;
        case 2:
            System.out.println("Ingrese el nuevo valor para Y:");
            double nuevoY = teclado.nextDouble();
            graficaY.set(indice, nuevoY);
            break;
        case 3:
            System.out.println("Ingrese el nuevo valor para X:");
            nuevoX = teclado.nextDouble();
            graficaX.set(indice, nuevoX);
            System.out.println("Ingrese el nuevo valor para Y:");
            nuevoY = teclado.nextDouble();
            graficaY.set(indice, nuevoY);
            break;
        default:
            System.out.println("Opción no válida.");
            ErrorModificarValor.add(Hora);
            break;
    }

    System.out.println("Valores actualizados correctamente.");
}

    public void MostrarGraficos() {
        double[] x = new double[graficaX.size()];
        double[] y = new double[graficaY.size()];
        for (int i = 0; i < graficaX.size(); i++) {
            x[i] = graficaX.get(i);
            y[i] = graficaY.get(i);
        }
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot("Coordenadas", x,y);
        plot.addLinePlot("Linea", x,y);
        plot.setFixedBounds(0, -10, 10);
        plot.setFixedBounds(1, -10, 10);
        JFrame grafico = new JFrame("Grafico");
        grafico.setContentPane(plot);
        grafico.setSize(800, 400);
        grafico.setVisible(true);
        grafico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void ListaDeErrores() {
    
        if (ErrorAgregarValorX.isEmpty() || ErrorAgregarValorY.isEmpty()|| ErrorModificarValor.isEmpty()) {
        System.out.println("No hay errores registrados.");
        return;
    }
        for (int i= 0; i <ErrorAgregarValorX.size(); i++){
            System.out.println("La hora en que se produjo un error al agregar un valor en X fue: " + (ErrorAgregarValorX.get(i)));
        }
        for (int a= 0; a <ErrorAgregarValorY.size(); a++){
            System.out.println("La hora en que se produjo un error al agregar un valor en Y fue: " + (ErrorAgregarValorY.get(a)));
        }
        for (int b = 0; b <ErrorModificarValor.size(); b++){
            System.out.println("La hora en que se produjo un error al modificar un valor fue: " + ErrorModificarValor.get(b));
        }
    }
}

