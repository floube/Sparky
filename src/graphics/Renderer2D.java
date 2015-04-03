package graphics;

public interface Renderer2D {

    public void submit(Renderable2D renderable);
    public void flush();

}
