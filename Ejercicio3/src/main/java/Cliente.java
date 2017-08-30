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

    private JDesktopPane desktopPane = new JDesktopPane();

//    public Cliente() {
//        super();
//        configurarVentana();
//        inicializarComponentes();
//    }

    private void configurarVentana() {
        this.setTitle("Cliente");
        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        campoPrimerValor.setBounds(50, 50, 100, 25);

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


        this.add(campoPrimerValor);
        this.add(campoSegundoValor);
        this.add(campoResultado);

        this.add(botonSuma);
        this.add(botonResta);
        this.add(botonDividir);
        this.add(botonMultiplicar);
        this.add(botonSumaElementos);
        this.add(botonPromedio);
        this.add(botonDesviacionEstandar);

        this.add(etiquetaPrimerNumero);
        this.add(etiquetaSegundoNumero);
        this.add(etiquetaResultado);
    }

    public Cliente() {
        JInternalFrame frame1 = new JInternalFrame("Frame 1", true, true, true,
                true);

        JInternalFrame frame2 = new JInternalFrame("Frame 2", true, true, true,
                true);

        frame1.getContentPane().add(new JLabel("Frame 1  contents..."));
        frame1.pack();
        frame1.setVisible(true);

        frame2.getContentPane().add(new JLabel("Frame 2  contents..."));
        frame2.pack();
        frame2.setVisible(true);

        int x2 = frame1.getX() + frame1.getWidth() + 10;
        int y2 = frame1.getY();
        frame2.setLocation(x2, y2);

        JMenuBar barra=new JMenuBar();
        JMenu archivo=new JMenu("Archivo");
        JMenuItem nuevo=new JMenuItem("Nuevo");

        desktopPane.add(frame1);
        desktopPane.add(frame2);

        archivo.add(nuevo);
        barra.add(archivo);
        setJMenuBar(barra);

        this.add(desktopPane, BorderLayout.CENTER);

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
        if (e.getSource() == botonSuma) {
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
