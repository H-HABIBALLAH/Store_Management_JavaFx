package StoreManagement.IHM.Produit;

import StoreManagement.DAO.Catégorie.Categorie;
import StoreManagement.DAO.Produit.Produit;
import StoreManagement.DAO.Produit.ProduitDaoImpl;
import StoreManagement.IHM.Catégorie.CategorieWindow;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormProduitWindow {

    private VBox root=new VBox(10);
    private Scene scene=new Scene(root);
    Stage window=new Stage();
    private Label titleLabel=new Label("Ajouter un produit");
    private Label designation=new Label("Designation :");
    private TextField designationInput= new TextField();
    private Label prix=new Label("Prix :");
    private TextField prixInput= new TextField();
    private Label quantite=new Label("Quantité :");
    private TextField quantiteInput= new TextField();
    private Label dateSaisie=new Label("Date saisie :");
    private DatePicker dateSaisieInput= new DatePicker();
    private Button ajouter=new Button("Ajouter");
    private Button annuler=new Button("Annuler");
    private Button afficherCategorieButton=new Button("Catégorie");
    HBox buttonLine=new HBox(20);
    ProduitDaoImpl pdao=new ProduitDaoImpl();
    Produit produit=null;
    Categorie categorie=new Categorie(0,"");

    private void addStylesToNodes(){
        titleLabel.getStyleClass().add("sceneTitle");
        designation.getStyleClass().add("formLabel");
        quantite.getStyleClass().add("formLabel");
        dateSaisie.getStyleClass().add("formLabel");
        prix.getStyleClass().add("formLabel");
        scene.getStylesheets().add("/StoreManagement/style.css");
        ajouter.getStyleClass().add("btn");
        annuler.getStyleClass().add("btn");
        titleLabel.setMinWidth(window.getWidth());
    }

    private void addNodesToWindow(){
        buttonLine.getChildren().addAll(ajouter,annuler);
        root.getChildren().addAll(titleLabel,designation,designationInput,prix,prixInput,quantite,quantiteInput,dateSaisie,dateSaisieInput,afficherCategorieButton,buttonLine);
    }

    private void addEvents(){
        window.setOnCloseRequest(e->{
            e.consume();
        });
        afficherCategorieButton.setOnAction(e->{
            new CategorieWindow(categorie);
        });
        ajouter.setOnAction(e ->{
            produit=new Produit(0,designationInput.getText(),Integer.parseInt(quantiteInput.getText()),Double.parseDouble(prixInput.getText()),dateSaisieInput.getValue(),categorie);
            new FormProduitHandler(produit,this);
        });
        annuler.setOnAction(e -> {
            window.close();
        });
    }

    private void initiWindow(){
        window.setWidth(700);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    public FormProduitWindow(){
        initiWindow();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }

}
