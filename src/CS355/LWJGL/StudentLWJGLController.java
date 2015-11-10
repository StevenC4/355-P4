package CS355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import org.lwjgl.input.Keyboard;

import java.util.Iterator;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController
{

    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();

    private Point3D translation;

    //This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL()
    {

    }

    @Override
    public void update()
    {

    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            System.out.println("You are pressing A!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            System.out.println("You are pressing D!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            System.out.println("You are pressing W!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            System.out.println("You are pressing S!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            System.out.println("You are pressing Q!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            System.out.println("You are pressing E!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            System.out.println("You are pressing R!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            System.out.println("You are pressing F!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
            System.out.println("You are pressing H!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
            System.out.println("You are pressing O!");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            System.out.println("You are pressing P!");
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render()
    {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);

        //Do your drawing here.
        glColor3f(1.0f, 0.0f, 0.0f);
        Iterator<Line3D> linesIterator = model.getLines();

        glBegin(GL_LINES);
        while(linesIterator.hasNext()) {
            Line3D line3D = linesIterator.next();
            glVertex3f((float)line3D.start.x, (float)line3D.start.y, (float)line3D.start.z);
            glVertex3f((float)line3D.end.x, (float)line3D.end.y, (float)line3D.end.z);
        }
        glEnd();
    }

}
