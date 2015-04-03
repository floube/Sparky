import graphics.Shader;
import graphics.Window;
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
                    8, 0, 0,
                    0, 3, 0,
                    0, 3, 0,
                    8, 3, 0,
                    8, 0, 0
            };

            int vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, BufferUtils.toFloatBuffer(vertices), GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);

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
                glDrawArrays(GL_TRIANGLES, 0, 6);

                window.update();
            }

            window.destroy();
        } finally {
            glfwTerminate();
        }
    }



}
