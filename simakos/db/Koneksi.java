package koneksi;  // <-- jika kamu pakai package, boleh dihapus kalau tidak pakai

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Koneksi {

    private static Connection conn;
    

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/simakos";
                String user = "root";
                String pass = "";

                conn = DriverManager.getConnection(url, user, pass);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}