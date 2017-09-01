import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends JFrame implements ActionListener {

    private JButton botonSuma, botonResta, botonDividir, botonMultiplicar, botonSumaElementos, botonPromedio, botonDesviacionEstandar;
    private JTextField campoPrimerValor, campoSegundoValor, campoResultado;
    private JLabel etiquetaPrimerNumero, etiquetaSegundoNumero, etiquetaResultado;

    JMenuBar barraMenu;
    JMenu menuArchivo, menuOperaciones, menuAyuda;
    JMenuItem itemSalir, itemBasicas, itemIntermedias, itemAvanzadas, itemAcercaDe;

    JInternalFrame frameBasicas, frameIntermedias;

    private JDesktopPane desktopPane;

//    public Cliente() {
//        super();
//        configurarVentana();
//        inicializarComponentes();
//    }

    private void configurarVentana() {
        desktopPane = new JDesktopPane();
//        this.setTitle("Cliente");
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

    private void inicializarFramesInternos() {
        frameBasicas = new JInternalFrame("Frame 1", true, true, true, true);
        frameBasicas.getContentPane().setLayout(new FlowLayout());
//        frameBasicas.getContentPane().add(new JLabel("Frame 1  contents..."));
//        frameBasicas.getContentPane().add(new JLabel("Frame 2  contents..."));
        frameBasicas.pack();
        frameBasicas.setVisible(true);

        desktopPane.add(frameBasicas);
    }

    private void inicializarComponentes() {

        campoPrimerValor = new JTextField();
        campoSegundoValor = new JTextField();
        campoResultado = new JTextField();

        etiquetaPrimerNumero = new JLabel("Primer numero");
        etiquetaSegundoNumero = new JLabel("Segundo numero");
        etiquetaResultado = new JLabel("Resultado");

        botonSuma = new JButton();
        botonResta = new JButton();
        botonDividir = new JButton();
        botonMultiplicar = new JButton();
        botonSumaElementos = new JButton();
        botonPromedio = new JButton();
        botonDesviacionEstandar = new JButton();

        campoPrimerValor.setText("");
        campoPrimerValor.setColumns(5);
//        campoPrimerValor.setBounds(50, 50, 1000, 250);

        campoSegundoValor.setText("");
        campoSegundoValor.setBounds(200, 50, 100, 25);

        campoResultado.setText("");
        campoResultado.setBounds(350, 50, 100, 25);

        botonSuma.setText("Suma");
        botonSuma.setBounds(50, 100, 75, 30);
        botonSuma.addActionListener(this);

        botonResta.setText("Resta");
        botonResta.setBounds(135, 100, 75, 30);
        botonResta.addActionListener(this);

        botonDividir.setText("Dividir");
        botonDividir.setBounds(220, 100, 75, 30);
        botonDividir.addActionListener(this);

        botonMultiplicar.setText("Multiplicar");
        botonMultiplicar.setBounds(305, 100, 95, 30);
        botonMultiplicar.addActionListener(this);

        botonSumaElementos.setText("Suma elementos");
        botonSumaElementos.setBounds(50, 150, 140, 30);
        botonSumaElementos.addActionListener(this);

        botonPromedio.setText("Promedio");
        botonPromedio.setBounds(200, 150, 100, 30);
        botonPromedio.addActionListener(this);

        botonDesviacionEstandar.setText("Desviación estándar");
        botonDesviacionEstandar.setBounds(310, 150, 150, 30);
        botonDesviacionEstandar.addActionListener(this);

        etiquetaPrimerNumero.setBounds(50, 20, 100, 25);
        etiquetaSegundoNumero.setBounds(200, 20, 100, 25);
        etiquetaResultado.setBounds(350, 20, 100, 25);


        frameBasicas.getContentPane().add(campoPrimerValor);
//        this.add(campoSegundoValor);
//        this.add(campoResultado);
//
        frameBasicas.getContentPane().add(botonSuma);
//        this.add(botonResta);
//        this.add(botonDividir);
//        this.add(botonMultiplicar);
//        this.add(botonSumaElementos);
//        this.add(botonPromedio);
//        this.add(botonDesviacionEstandar);
//
//        this.add(etiquetaPrimerNumero);
//        this.add(etiquetaSegundoNumero);
//        this.add(etiquetaResultado);
    }

    public Cliente() {

        configurarVentana();

        inicializarMenu();

        inicializarFramesInternos();

        inicializarComponentes();

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
        return getOperacion("suma");
    }

    private String getResta() throws UnknownHostException, IOException {
        return getOperacion("resta");
    }

    private String getDividir() throws UnknownHostException, IOException {
        return getOperacion("dividir");
    }

    private String getMultiplicar() throws UnknownHostException, IOException {
        return getOperacion("multiplicar");
    }

    private String getSumaElementos() throws UnknownHostException, IOException {
        return getOperacionElementos("sumaElementos");
    }

    private String getPromedio() throws UnknownHostException, IOException {
        return getOperacionElementos("promedio");
    }

    private String getDesviacionEstandar() throws UnknownHostException, IOException {
        return getOperacionElementos("desviacionEstandar");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemBasicas) {
            System.out.println("pp");
            frameBasicas.setVisible(false);
//                campoResultado.setText(getSuma());

        } else if (e.getSource() == itemIntermedias) {
            frameBasicas.setVisible(true);
//                campoResultado.setText(getResta());
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
                campoResultado.setText(getSumaElementos());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonPromedio) {
            try {
                campoResultado.setText(getPromedio());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == botonDesviacionEstandar) {
            try {
                campoResultado.setText(getDesviacionEstandar());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
