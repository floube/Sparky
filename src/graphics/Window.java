package graphics;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private String m_Title;
    private int m_Width, m_Height;
    private long m_Window;

    public Window(String title, int width, int height) {
        m_Title = title;
        m_Width = width;
        m_Height = height;

        if (!init()) {
            glfwTerminate();
        }
    }

    private boolean init() {
        if (glfwInit() != GL_TRUE) {
            System.out.println("Failed to initialize GLFW!");
            return false;
        }

        m_Window = glfwCreateWindow(m_Width, m_Height, m_Title, NULL, NULL);
        if (m_Window == NULL) {
            System.out.println("Failed to create GLFW window!");
            return false;
        }

        glfwMakeContextCurrent(m_Window);
        glfwSetWindowSizeCallback(m_Window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
            }
        });

        GLContext.createFromCurrent();
        System.out.println("OpenGL" + glGetString(GL_VERSION));

        return true;
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void update() {
        glfwPollEvents();
        glfwSwapBuffers(m_Window);
    }

    public boolean closed() {
        return glfwWindowShouldClose(m_Window) == GL_TRUE;
    }

    public int getWidth() {
        return m_Width;
    }

    public int getHeight() {
        return m_Height;
    }

}
