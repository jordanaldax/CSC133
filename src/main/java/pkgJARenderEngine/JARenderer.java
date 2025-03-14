package pkgJARenderEngine;

import org.joml.Matrix4f;
import pkgJAUtils.JAWindowManager;

import java.nio.FloatBuffer;

public class JARenderer {
    private int NUM_COLS;
    private int NUM_ROWS;
    private int SIZE;
    private int PADDING;
    private int OFFSET;
    private int VPT;
    private int EPT;
    private int FPV;
    private static int OGL_MATRIX_SIZE;

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

    private void initOpenGL() {
        //
    }

    private void renderObjects() {
        //
    }

    private int[] generateTileIndices(final int rows, final int cols) {
        return null;
    }

    public void render(final int offset, final int padding, final int size, final int numRows, final int numCols) {
        //
    }
}
