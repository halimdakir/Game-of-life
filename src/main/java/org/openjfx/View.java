package org.openjfx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;

public class View extends VBox {
    private int counter = 1;
    private final Label label;
    private final Separator separator;
    private final Button button;
    private final Canvas canvas;
    private final Affine affine;
    private final Simulator simulator;


    public View() {
        this.label= new Label(" Generation : "+ counter);

        this.canvas = new Canvas(400, 400);
        this.simulator = new Simulator(10, 10);
        this.button = new Button("Next Generation");
        this.separator = new Separator();
        this.affine = new Affine();

        buttonStyle();
        labelStyle();
        btnOnAction();
        addAllChildren();
        setUpAffine();
        drawCells();
    }

    private void addAllChildren(){
        this.getChildren().addAll(this.label, this.canvas, this.separator, this.button);
    }

    private void setUpAffine(){
        this.affine.appendScale(400 / 10f, 400 / 10f);
    }

    private void btnOnAction(){
        this.button.setOnAction(actionEvent -> {
            simulator.nextGenerationStep();
            drawOnCanvas();
            counter++;
            this.label.setText(" Generation :"+ counter);
        });
    }

    private void drawCells(){
        FillCells fillCells = new FillCells();
        fillCells.insertAliveCells(this.simulator);
    }

    public void drawOnCanvas() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        setUpCanvas(graphicsContext, this.affine, 0, 0, 450, 450);
        drawCellsStyle(graphicsContext, this.simulator.getWidth(), this.simulator.getHeight());
        drawColRowLines(0.05, graphicsContext, this.simulator.getWidth(), this.simulator.getHeight(), Color.DARKBLUE);

    }

    private void setUpCanvas(GraphicsContext graphicsContext, Affine affine, double v, double v1, double v2, double v3){
        graphicsContext.setTransform(affine);
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(v, v1, v2, v3);
    }

    private void drawCellsStyle(GraphicsContext graphicsContext, int width, int height){
        graphicsContext.setFill(Color.BLACK);
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (this.simulator.getCellState(row, col) == 1) {
                    graphicsContext.fillRect(row, col, 1, 1);
                }
            }
        }
    }

    private void drawColRowLines(double lineWidth, GraphicsContext graphicsContext, int width, int height, Color color){
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(lineWidth);
        drawRowsLine(graphicsContext, width);
        drawColumnsLine(graphicsContext, height);
    }

    private void drawRowsLine(GraphicsContext graphicsContext, int width){
        for (int row = 0; row <= width; row++) {
            graphicsContext.strokeLine(row, 0, row, 10);
        }
    }

    private void drawColumnsLine(GraphicsContext graphicsContext, int height){
        for (int col = 0; col <= height; col++) {
            graphicsContext.strokeLine(0, col, 10, col);
        }
    }

    private void labelStyle(){
        this.label.setStyle("-fx-border-width: 2px; -fx-text-fill: #000000; -fx-font-size: 15px; -fx-pref-width: 400;");
    }

    private void buttonStyle(){
        this.button.setStyle("-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-text-fill: #ffffff;" +
                "-fx-font-size: 15px; -fx-background-color: #066495; -fx-pref-width: 400;" +
                "-fx-cursor: hand; -fx-border-radius: 4px;");
    }
}
