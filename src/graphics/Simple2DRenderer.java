package graphics;

import maths.mat4;

import java.util.ArrayDeque;

import static org.lwjgl.opengl.GL11.*;

public class Simple2DRenderer implements Renderer2D {

    private ArrayDeque<StaticSprite> m_RenderQueue = new ArrayDeque<>();

    @Override
    public void dispose() {
        while (!m_RenderQueue.isEmpty()) {
            StaticSprite sprite = m_RenderQueue.pollFirst();
            sprite.dispose();
        }
    }

    @Override
    public void submit(Renderable2D renderable) {
        m_RenderQueue.addLast((StaticSprite) renderable);
    }

    @Override
    public void flush() {
        while (!m_RenderQueue.isEmpty()) {
            StaticSprite sprite = m_RenderQueue.pollFirst();
            sprite.getVAO().bind();
            sprite.getIBO().bind();

            sprite.getShader().setUniformMat4("ml_matrix", mat4.translation(sprite.getPosition()));
            glDrawElements(GL_TRIANGLES, sprite.getIBO().getCount(), GL_UNSIGNED_INT, 0);

            sprite.getIBO().unbind();
            sprite.getVAO().unbind();
        }
    }

}
