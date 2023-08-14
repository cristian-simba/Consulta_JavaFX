package com.example.consulta_javafx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class HelloController {
    @FXML
    private TextField codigoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField carreraField;
    @FXML
    private TextField notaField;
    static final String DB_URL="jdbc:mysql://localhost/Crud";
    static final String USER="root";
    static final String PASS="root";
    static final String QUERY="Select * From notas ;";

    @FXML
    private void registrarBt(ActionEvent event) {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String carrera = carreraField.getText();
        String nota = notaField.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO notas (Codigo, Nombre, Carrera,NotaF) VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, Integer.parseInt(codigo));
            pstmt.setString(2, nombre);
            pstmt.setString(3, carrera);
            pstmt.setInt(4, Integer.parseInt(nota));

            pstmt.executeUpdate();

            showAlert("Registro guardado correctamente.", Alert.AlertType.INFORMATION);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al guardar el registro.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void buscarBt(ActionEvent event) {
        String codigo = codigoField.getText();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM notas WHERE codigo = ?   ");

            pstmt.setInt(1, Integer.parseInt(codigo));

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                int codigoEncontrado = resultSet.getInt("Codigo");
                String nombre = resultSet.getString("Nombre");
                String carrera = resultSet.getString("Carrera");
                int nota = resultSet.getInt("NotaF");

                String mensaje = "Código: " + codigoEncontrado + "\nNombre: " + nombre + "\nCarrera: " + carrera + "\nNota: " + nota;
                showAlert(mensaje, Alert.AlertType.INFORMATION);
            } else {
                showAlert("No se encontró ningún registro con el código proporcionado.", Alert.AlertType.WARNING);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("No se encontro ningun registro.", Alert.AlertType.ERROR);
        }
    }

    public void actualizarBt(ActionEvent event) {
        String codigo = codigoField.getText();
        String nuevoNombre = nombreField.getText();
        String nuevaCarrera = carreraField.getText();
        int nuevaNota = Integer.parseInt(notaField.getText());

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "UPDATE notas SET Nombre = ?, Carrera = ?, NotaF = ? WHERE Codigo = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevaCarrera);
            pstmt.setInt(3, nuevaNota);
            pstmt.setInt(4, Integer.parseInt(codigo));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Datos actualizados correctamente.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No se encontró ningún registro con el código proporcionado.", Alert.AlertType.WARNING);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al actualizar los datos.", Alert.AlertType.ERROR);
        }
    }

    public void borrarBt(ActionEvent event) {
        String codigo = codigoField.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM notas WHERE codigo = ?");

            pstmt.setInt(1, Integer.parseInt(codigo));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro eliminado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No se encontró ningún registro con el código proporcionado.", Alert.AlertType.WARNING);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al eliminar el registro.", Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void limpiarCampos() {
        codigoField.clear();
        nombreField.clear();
        carreraField.clear();
        notaField.clear();
    }
}
