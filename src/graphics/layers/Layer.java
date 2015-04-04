package graphics.layers;

import graphics.Renderable2D;
import graphics.Renderer2D;
import graphics.Shader;
import maths.mat4;

import java.util.ArrayList;
import java.util.List;

public class Layer {

    protected Renderer2D m_Renderer;
    protected List<Renderable2D> m_Renderables = new ArrayList<>();
    protected Shader m_Shader;
    protected mat4 m_ProjectionMatrix;

    protected Layer(Renderer2D renderer, Shader shader, mat4 projectionMatrix) {
        m_Renderer = renderer;
        m_Shader = shader;
        m_ProjectionMatrix = projectionMatrix;

        m_Shader.enable();
        m_Shader.setUniformMat4("pr_matrix", m_ProjectionMatrix);
        m_Shader.disable();
    }

    public void dispose() {
        m_Shader.dispose();
        m_Renderer.dispose();

        for (Renderable2D renderable : m_Renderables) {
            renderable.dispose();
        }
    }

    public void add(Renderable2D renderable) {
        m_Renderables.add(renderable);
    }

    public void render() {
        m_Shader.enable();

        m_Renderer.begin();
        for (Renderable2D renderable : m_Renderables) {
            m_Renderer.submit(renderable);
        }
        m_Renderer.end();

        m_Renderer.flush();
    }

}
