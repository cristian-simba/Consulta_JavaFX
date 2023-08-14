package com.example.consulta_javafx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
    static final String PASS="root_bas3";
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
        System.out.println("Sirve el Buscar");
    }

    public void actualizarBt(ActionEvent event) {
        System.out.println("Sirve el Actualizar");
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

    }
