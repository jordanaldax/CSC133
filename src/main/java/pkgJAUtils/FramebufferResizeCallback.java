


private static GLFWFramebufferSizeCallback resizeWindow =
            new GLFWFramebufferSizeCallback(){
                @Override
                public void invoke(long window, int width, int height){
                    glViewport(0,0,width, height);
                }
            };
            
public void enableResizeWindowCallback() {
        glfwSetFramebufferSizeCallback(glfwWindow, resizeWindow);
}  //  public void enableResizeWindowCallback(...)
