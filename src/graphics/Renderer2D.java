package graphics;

public abstract class Renderer2D {

    public abstract void dispose();

    public void begin() { }
    public abstract void submit(Renderable2D renderable);
    public void end() { }
    public abstract void flush();

}
