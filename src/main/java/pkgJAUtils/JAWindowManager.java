package pkgJAUtils;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import pkgJAUtils.JAWindowManager;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;

public class JAWindowManager {

    private static GLFWFramebufferSizeCallback resizeWindow =
            new GLFWFramebufferSizeCallback(){
                @Override
                public void invoke(long window, int width, int height){
                    glViewport(0,0,width, height);
                }
            };


    private static JAWindowManager my_window = new JAWindowManager();
    private long glfwWindow;
    private int win_height;
    private int win_width;

    private JAWindowManager() {}

    public void updateContextToThis() {}

    public void destroyGlfwWindow() {}

    public boolean isGlfwWindowClosed() {
        return true;
    }

    public static JAWindowManager get() {
        return my_window;
    }

    public static JAWindowManager get(int width, int height, int pos_x, int pos_y) {
        return my_window;
    }

    protected void setWinWidth(int width, int height) {}

    public void enableResizeWindowCallback() {
        glfwSetFramebufferSizeCallback(glfwWindow, resizeWindow);
    }  //  public void enableResizeWindowCallback(...)

    public void swapBuffers() {}

    private void initGlfwWindow() {}

    public int[] getWindowSize() {
        return new int[] {0};
    }
}
