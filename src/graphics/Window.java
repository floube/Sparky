package graphics;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GLContext;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final static int MAX_KEYS = 1024;
    private final static int MAX_BUTTONS = 32;

    private static GLFWWindowSizeCallback windowSizeCallback;
    private static GLFWKeyCallback keyCallback;
    private static GLFWMouseButtonCallback mouseButtonCallback;
    private static GLFWCursorPosCallback cursorPosCallback;

    private String m_Title;
    private int m_Width, m_Height;
    private long m_Window;

    private boolean[] m_Keys = new boolean[MAX_KEYS];
    private boolean[] m_MouseButtons = new boolean[MAX_BUTTONS];
    private float mx, my;

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
        glfwSetWindowSizeCallback(m_Window, windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
            }
        });
        glfwSetKeyCallback(m_Window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                m_Keys[key] = (action != GLFW_RELEASE);
            }
        });
        glfwSetMouseButtonCallback(m_Window, mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                m_MouseButtons[button] = (action != GLFW_RELEASE);
            }
        });
        glfwSetCursorPosCallback(m_Window, cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mx = (float)xpos;
                my = (float)ypos;
            }
        });

        GLContext.createFromCurrent();
        System.out.println("OpenGL" + glGetString(GL_VERSION));

        return true;
    }

    public boolean isKeyPressed(int keycode) {
        // TODO: Log this!
        if (keycode >= MAX_KEYS) {
            return false;
        }

        return m_Keys[keycode];
    }

    public boolean isMouseButtonPressed(int button) {
        // TODO: Log this!
        if (button >= MAX_BUTTONS) {
            return false;
        }

        return m_MouseButtons[button];
    }

    public Point2D.Float getMousePosition() {
        return new Point2D.Float(mx, my);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void update() {
        glfwPollEvents();
        glfwSwapBuffers(m_Window);
    }

    public void destroy() {
        windowSizeCallback.release();
        keyCallback.release();
        mouseButtonCallback.release();
        cursorPosCallback.release();

        glfwDestroyWindow(m_Window);
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
