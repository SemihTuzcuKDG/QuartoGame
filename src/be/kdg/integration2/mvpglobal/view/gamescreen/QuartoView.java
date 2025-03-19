package be.kdg.integration2.mvpglobal.view.gamescreen;

import be.kdg.integration2.mvpglobal.model.Attribute;
import be.kdg.integration2.mvpglobal.model.Piece;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class QuartoView extends BorderPane {
    private Label messageLabel;
    private GridPane boardGrid;
    private GridPane availablePiecesGrid;
    private StackPane selectedPiecePane;
    private Button resetButton;
    private QuartoPresenter presenter;

    public QuartoView() {
        initializeNodes();
        layoutNodes();
        attachHandlers();
    }

    private void initializeNodes() {
        messageLabel = new Label("Welcome to Quarto!");
        boardGrid = new GridPane();
        availablePiecesGrid = new GridPane();
        selectedPiecePane = new StackPane();
        resetButton = new Button("Reset Game");
    }

    private void layoutNodes() {
        boardGrid.setAlignment(Pos.CENTER);
        boardGrid.setHgap(10);
        boardGrid.setVgap(10);

        availablePiecesGrid.setAlignment(Pos.CENTER);
        availablePiecesGrid.setHgap(5);
        availablePiecesGrid.setVgap(5);

        selectedPiecePane.setAlignment(Pos.CENTER);
        selectedPiecePane.setMinSize(100, 100);
        selectedPiecePane.setStyle("-fx-border-color: black; -fx-padding: 10px;");

        HBox bottomBox = new HBox(10, messageLabel, resetButton);
        bottomBox.setAlignment(Pos.CENTER);

        VBox leftPanel = new VBox(20, new Label("Available Pieces:"), availablePiecesGrid);
        leftPanel.setAlignment(Pos.CENTER);

        VBox rightPanel = new VBox(20, new Label("Selected Piece:"), selectedPiecePane);
        rightPanel.setAlignment(Pos.CENTER);

        this.setLeft(leftPanel);
        this.setCenter(boardGrid);
        this.setRight(rightPanel);
        this.setBottom(bottomBox);
    }

    private void attachHandlers() {
        resetButton.setOnAction(e -> presenter.resetGame());
    }

    public void setPresenter(QuartoPresenter presenter) {
        this.presenter = presenter;
    }

    public void displayBoard(Piece[][] boardState) {
        boardGrid.getChildren().clear();
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                Button cell = new Button();
                cell.setMinSize(60, 60);

                Piece piece = boardState[row][col];
                if (piece != null) {
                    cell.setGraphic(getPieceImage(piece));
                }

                int currentRow = row;
                int currentCol = col;
                cell.setOnAction(e -> presenter.handleMove(currentRow, currentCol));
                boardGrid.add(cell, col, row);
            }
        }
    }

    public void updatePieceSelection(List<Piece> availablePieces) {
        availablePiecesGrid.getChildren().clear();
        int row = 0, col = 0;
        for (Piece piece : availablePieces) {
            Button pieceButton = new Button();
            pieceButton.setGraphic(getPieceImage(piece));
            pieceButton.setOnAction(e -> presenter.handlePieceSelection(piece));
            availablePiecesGrid.add(pieceButton, col, row);

            col++;
            if (col >= 4) {
                col = 0;
                row++;
            }
        }
    }

    public void displaySelectedPiece(Piece piece) {
        selectedPiecePane.getChildren().clear();
        if (piece != null) {
            selectedPiecePane.getChildren().add(getPieceImage(piece));
        }
    }

    public void displayMessage(String message) {
        messageLabel.setText(message);
    }


    private ImageView getPieceImage(Piece piece) {
        String fileName = getImageFileName(piece);

        String imagePath = "file:C:/Users/semih/Downloads/QuartoGame/src/be/kdg/integration2/mvpglobal/resource/" + fileName;
        Image baseImage = new Image(imagePath);

        if (baseImage.isError()) {
            System.out.println("Failed to load: " + imagePath);
        }

        ImageView imageView = new ImageView(baseImage);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        if (piece.hasAttribute(Attribute.HOLLOW)) {
            String holePath = "file:C:/Users/semih/Downloads/QuartoGame/src/be/kdg/integration2/mvpglobal/resources/" + fileName;
            Image holeImage = new Image(imagePath);

            if (holeImage.isError()) {
                System.out.println("Failed to load hole overlay: " + holePath);
            }

            ImageView holeOverlay = new ImageView(holeImage);
            holeOverlay.setFitWidth(50);
            holeOverlay.setFitHeight(50);

            StackPane stackPane = new StackPane(imageView, holeOverlay);
            return new ImageView(stackPane.snapshot(null, null));
        }
        return imageView;
        //not done
    }

    private String getImageFileName(Piece piece) {
        StringBuilder fileName = new StringBuilder();

        fileName.append(piece.hasAttribute(Attribute.TALL) ? "big_" : "sml_");
        fileName.append(piece.hasAttribute(Attribute.BROWN) ? "bwn_" : "ylw_");
        fileName.append(piece.hasAttribute(Attribute.SQUARE) ? "sqr_" : "cir_");
        fileName.append(piece.hasAttribute(Attribute.SOLID) ? "_solid.png" : "_hollow.png");

        return fileName.toString();
    }
    public BorderPane getViewLayout() {
        return this;//not finished
    }
}

