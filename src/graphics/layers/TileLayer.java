package graphics.layers;

import graphics.BatchRenderer2D;
import graphics.Renderer2D;
import graphics.Shader;
import maths.mat4;

public class TileLayer extends Layer {

    public TileLayer(Shader shader) {
        super(new BatchRenderer2D(), shader, mat4.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

}
