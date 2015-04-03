import graphics.Window;
import maths.mat4;
import maths.vec3;
import maths.vec4;

import java.awt.geom.Point2D;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    public static void main(String[] args) {
        try {
            Window window = new Window("Sparky: Java Edition!", 960, 540);
            glClearColor(0.2f, 0.3f, 0.8f, 1.0f);

            int vao = glGenVertexArrays();
            glBindVertexArray(vao);

            vec4 a = new vec4(0.2f, 0.3f, 0.8f, 1.0f);
            vec4 b = new vec4(0.5f, 0.2f, 0.1f, 1.0f);

            vec4 c = a.multiply(b);

            mat4 position = mat4.translation(new vec3(2, 3, 4));
            position.multiply(mat4.identity());

            position.elements[12] = 2.0f;

            vec4 c0 = position.getColumn(3);
            System.out.println(position.elements[12]);
            System.out.println(c0.x);

            while (!window.closed()) {
                window.clear();

//                System.out.println(c);

                glBegin(GL_QUADS);
                glVertex2f(-0.5f, -0.5f);
                glVertex2f(-0.5f, 0.5f);
                glVertex2f(0.5f, 0.5f);
                glVertex2f(0.5f, -0.5f);
                glEnd();

                glDrawArrays(GL_ARRAY_BUFFER, 0, 6);

                window.update();
            }

            window.destroy();
        } finally {
            glfwTerminate();
        }
    }

}
