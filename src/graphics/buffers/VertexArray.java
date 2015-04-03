package graphics.buffers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

    private int m_ArrayID;
    private List<Buffer> m_Buffers = new ArrayList<>();

    public VertexArray() {
        m_ArrayID = glGenVertexArrays();
    }

    public void dispose() {
        for (Buffer buffer : m_Buffers) {
            buffer.dispose();
        }

        glDeleteVertexArrays(m_ArrayID);
    }

    public void addBuffer(Buffer buffer, int index) {
        bind();
        buffer.bind();

        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, buffer.getComponentCount(), GL_FLOAT, false, 0, 0);

        buffer.unbind();
        unbind();

        m_Buffers.add(buffer);
    }

    public void bind() {
        glBindVertexArray(m_ArrayID);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

}
