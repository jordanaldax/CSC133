package pkgJARenderEngine;

import pkgJAUtils.*;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class JAGeometryManager {

    private final int LIVE = 1;
    private final int DEAD = 0;

    private int NUM_COLS;
    private int NUM_ROWS;
    private int TOTAL_PRIMS;
    private int SIZE;
    private int OFFSET;
    private int PADDING;
    private int[] winWidthHeight;
    private JAGoLArray myPPArray;

    private final int FPV = 2; // Floats per vertex
    private final int VPT = 4; // Vertices per tile
    private final int IPT = 6; // Indices per tile

    protected JAGeometryManager(int maxRows, int maxCols, int offset, int size, int padding, int[] winWidthHeight) {
        this.NUM_ROWS = maxRows;
        this.NUM_COLS = maxCols;
        this.OFFSET = offset;
        this.SIZE = size;
        this.PADDING = padding;
        this.winWidthHeight = winWidthHeight;
    }

    protected void getPPA(JAGoLArray myPPA) {
        this.myPPArray = myPPA;
    }

    protected void tickUpdate() {
        myPPArray.run();
    }

    /*
        Vertices are (xmin, ymin), (xmin+SIZE, ymin), (xmin+SIZE, ymin-SIZE), and (xmin, ymin-SIZE)
        Order is:
            0: xmin
            1: ymin
            2: xmin + SIZE
            3: ymin
            4: xmin + SIZE
            5: ymin - SIZE
            6: xmin
            7: ymin - SIZE
     */
    protected float[] generateTilesVertices() {
        int liveCount = myPPArray.getLiveCount();
        float[] vertices = new float[liveCount * FPV * VPT];
        generateTilesVertices(vertices);
        return vertices;
    }

    protected boolean generateTilesVertices(float[] vertices) {
        int index = 0;
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 1; col < NUM_COLS; col++) {

                float xmin = OFFSET + col * (SIZE + PADDING);
                float ymin = winWidthHeight[1] - (OFFSET + SIZE + row * (SIZE + PADDING));

                if(myPPArray.getCell(row, col) == LIVE) {
                    // Bottom left
                    vertices[index++] = xmin;
                    vertices[index++] = ymin;

                    // Bottom right
                    vertices[index++] = xmin + SIZE;
                    vertices[index++] = ymin;

                    // Top right
                    vertices[index++] = xmin + SIZE;
                    vertices[index++] = ymin + SIZE;

                    // Top left
                    vertices[index++] = xmin;
                    vertices[index++] = ymin + SIZE;
                }
            }
        }
        return true;
    }

    protected int[] generateTilesIndices() {
        int liveCount = myPPArray.getLiveCount();
        int[] indices = new int[liveCount * IPT];

        int index = 0;
        int vertexOffset = 0;

        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 1; col < NUM_COLS; col++) {

                if(myPPArray.getCell(row, col) == LIVE) {
                    // First Triangle: Bottom Left, Bottom Right, Top Right
                    // 0, 1, 2
                    indices[index++] = vertexOffset;
                    indices[index++] = vertexOffset + 1;
                    indices[index++] = vertexOffset + 2;

                    // Second Triangle: Bottom Left, Top Right, Top Left
                    // 0, 2, 3
                    indices[index++] = vertexOffset;
                    indices[index++] = vertexOffset + 2;
                    indices[index++] = vertexOffset + 3;

                    vertexOffset += VPT;
                }
            }
        }

        return indices;
    }

    protected boolean fillArrayWithTileVertices(float[] vertices, int startIndex, float xmin, float ymin) {
        return false;
    }

}
