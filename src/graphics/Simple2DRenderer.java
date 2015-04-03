package graphics;

import maths.mat4;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.lwjgl.opengl.GL11.*;

public class Simple2DRenderer implements Renderer2D {

    private ArrayDeque<Renderable2D> m_RenderQueue = new ArrayDeque<>();

    @Override
    public void submit(Renderable2D renderable) {
        m_RenderQueue.addLast(renderable);
    }

    @Override
    public void flush() {
        while (!m_RenderQueue.isEmpty()) {
            Renderable2D renderable = m_RenderQueue.getFirst();
            renderable.getVAO().bind();
            renderable.getIBO().bind();

            renderable.getShader().setUniformMat4("ml_matrix", mat4.translation(renderable.getPosition()));
            glDrawElements(GL_TRIANGLES, renderable.getIBO().getCount(), GL_UNSIGNED_SHORT, 0);

            renderable.getIBO().unbind();
            renderable.getVAO().unbind();

            m_RenderQueue.removeFirst();
        }
    }

}
