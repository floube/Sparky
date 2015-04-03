package graphics.buffers;

import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

    private int m_ArrayID;
    private Vector<Buffer> m_Buffers;

    public VertexArray() {
        m_ArrayID = glGenVertexArrays();
    }

    public void addBuffer(Buffer buffer, int index) {
        bind();
        buffer.bind();

        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, buffer.getComponentCount(), GL_FLOAT, false, 0, 0);

        buffer.unbind();
        unbind();
    }

    public void bind() {
        glBindVertexArray(m_ArrayID);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

}
