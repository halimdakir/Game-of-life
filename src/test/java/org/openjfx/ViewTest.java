package org.openjfx;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.transform.Affine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ViewTest {

    @Mock
    private Separator separator;
    @Mock private Button button;
    @Mock private Canvas canvas;
    @Mock private Affine affine;
    @InjectMocks
    private View view;
    private Simulator simulator;


    @BeforeEach
    void setUp(){
        this.canvas = new Canvas(400, 400);
        this.simulator = new Simulator(10, 10);
        this.button = new Button("Next Generation");
        this.separator = new Separator();
        this.affine = new Affine();
    }

    @Test
    void drawOnCanvas() {
    }
}