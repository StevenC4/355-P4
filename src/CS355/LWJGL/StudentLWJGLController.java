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

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;
/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController {
    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();

	private Point3D translate = new Point3D(0, -5, -20);
	private float rotate = 0;

	//This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL() {
	    perspective();
    }

    @Override
    public void update() {

    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard() {
	    float rotateBy = 0.7f;
	    float translateBy = 0.3f;

	    if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
	        translate.x += translateBy * Math.cos(Math.toRadians(rotate));
	        translate.z += translateBy * Math.sin(Math.toRadians(rotate));
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
	        translate.x -= translateBy * Math.cos(Math.toRadians(rotate));
	        translate.z -= translateBy * Math.sin(Math.toRadians(rotate));
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
	        translate.x -= translateBy * Math.sin(Math.toRadians(rotate));
	        translate.z += translateBy * Math.cos(Math.toRadians(rotate));
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
	        translate.x += translateBy * Math.sin(Math.toRadians(rotate));
	        translate.z -= translateBy * Math.cos(Math.toRadians(rotate));
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
	        rotate -= rotateBy;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
	        rotate += rotateBy;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
	        translate.y -= translateBy;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
	        translate.y += translateBy;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
	        translate = new Point3D(0, -5, -20);
	        rotate = 0;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
	        orthographic();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
	        perspective();
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);

	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    glRotatef(rotate, 0, 1, 0);
	    glTranslatef((float)translate.x, (float)translate.y, (float)translate.z);

        //Do your drawing here.
        glColor3f(1.0f, 0.0f, 0.0f);
        Iterator<Line3D> linesIterator = model.getLines();

	    glBegin(GL_LINES);
	    while(linesIterator.hasNext()) {
	        Line3D line3D = linesIterator.next();
		    glVertex3d(line3D.start.x, line3D.start.y, line3D.start.z);
		    glVertex3d(line3D.end.x, line3D.end.y, line3D.end.z);
        }
        glEnd();
    }

	public void perspective() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60, 1.3f, 1, 100);
	}

	public void orthographic() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-15, 15, -15, 15, 100, 100);
	}
}
