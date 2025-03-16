package pkgJARenderEngine;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import pkgJAUtils.JAWindowManager;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwWaitEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class JARenderer {
    private int NUM_COLS;
    private int NUM_ROWS;
    private int SIZE;
    private int PADDING;
    private int OFFSET;
    private final int FPV = 2; // Floats per Vertex
    private final int VPT = 4; // Vertices per Tile
    private final int IPT = 6; // Indices per Tile
    private static final int OGL_MATRIX_SIZE = 16;

    private int shader_program;
    private int[] winWidthHeight;
    private int vpMatLocation;
    private int renderColorLocation;

    private FloatBuffer myFloatBuffer;
    private JAWindowManager myWM;
    private Matrix4f viewProjMatrix;

    public JARenderer(JAWindowManager wm) {
        myWM = wm;
    }

    private float[] generateTilesVertices(final int rowTiles, final int colTiles) {
        return null;
    }

    private int[] generateTileIndices(final int rows, final int cols) {
        int[] indices = new int[NUM_ROWS * NUM_COLS * IPT];

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                int tileNum = row * cols + col;
                int startIndex = tileNum * IPT;
                int startIV = tileNum * VPT;

                // First Triangle
                indices[startIndex] = startIV;
                indices[startIndex + 1] = startIV + 1;
                indices[startIndex + 2] = startIV + 2;

                // Second Triangle
                indices[startIndex + 3] = startIV;
                indices[startIndex + 4] = startIV + 2;
                indices[startIndex + 5] = startIV + 3;
            }
        }
        return indices;
    }

    private void initOpenGL() {
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);

        // Don't need to set WIN_WIDTH and WIN_HEIGHT, just use values from myWM
        int[] windowSize = myWM.getWindowSize();
        glViewport(0, 0, windowSize[0], windowSize[1]);

        glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
        this.shader_program = glCreateProgram();
        int vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs,
                "uniform mat4 viewProjMatrix;" +
                        "void main(void) {" +
                        " gl_Position = viewProjMatrix * gl_Vertex;" +
                        "}");
        glCompileShader(vs);
        glAttachShader(shader_program, vs);
        int fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs,
                "uniform vec3 color;" +
                        "void main(void) {" +
                        " gl_FragColor = vec4(0.6f, 0.7f, 0.1f, 1.0f);" +
                        "}");
        glCompileShader(fs);
        glAttachShader(shader_program, fs);
        glLinkProgram(shader_program);
        glUseProgram(shader_program);
        vpMatLocation = glGetUniformLocation(shader_program, "viewProjMatrix");
        return;
    }

    private void renderObjects() {
        while (!myWM.isGlfwWindowClosed()) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            int vbo = glGenBuffers();
            int ibo = glGenBuffers();

            float[] vertices = {-20f, -20f, 20f, -20f, 20f, 20f, -20f, 20f};
            int[] indices = {0, 1, 2, 0, 2, 3};

            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, (FloatBuffer) BufferUtils.
                    createFloatBuffer(vertices.length).
                    put(vertices).flip(), GL_STATIC_DRAW);
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (IntBuffer) BufferUtils.
                    createIntBuffer(indices.length).
                    put(indices).flip(), GL_STATIC_DRAW);
            glVertexPointer(2, GL_FLOAT, 0, 0L);
            viewProjMatrix.setOrtho(-100, 100, -100, 100, 0, 10);
            glUniformMatrix4fv(vpMatLocation, false,
                    viewProjMatrix.get(myFloatBuffer));
            glUniform3f(renderColorLocation, 1.0f, 0.498f, 0.153f);
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            final int VTD = 6; // need to process 6 Vertices To Draw 2 triangles
            glDrawElements(GL_TRIANGLES, VTD, GL_UNSIGNED_INT, 0L);
            myWM.swapBuffers();
        }
    }

    public void render(final int offset, final int padding, final int size, final int numRows, final int numCols) {
        //
    }

    private void renderLoop() {
        glfwPollEvents();
        initOpenGL();
        renderObjects();
        /* Process window messages in the main thread */
        while (!myWM.isGlfwWindowClosed()) {
            glfwWaitEvents();
        }
    }
}
