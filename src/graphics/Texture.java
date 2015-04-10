package graphics;

import utils.ImageUtils;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private String m_Filename;
    private int m_TID, m_Width, m_Height;

    public Texture(String filename) {
        m_Filename = filename;
        m_TID = load();
    }

    private int load() {
        ImageUtils.DecodedImage image = ImageUtils.loadImage(m_Filename);

        m_Width = image.getWidth();
        m_Height = image.getHeight();

        int result = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, result);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, m_Width, m_Height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.getBuffer());
        glBindTexture(GL_TEXTURE_2D, 0);

        return result;
    }

    public void dispose() {

    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, m_TID);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getWidth() {
        return m_Width;
    }

    public int getHeight() {
        return m_Height;
    }

}
