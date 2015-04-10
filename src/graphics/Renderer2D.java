package graphics;

import maths.mat4;

import java.util.ArrayDeque;

public abstract class Renderer2D {

    protected ArrayDeque<mat4> m_TransformationStack = new ArrayDeque<>();
    protected mat4 m_TransformationBack;

    protected Renderer2D() {
        m_TransformationStack.addLast(mat4.identity());
        m_TransformationBack = m_TransformationStack.getLast().clone();
    }

    public abstract void dispose();

    public void push(mat4 matrix) {
        m_TransformationStack.addLast(m_TransformationBack.multiply(matrix));
        m_TransformationBack = m_TransformationStack.getLast().clone();
    }

    public void pushOverride(mat4 matrix) {
        m_TransformationStack.addLast(matrix);
        m_TransformationBack = matrix.clone();
    }

    public void pop() {
        // TODO: Log this!
        if (m_TransformationStack.size() > 1) {
            m_TransformationStack.removeLast();
        }

        m_TransformationBack = m_TransformationStack.getLast().clone();
    }

    public void begin() { }
    public abstract void submit(Renderable2D renderable);
    public void end() { }
    public abstract void flush();

}
