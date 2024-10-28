package org.hugo.practicahibernatecoches.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hugo.practicahibernatecoches.dao.CocheDAOImpl;
import org.hugo.practicahibernatecoches.model.Coche;
import org.hugo.practicahibernatecoches.util.AlertUtil;
import org.hugo.practicahibernatecoches.util.HibernateUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MenuCtrll implements Initializable {

    @FXML
    private TextField inMatricula, inMarca, inModelo;

    @FXML
    private ChoiceBox<String> inTipo;

    @FXML
    private TableView<Coche> tablaCoches;

    @FXML
    private TableColumn<Coche, String> colMatricula, colMarca, colModelo, colTipo;

    private final ObservableList<Coche> coches = FXCollections.observableArrayList();
    private final ObservableList<String> tipos = FXCollections.observableArrayList();

    private Coche cocheCargado = null;

    private CocheDAOImpl cocheDAO = new CocheDAOImpl();

    private SessionFactory factory = HibernateUtil.getSessionFactory();
    private Session session = HibernateUtil.getSession();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        coches.addAll(cocheDAO.listarCoches(session));
        tablaCoches.setItems(coches);

        String[] listaTipos = new String[]{"Familiar", "Deportivo", "Todo Terreno"};

        tipos.addAll(new ArrayList<>(List.of(listaTipos)));
        inTipo.setItems(tipos);

    }


    public void onLimpiar(ActionEvent actionEvent) {
        inMatricula.setText("");
        inMarca.setText("");
        inModelo.setText("");
        inTipo.setValue("");
        cocheCargado = null;
        tablaCoches.getSelectionModel().clearSelection();
    }

    public void onGuardar(ActionEvent actionEvent) {
        Coche c = new Coche();

        c.setMarca(inMarca.getText());
        c.setMatricula(inMatricula.getText());
        c.setModelo(inModelo.getText());
        c.setTipo(inTipo.getValue());

        if (!cocheDAO.guardarCoche(c, session)) {
            AlertUtil.mostrarInfo("Esa matricula ya está asignada");
            return;
        }

        cocheCargado = c;

        coches.add(c);

    }

    public void onActualizar(ActionEvent actionEvent) {
        if (cocheCargado == null) {
            return;
        }

        cocheCargado.setMarca(inMarca.getText());
        cocheCargado.setMatricula(inMatricula.getText());
        cocheCargado.setModelo(inModelo.getText());
        cocheCargado.setTipo(inTipo.getValue());

        if (!cocheDAO.actualizarCoche(cocheCargado, session)) {
            AlertUtil.mostrarInfo("Esa matricula ya está asignada");

        }
    }

    public void onEliminar(ActionEvent actionEvent) {
        if (cocheCargado == null){
            return;
        }

        cocheDAO.eliminarCoche(cocheCargado, session);

        coches.remove(cocheCargado);

        cocheCargado = null;

        onLimpiar(actionEvent);

    }

    public void onClic(MouseEvent mouseEvent) {
        Coche c = tablaCoches.getSelectionModel().getSelectedItem();

        if (c == null){
            return;
        }

        inMatricula.setText(c.getMatricula());
        inMarca.setText(c.getMarca());
        inModelo.setText(c.getModelo());
        inTipo.setValue(c.getTipo());

        cocheCargado = c;

        System.out.println(c.getId());
    }

    public void close() {
        System.out.println("Saliendo de la aplicación");

        session.close();
        factory.close();

    }
}