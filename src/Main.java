import graphics.Window;

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

            while (!window.closed()) {
                window.clear();

                glBegin(GL_QUADS);
                glVertex2f(-0.5f, -0.5f);
                glVertex2f(-0.5f, 0.5f);
                glVertex2f(0.5f, 0.5f);
                glVertex2f(0.5f, -0.5f);
                glEnd();

                window.update();
            }
        } finally {
            glfwTerminate();
        }
    }

}
