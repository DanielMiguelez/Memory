/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorydani;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button[][] buttons = new Button[4][4];
    private String[] symbols = {"A", "B", "C", "D", "E", "F", "G", "H"};
    
     private void initializeGame() {
    // Crear una instancia de la baraja
    Deck deck = new Deck();
    // Inicializar la baraja con el conjunto de símbolos
    deck.initializeDeck(symbols);
    // Barajar la baraja
    deck.shuffle();
    
    int index = 0;
    // Asignar los nombres de las cartas a los botones en la cuadrícula
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            buttons[i][j].setText(deck.getCards().get(index).getName());
            index++;
        }
    }
}
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeGame();
    }    
    
}
