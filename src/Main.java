import graphics.Shader;
import graphics.Window;
import graphics.buffers.Buffer;
import graphics.buffers.IndexBuffer;
import graphics.buffers.VertexArray;
import maths.mat4;
import maths.vec2;
import maths.vec3;
import maths.vec4;
import utils.BufferUtils;

import java.awt.geom.Point2D;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    public static void main(String[] args) {
        try {
            Window window = new Window("Sparky: Java Edition!", 960, 540);
//            glClearColor(0.2f, 0.3f, 0.8f, 1.0f);

            float[] vertices = new float[] {
                    0, 0, 0,
                    0, 3, 0,
                    8, 3, 0,
                    8, 0, 0
            };

            short[] indices = new short[] {
                    0, 1, 2,
                    2, 3, 0
            };
            float[] colorsA = new float[] {
                    1, 0, 1, 1,
                    1, 0, 1, 1,
                    1, 0, 1, 1,
                    1, 0, 1, 1
            };

            float[] colorsB = new float[] {
                    0.2f, 0.3f, 0.8f, 1,
                    0.2f, 0.3f, 0.8f, 1,
                    0.2f, 0.3f, 0.8f, 1,
                    0.2f, 0.3f, 0.8f, 1
            };

            VertexArray sprite1 = new VertexArray();
            VertexArray sprite2 = new VertexArray();
            IndexBuffer ibo = new IndexBuffer(indices, 6);

            sprite1.addBuffer(new Buffer(vertices, 4 * 3, 3), 0);
            sprite1.addBuffer(new Buffer(colorsA, 4 * 4, 4), 1);

            sprite2.addBuffer(new Buffer(vertices, 4 * 3, 3), 0);
            sprite2.addBuffer(new Buffer(colorsB, 4 * 4, 4), 1);

            mat4 ortho = mat4.orthographic(0.0f, 16.0f, 0.0f, 9.0f, -1.0f, 1.0f);

            Shader shader = new Shader("src/shaders/basic.vert", "src/shaders/basic.frag");
            shader.enable();
            shader.setUniformMat4("pr_matrix", ortho);
            shader.setUniformMat4("ml_matrix", mat4.translation(new vec3(4, 3, 0)));

            shader.setUniform2f("light_pos", new vec2(4.0f, 1.5f));
            shader.setUniform4f("colour", new vec4(0.2f, 0.3f, 0.8f, 1.0f));

            while (!window.closed()) {
                window.clear();

                Point2D.Float mousePosition = window.getMousePosition();
                shader.setUniform2f("light_pos", new vec2(mousePosition.x * 16.0f / 960.0f, 9.0f - mousePosition.y * 9.0f / 540.0f));

                sprite1.bind();
                ibo.bind();
                shader.setUniformMat4("ml_matrix", mat4.translation(new vec3(4, 3, 0)));
                glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_SHORT, 0);
                ibo.unbind();
                sprite1.unbind();

                sprite2.bind();
                ibo.bind();
                shader.setUniformMat4("ml_matrix", mat4.translation(new vec3(0, 0, 0)));
                glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_SHORT, 0);
                ibo.unbind();
                sprite2.unbind();

                window.update();
            }

            window.destroy();
        } finally {
            glfwTerminate();
        }
    }


}
