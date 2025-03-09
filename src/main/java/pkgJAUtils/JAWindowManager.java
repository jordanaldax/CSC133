package pkgJAUtils;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

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
    private static long glfwWindow;
    private int WIN_WIDTH = 1024;
    private int WIN_HEIGHT = 1024;

    GLFWErrorCallback errorCallback;
    GLFWKeyCallback keyCallback;
    GLFWFramebufferSizeCallback fbCallback;
    int WIN_POS_X = 30, WIN_POX_Y = 90;

    private JAWindowManager() {}

    public void updateContextToThis() {
        initGLFWindow();
    }

    public void destroyGlfwWindow() {
        glfwTerminate();
    }

    public boolean isGlfwWindowClosed() {
        return my_window == null;
    }

    public static JAWindowManager get() {
        return my_window;
    }

    public static JAWindowManager get(int width, int height) {
        setWinWidth(width, height);
        return my_window;
    }

    public static JAWindowManager get(int width, int height, int orgX, int orgY) {
        get(width, height);
        setWindowPosition(orgX, orgY);
        return my_window;
    }  //  public SlWindowManager get(...)

    public static void setWindowPosition(int orgX, int orgY) {
        if (glfwWindow > 0) {
            glfwSetWindowPos(glfwWindow, orgX, orgY);
        }  //  if (glfwWindow > 0)
    }  //  public void setWindowPosition(...)

    protected static void setWinWidth(int width, int height) {
        my_window.WIN_WIDTH = width;
        my_window.WIN_HEIGHT = height;
    }

    public void enableResizeWindowCallback() {
        glfwSetFramebufferSizeCallback(glfwWindow, resizeWindow);
    }  //  public void enableResizeWindowCallback(...)

    public void swapBuffers() {
        glfwSwapBuffers(glfwWindow);
    }

    private void initGLFWindow() {
        glfwSetErrorCallback(errorCallback =
                GLFWErrorCallback.createPrint(System.err));
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 8);
        glfwWindow = glfwCreateWindow(WIN_WIDTH, WIN_HEIGHT, "CSC 133", NULL, NULL);
        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(glfwWindow, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int
                    mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, true);
            }
        });
        glfwSetFramebufferSizeCallback(glfwWindow, fbCallback = new
                GLFWFramebufferSizeCallback() {
                    @Override
                    public void invoke(long window, int w, int h) {
                        if (w > 0 && h > 0) {
                            WIN_WIDTH = w;
                            WIN_HEIGHT = h;
                        }
                    }
                });
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(glfwWindow, WIN_POS_X, WIN_POX_Y);
        glfwMakeContextCurrent(glfwWindow);
        int VSYNC_INTERVAL = 1;
        glfwSwapInterval(VSYNC_INTERVAL);
        glfwShowWindow(glfwWindow);
    }

    public int[] getWindowSize() {
        int[] size = new int[2];
        size[0] = WIN_WIDTH;
        size[1] = WIN_HEIGHT;
        return size;
    }
}
