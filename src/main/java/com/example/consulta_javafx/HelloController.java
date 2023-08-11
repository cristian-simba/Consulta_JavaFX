package com.example.consulta_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField codigoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField carreraField;
    @FXML
    private TextField notaField;

    @FXML
    private void registrarBt(ActionEvent event) {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String carrera = carreraField.getText();
        String nota = notaField.getText();

        System.out.println("se guardo el código: "+codigo);
        System.out.println("se guardo el nombre: "+nombre);
        System.out.println("se guardo la carrera: "+carrera);
        System.out.println("se guardo la nota: "+nota);
    }

    public void buscarBt(ActionEvent event) {
        System.out.println("Sirve el Buscar");
    }

    public void actualizarBt(ActionEvent event) {
        System.out.println("Sirve el Actualizar");
    }

    public void borrarBt(ActionEvent event) {
        System.out.println("Sirve el Borrar");
    }
}