package graphics.layers;

import graphics.Renderable2D;
import graphics.Renderer2D;
import maths.mat4;

import java.util.ArrayList;
import java.util.List;

public class Group extends Renderable2D {

    private List<Renderable2D> m_Renderables = new ArrayList<>();
    private mat4 m_TransformationMatrix;

    public Group(mat4 transform) {
        m_TransformationMatrix = transform;
    }

    public void add(Renderable2D renderable) {
        m_Renderables.add(renderable);
    }

    @Override
    public void submit(Renderer2D renderer) {
        renderer.push(m_TransformationMatrix);

        for (Renderable2D renderable : m_Renderables) {
            renderable.submit(renderer);
        }

        renderer.pop();
    }

}
