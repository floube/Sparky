import graphics.Shader;
import graphics.Sprite;
import graphics.Texture;
import graphics.Window;
import graphics.layers.TileLayer;
import maths.mat4;
import maths.vec2;
import maths.vec4;

import java.awt.geom.Point2D;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Main {

    public static void main(String[] args) {
        try {
            Window window = new Window("Sparky: Java Edition!", 960, 540);
//            glClearColor(0.2f, 0.3f, 0.8f, 1.0f);

            Shader shader = new Shader("src/shaders/basic.vert", "src/shaders/basic.frag");

            TileLayer layer = new TileLayer(shader);
            Random rand = new Random();

            for (float y = -9.0f; y < 9.0f; y++) {
                for (float x = -16.0f; x < 16.0f; x++) {
                    layer.add(new Sprite(x, y, 0.9f, 0.9f, new vec4(rand.nextFloat(), 0, 1, 1)));
                }
            }

            glActiveTexture(GL_TEXTURE0);
            Texture texture = new Texture("test.png");
            texture.bind();

            shader.enable();
            shader.setUniform1i("tex", 0);
            shader.setUniformMat4("pr_matrix", mat4.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

            long start = System.currentTimeMillis(), lastTime = System.currentTimeMillis();
            int frames = 0;

            while (!window.closed()) {
                window.clear();

                Point2D.Float mousePosition = window.getMousePosition();
                shader.setUniform2f("light_pos", new vec2(mousePosition.x * 32.0f / 960.0f - 16.0f, 9.0f - mousePosition.y * 18.0f / 540.0f));

                layer.render();

                frames++;

                if (System.currentTimeMillis() - lastTime >= 1000) {
                    lastTime += 1000;
                    window.setTitle("Sparky: Java Edition! - " + frames + " FPS");
                    frames = 0;
                }

                window.update();
            }

            shader.dispose();
            layer.dispose();
            window.destroy();
        } finally {
            glfwTerminate();
        }
    }

}
