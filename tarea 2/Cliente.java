import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends JFrame implements ActionListener {

    private JLabel etiquetaFecha;
    private JLabel etiquetaHora;
    private JButton botonHora, botonFecha;

    public Cliente() {
        super();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle("Cliente");
        this.setSize(310, 210);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {

        etiquetaFecha = new JLabel();
        etiquetaHora = new JLabel();

        botonHora = new JButton();
        botonFecha = new JButton();

        etiquetaFecha.setText("");
        etiquetaFecha.setBounds(50, 50, 100, 25);

        etiquetaHora.setText("");
        etiquetaHora.setBounds(200, 50, 100, 25);

        botonHora.setText("Mostrar hora");
        botonHora.setBounds(50, 100, 200, 30);
        botonHora.addActionListener(this);

        botonFecha.setText("Mostrar fecha");
        botonFecha.setBounds(50, 150, 200, 30);
        botonFecha.addActionListener(this);


        this.add(etiquetaFecha);
        this.add(etiquetaHora);
        this.add(botonHora);
        this.add(botonFecha);
    }


    public static void main(String args[]) throws UnknownHostException, IOException {

        Cliente ventanaCliente = new Cliente();
        ventanaCliente.setVisible(true);


    }

    private String getHora() throws UnknownHostException, IOException {

        Socket socket = new Socket("127.0.0.1", 5000);
        InputStream inputStream = socket.getInputStream();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("getHora");

        BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(inputStream));
        String hora = bufferEntrada.readLine();

        bufferEntrada.close();
        socket.close();
        return hora;
    }

    private String getFecha() throws IOException {

        Socket socket = new Socket("127.0.0.1", 5000);
        InputStream inputStream = socket.getInputStream();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("getFecha");

        BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(inputStream));
        String fecha = bufferEntrada.readLine();

        bufferEntrada.close();
        socket.close();
        return fecha;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonHora) {
            try {
                etiquetaHora.setText(getHora());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        else if (e.getSource() == botonFecha) {
            try {
                etiquetaFecha.setText(getFecha());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
