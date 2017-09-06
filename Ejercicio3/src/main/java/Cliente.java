import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cliente extends JFrame implements ActionListener {

    private JButton botonSuma, botonResta, botonDividir, botonMultiplicar, botonSumaElementos, botonPromedio, botonDesviacionEstandar;
    private JTextField campoPrimerValor, campoSegundoValor, campoResultado, campoListaNumeros, campoResultado2;
    private JLabel etiquetaPrimerNumero, etiquetaSegundoNumero, etiquetaResultado,etiquetaResultado2, etiquetaNumeros;
    private JPanel panelOperandosBasicas, panelBotonesBasicas, panelOperandosIntermedias, panelBotonesIntermedias;

    private JMenuBar barraMenu;
    private JMenu menuArchivo, menuOperaciones, menuAyuda;
    private JMenuItem itemSalir, itemBasicas, itemIntermedias, itemAvanzadas, itemAcercaDe;

    private JInternalFrame frameBasicas, frameIntermedias;
    private Operaciones operaciones;

    private JDesktopPane desktopPane;

    private void inicializarObjetoOperaciones() {
        try {
            operaciones = (Operaciones) Naming.lookup("operaciones");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void configurarVentana() {
        desktopPane = new JDesktopPane();
        this.setTitle("Cliente");
//        this.setSize(500, 250);
//        this.setLocationRelativeTo(null);
//        this.setLayout(null);
//        this.setResizable(false);
        this.add(desktopPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void inicializarMenu() {
        barraMenu = new JMenuBar();

        menuArchivo = new JMenu("Archivo");
        itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(this);
        menuArchivo.add(itemSalir);
        barraMenu.add(menuArchivo);

        menuOperaciones = new JMenu("Operaciones");
        itemBasicas = new JMenuItem("Básicas");
        itemBasicas.addActionListener(this);
        itemIntermedias = new JMenuItem("Intermedias");
        itemIntermedias.addActionListener(this);
        itemAvanzadas = new JMenuItem("Avanzadas");
        itemAvanzadas.addActionListener(this);
        menuOperaciones.add(itemBasicas);
        menuOperaciones.add(itemIntermedias);
        menuOperaciones.add(itemAvanzadas);
        barraMenu.add(menuOperaciones);

        menuAyuda = new JMenu("Ayuda");
        itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.addActionListener(this);
        menuAyuda.add(itemAcercaDe);
        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

    }

    private void inicializarPaneles() {
        panelOperandosBasicas = new JPanel(new FlowLayout());
        panelBotonesBasicas = new JPanel(new FlowLayout());

        panelOperandosIntermedias = new JPanel(new FlowLayout());
        panelBotonesIntermedias = new JPanel(new FlowLayout());
    }

    private void inicializarFramesInternos() {

        frameBasicas = new JInternalFrame("Operaciones básicas", true, true, true, true);
        frameBasicas.getContentPane().setLayout(new BoxLayout(frameBasicas.getContentPane(), BoxLayout.Y_AXIS));
        frameBasicas.getContentPane().add(panelOperandosBasicas);
        frameBasicas.getContentPane().add(panelBotonesBasicas);
        frameBasicas.pack();
        frameBasicas.setVisible(false);

        frameIntermedias = new JInternalFrame("Operaciones intermedias", true, true, true, true);
        frameIntermedias.getContentPane().setLayout(new BoxLayout(frameIntermedias.getContentPane(), BoxLayout.Y_AXIS));
        frameIntermedias.getContentPane().add(panelOperandosIntermedias);
        frameIntermedias.getContentPane().add(panelBotonesIntermedias);
        frameIntermedias.pack();
        frameIntermedias.setVisible(false);

        desktopPane.add(frameBasicas);
        desktopPane.add(frameIntermedias);
    }

    private void inicializarComponentes() {

        campoPrimerValor = new JTextField();
        campoSegundoValor = new JTextField();
        campoResultado = new JTextField();

        campoListaNumeros = new JTextField();
        campoResultado2 = new JTextField();

        etiquetaPrimerNumero = new JLabel("Primer numero: ");
        etiquetaSegundoNumero = new JLabel("Segundo numero: ");
        etiquetaResultado = new JLabel("Resultado: ");

        etiquetaNumeros = new JLabel("Números (separados por comas): ");
        etiquetaResultado2 = new JLabel("Resultado: ");

        botonSuma = new JButton();
        botonResta = new JButton();
        botonDividir = new JButton();
        botonMultiplicar = new JButton();
        botonSumaElementos = new JButton();
        botonPromedio = new JButton();
        botonDesviacionEstandar = new JButton();

        campoPrimerValor.setText("");
        campoPrimerValor.setColumns(5);

        campoSegundoValor.setText("");
        campoSegundoValor.setColumns(5);

        campoResultado.setText("");
        campoResultado.setColumns(5);

        campoListaNumeros.setText("");
        campoListaNumeros.setColumns(5);

        campoResultado2.setText("");
        campoResultado2.setColumns(5);

        botonSuma.setText("Suma");
        botonSuma.addActionListener(this);

        botonResta.setText("Resta");
        botonResta.addActionListener(this);

        botonDividir.setText("Dividir");
        botonDividir.addActionListener(this);

        botonMultiplicar.setText("Multiplicar");
        botonMultiplicar.addActionListener(this);

        botonSumaElementos.setText("Suma elementos");
        botonSumaElementos.addActionListener(this);

        botonPromedio.setText("Promedio");
        botonPromedio.addActionListener(this);

        botonDesviacionEstandar.setText("Desviación estándar");
        botonDesviacionEstandar.addActionListener(this);

        panelOperandosBasicas.add(etiquetaPrimerNumero);
        panelOperandosBasicas.add(campoPrimerValor);
        panelOperandosBasicas.add(etiquetaSegundoNumero);
        panelOperandosBasicas.add(campoSegundoValor);
        panelOperandosBasicas.add(etiquetaResultado);
        panelOperandosBasicas.add(campoResultado);

        panelBotonesBasicas.add(botonSuma);
        panelBotonesBasicas.add(botonResta);
        panelBotonesBasicas.add(botonDividir);
        panelBotonesBasicas.add(botonMultiplicar);


        panelOperandosIntermedias.add(etiquetaNumeros);
        panelOperandosIntermedias.add(campoListaNumeros);
        panelOperandosIntermedias.add(etiquetaResultado2);
        panelOperandosIntermedias.add(campoResultado2);

        panelBotonesIntermedias.add(botonSumaElementos);
        panelBotonesIntermedias.add(botonPromedio);
        panelBotonesIntermedias.add(botonDesviacionEstandar);


    }

    public Cliente() {

        super();

        inicializarObjetoOperaciones();

        configurarVentana();

        inicializarMenu();

        inicializarPaneles();

        inicializarComponentes();

        inicializarFramesInternos();

        this.setMinimumSize(new Dimension(300, 300));

    }

    public static void main(String args[]) throws UnknownHostException, IOException {

//        Cliente ventanaCliente = new Cliente();
//        ventanaCliente.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            Cliente frame = new Cliente();
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(frame.MAXIMIZED_BOTH);
        });
    }

    public String getOperacion(String operacion) throws UnknownHostException, IOException {

        Socket socket = new Socket("127.0.0.1", 5000);
        InputStream inputStream = socket.getInputStream();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(operacion);

        printWriter.println(campoPrimerValor.getText());
        printWriter.println(campoSegundoValor.getText());

        BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(inputStream));
        String resultado = bufferEntrada.readLine();

        bufferEntrada.close();
        socket.close();
        return resultado;

    }

    public String getOperacionElementos(String operacion) throws UnknownHostException, IOException {

        Socket socket = new Socket("127.0.0.1", 5000);
        InputStream inputStream = socket.getInputStream();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(operacion);

        printWriter.println(campoPrimerValor.getText());

        BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(inputStream));
        String resultado = bufferEntrada.readLine();

        bufferEntrada.close();
        socket.close();
        return resultado;

    }

    private String getSuma() throws UnknownHostException, IOException {
        return Double.toString(operaciones.suma(Double.parseDouble(campoPrimerValor.getText()), Double.parseDouble(campoSegundoValor.getText())));
    }

    private String getResta() throws UnknownHostException, IOException {
        return Double.toString(operaciones.resta(Double.parseDouble(campoPrimerValor.getText()), Double.parseDouble(campoSegundoValor.getText())));
    }

    private String getDividir() throws UnknownHostException, IOException {
        return Double.toString(operaciones.dividir(Double.parseDouble(campoPrimerValor.getText()), Double.parseDouble(campoSegundoValor.getText())));
    }

    private String getMultiplicar() throws UnknownHostException, IOException {
        return Double.toString(operaciones.multiplicar(Double.parseDouble(campoPrimerValor.getText()), Double.parseDouble(campoSegundoValor.getText())));
    }

    private String getSumaElementos() throws UnknownHostException, IOException {
        return Double.toString(operaciones.suma(generarArrayList(campoListaNumeros.getText())));
    }

    private String getPromedio() throws UnknownHostException, IOException {
        return Double.toString(operaciones.promedio(generarArrayList(campoListaNumeros.getText())));
    }

    private String getDesviacionEstandar() throws UnknownHostException, IOException {
        return Double.toString(operaciones.desviacionEstandar(generarArrayList(campoListaNumeros.getText())));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemBasicas) {
            frameBasicas.setVisible(true);
            frameIntermedias.setVisible(false);

        } else if (e.getSource() == itemIntermedias) {
            frameBasicas.setVisible(false);
            frameIntermedias.setVisible(true);
        } else if (e.getSource() == botonSuma) {
            try {
                campoResultado.setText(getSuma());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonResta) {
            try {
                campoResultado.setText(getResta());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonDividir) {
            try {
                campoResultado.setText(getDividir());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonMultiplicar) {
            try {
                campoResultado.setText(getMultiplicar());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonSumaElementos) {
            try {
                campoResultado2.setText(getSumaElementos());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonPromedio) {
            try {
                campoResultado2.setText(getPromedio());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonDesviacionEstandar) {
            try {
                campoResultado2.setText(getDesviacionEstandar());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static Double[] generarArrayList(String cadena) {
        List<Double> elementos = new ArrayList<Double>();

        StringTokenizer s = new StringTokenizer(cadena, ",");

        while (s.hasMoreTokens()) {
            elementos.add(Double.parseDouble(s.nextToken()));
        }


        return elementos.toArray(new Double[elementos.size()]);
    }
}
