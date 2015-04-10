import graphics.Shader;
import graphics.Sprite;
import graphics.Texture;
import graphics.Window;
import graphics.layers.Group;
import graphics.layers.TileLayer;
import maths.mat4;
import maths.vec3;
import maths.vec4;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {
        try {
            Window window = new Window("Sparky: Java Edition!", 960, 540);
//            glClearColor(0.2f, 0.3f, 0.8f, 1.0f);

            mat4 ortho = mat4.orthographic(0.0f, 16.0f, 0.0f, 9.0f, -1.0f, 1.0f);

            Shader shader = new Shader("src/shaders/basic.vert", "src/shaders/basic.frag");
            Shader shader2 = new Shader("src/shaders/basic.vert", "src/shaders/basic.frag");
            shader.enable();
            shader2.enable();

            TileLayer layer = new TileLayer(shader);
            Random rand = new Random();

//            for (float y = -9.0f; y < 9.0f; y += 0.1f) {
//                for (float x = -16.0f; x < 16.0f; x += 0.1f) {
//                    layer.add(new Sprite(x, y, 0.09f, 0.09f, new vec4(rand.nextFloat(), 0, 1, 1)));
//                }
//            }

            Group group = new Group(mat4.translation(new vec3(-15.0f, 5.0f, 0.0f)));
            group.add(new Sprite(0, 0, 6, 3, new vec4(1, 1, 1, 1)));

            Group button = new Group(mat4.translation(new vec3(0.5f, 0.5f, 0.0f)));
            button.add(new Sprite(0, 0, 5.0f, 2.0f, new vec4(1, 0, 1, 1)));
            button.add(new Sprite(0.5f, 0.5f, 3.0f, 1.0f, new vec4(0.2f, 0.3f, 0.8f, 1)));
            group.add(button);

            layer.add(group);

            TileLayer layer2 = new TileLayer(shader2);
            layer2.add(new Sprite(-2, -2, 4, 4, new vec4(1, 0, 1, 1)));

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

                /*Point2D.Float mousePosition = window.getMousePosition();

                shader.enable();
                shader.setUniform2f("light_pos", new vec2(mousePosition.x * 32.0f / 960.0f - 16.0f, 9.0f - mousePosition.y * 18.0f / 540.0f));
//                shader.setUniform2f("light_pos", new vec2(-8, -3));

                shader2.enable();

                layer.render();*/
//                layer2.render();

                glBegin(GL_QUADS);
                glTexCoord2f(0, 0);
                glVertex2f(0, 0);
                glTexCoord2f(0, 1);
                glVertex2f(0, 8);
                glTexCoord2f(1, 1);
                glVertex2f(4, 8);
                glTexCoord2f(1, 0);
                glVertex2f(4, 0);
                glEnd();

                frames++;

                if (System.currentTimeMillis() - lastTime >= 1000) {
                    lastTime += 1000;
                    window.setTitle("Sparky: Java Edition! - " + frames + " FPS");
                    frames = 0;
                }

                window.update();
            }

            shader.dispose();
            shader2.dispose();
            layer.dispose();
            layer2.dispose();
            window.destroy();
        } finally {
            glfwTerminate();
        }
    }

}
