package graphics;

public interface Renderer2D {

    public void dispose();
    public void submit(Renderable2D renderable);
    public void flush();

}
