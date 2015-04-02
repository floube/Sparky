import graphics.Window;

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

            while (!window.closed()) {
                window.clear();

                Point2D.Float mousePosition = window.getMousePosition();
                System.out.println(mousePosition.x + ", " + mousePosition.y);

                glBegin(GL_QUADS);
                glVertex2f(-0.5f, -0.5f);
                glVertex2f(-0.5f, 0.5f);
                glVertex2f(0.5f, 0.5f);
                glVertex2f(0.5f, -0.5f);
                glEnd();

                glDrawArrays(GL_ARRAY_BUFFER, 0, 6);

                window.update();
            }
        } finally {
            glfwTerminate();
        }
    }

}
