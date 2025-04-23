package pkgJARenderEngine;

import pkgJAUtils.*;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import pkgJAUtils.JAWindowManager;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwWaitEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class JAGeometryManager {

    private int NUM_COLS;
    private int NUM_ROWS;
    private int TOTAL_PRIMS;
    private int SIZE;
    private int OFFSET;
    private int PADDING;
    private int[] WinWidthHeight;
    private JAPingPongArray myPPArray;

    protected JAGeometryManager(int maxRows, int maxCols, int offset, int size, int padding, int[] winWidthHeight) {
        //
    }

    protected float[] generateTilesVertices(final int rowTiles, final int colTiles) {
        return null;
    }

    protected boolean generateTilesVertices(final JAGoLArray myGoLA, float[] vertices) {
        return false;
    }

    protected int[] generateTilesIndices(final int totalTiles) {
        return null;
    }

    protected boolean fillArrayWithTileVertices(float[] vertices, int startIndex, float xmin, float ymin) {
        return false;
    }

}
