import graphics.*;
import maths.mat4;
import maths.vec2;
import maths.vec3;
import maths.vec4;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class Main {

    public static void main(String[] args) {
        try {
            Window window = new Window("Sparky: Java Edition!", 960, 540);
//            glClearColor(0.2f, 0.3f, 0.8f, 1.0f);

            mat4 ortho = mat4.orthographic(0.0f, 16.0f, 0.0f, 9.0f, -1.0f, 1.0f);

            Shader shader = new Shader("src/shaders/basic.vert", "src/shaders/basic.frag");
            shader.enable();
            shader.setUniformMat4("pr_matrix", ortho);

            List<Renderable2D> sprites = new ArrayList<>();
            Random rand = new Random();

            for (float y = 0; y < 9.0f; y += 0.05f) {
                for (float x = 0; x < 16.0f; x += 0.05f) {
                    sprites.add(new Sprite(x, y, 0.04f, 0.04f, new vec4(rand.nextFloat(), 0, 1, 1)));
                }
            }

            int size = sprites.size();

            BatchRenderer2D renderer = new BatchRenderer2D();

            shader.setUniform2f("light_pos", new vec2(4.0f, 1.5f));
            shader.setUniform4f("colour", new vec4(0.2f, 0.3f, 0.8f, 1.0f));

            long start = System.currentTimeMillis(), lastTime = System.currentTimeMillis();
            int frames = 0;

            while (!window.closed()) {
                mat4 mat = mat4.translation(new vec3(5, 5, 5));
                mat = mat.multiply(mat4.rotation((System.currentTimeMillis() - start) / 10, new vec3(0, 0, 1)));
                mat = mat.multiply(mat4.translation(new vec3(-5, -5, -5)));
                shader.setUniformMat4("ml_matrix", mat);

                window.clear();

                Point2D.Float mousePosition = window.getMousePosition();
                shader.setUniform2f("light_pos", new vec2(mousePosition.x * 16.0f / 960.0f, 9.0f - mousePosition.y * 9.0f / 540.0f));

                renderer.begin();

                for (int i = 0; i < size; i++) {
                    renderer.submit(sprites.get(i));
                }

                renderer.end();
                renderer.flush();

                frames++;

                if (System.currentTimeMillis() - lastTime >= 1000) {
                    lastTime += 1000;
                    window.setTitle("Sparky: Java Edition! - " + frames + " FPS");
                    frames = 0;
                }

                window.update();
            }

            shader.dispose();
            renderer.dispose();
            window.destroy();
        } finally {
            glfwTerminate();
        }
    }


}
