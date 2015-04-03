package graphics;

import maths.mat4;
import maths.vec2;
import maths.vec3;
import maths.vec4;
import utils.FileUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private int m_ShaderID;
    private String m_VertPath, m_FragPath;

    public Shader(String vertPath, String fragPath) {
        m_VertPath = vertPath;
        m_FragPath = fragPath;

        m_ShaderID = load();
    }

    private int load() {
        int program = glCreateProgram();
        int vertex = glCreateShader(GL_VERTEX_SHADER);
        int fragment = glCreateShader(GL_FRAGMENT_SHADER);

        String vertSource = FileUtils.readFile(m_VertPath);
        String fragSource = FileUtils.readFile(m_FragPath);

        glShaderSource(vertex, vertSource);
        glCompileShader(vertex);

        int compiled = glGetShaderi(vertex, GL_COMPILE_STATUS);
        if (compiled == GL_FALSE) {
            String error = glGetShaderInfoLog(vertex);
            System.out.println("Failed to compile vertex shader!\n" + error);

            glDeleteShader(vertex);
            return 0;
        }

        glShaderSource(fragment, fragSource);
        glCompileShader(fragment);

        compiled = glGetShaderi(fragment, GL_COMPILE_STATUS);
        if (compiled == GL_FALSE) {
            String error = glGetShaderInfoLog(fragment);
            System.out.println("Failed to compile fragment shader!\n" + error);

            glDeleteShader(fragment);
            return 0;
        }

        glAttachShader(program, vertex);
        glAttachShader(program, fragment);

        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertex);
        glDeleteShader(fragment);

        return program;
    }

    private int getUniformLocation(String name) {
        return glGetUniformLocation(m_ShaderID, name);
    }

    public void setUniform1f(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform1i(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform2f(String name, vec2 vector) {
        glUniform2f(getUniformLocation(name), vector.x, vector.y);
    }

    public void setUniform3f(String name, vec3 vector) {
        glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
    }

    public void setUniform4f(String name, vec4 vector) {
        glUniform4f(getUniformLocation(name), vector.x, vector.y, vector.z, vector.w);
    }

    public void setUniformMat4(String name, mat4 matrix) {
        glUniformMatrix4(getUniformLocation(name), false, matrix.getBuffer());
    }

    public void enable() {
        glUseProgram(m_ShaderID);
    }

    public void disable() {
        glUseProgram(0);
    }

}