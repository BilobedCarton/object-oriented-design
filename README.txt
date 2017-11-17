Animator Readme by Matthew Hosking and Jeffrey Curran
Object Oriented Design Assignment 6 - Animator With Views

---------------------- The Model ----------------------
The IAnimationModel interface is a representation of the data used in an Animation.
The implementation of that interface is SimpleAnimation.
This uses a list of Shape objects to keep track of all the shapes and it uses a list of AnimationAction objects to keep track of the various actions being undertaken upon those shapes.

A Shape implements IAnimationPiece and is a representation of a geometric shape. There are two implemented shapes: Rectangle, and Oval. They both use a similar structure with a position in x and y, a color, and a size in x and y. They also keep track of the time they should first appear, and the time they should disappear.
They also have a boolean field representing visibility. This is referenced when displaying the animation and exporting it to SVG.
Shapes all have a final instance of an OriginalShape object that stores the state of the shape at time of construction.
This OriginalShape is used for recovering the Shape's original state.

An AnimationAction implements ITimedAction and is a representation of an action or change to be applied to a shape. There are three implemented actions: ColorShiftAction, MoveAction, and ScaleAction. They all have fields for original values, target values, target shape, and times to start and end.

Shapes are built using the ShapeBuilder class. This uses an enum to keep track of different types of shapes.
AnimationAction are build using the AnimationActionBuilder class. This also uses an enum to keep track of different types of actions.

SimpleAnimation:
	Properties:
	* updatedActions - private ArrayList<AnimationAction> - This is the list of actions ready to be used by the animation model.
	* actionsToBeUpdated - private ArrayList<AnimationAction> - This is the list of actions used by the animation model that have yet to be updated.
	* shapes - private ArrayList<Shape> - This is the list of shapes used by the animation model.

	Methods:
	- public getActions() - returns a immutable version of the list of actions.
	- public getShapes() - returns a immutable version of the list of shapes.
	- public addAction(AnimationAction action) - adds the given action to the actionsToBeUpdated list.
	- public addShape(Shape shape) - adds the given shape to the list.
	- public runCycle(int currTick) - Updates the actionsToBeUpdated and then runs a cycle of the animation. Executing all actions with a startTick before currTick and an endTick after currTime.
	- public toString(double ticksPerSecond) - converts this model into a string representation using the given value of ticksPerSecond.
	- public getShapeStateAt(int tick, Shape s) - returns a version of the given shape with its value changed to reflect its predicted state at the given tick.
    - public updateActions() - Runs through the actionsToBeUpdated and sets each action's original values to the correct values at the time the action starts.

	SimpleAnimation includes the Builder class.
	Builder implements TweenModelBuilder<IAnimationModel>
		Properties:
		* actions - private ArrayList<AnimationAction> - This is the list of actions to be used by the animation model.
		* shapes - private ArrayList<Shape> - This is the list of shapes to be used by the animation model.

		Methods:
		- public addOval(String name, float cx, float cy, float xRadius, float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) - adds an oval.
		- public addRectangle(String name, float cx, float cy, float xRadius, float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) - adds a rectangle.
		- public addMove(String name, float moveFromX, float moveFromY, float moveToX, float moveToY, int startTime, int endTime)  - adds a move action.
		- public addColorChange(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB, int startTime, int endTime) - adds a ColorShift action.
		- public addScaleToChange(String name, float fromSx, float fromSy, float toSx, float toSy, int startTime, int endTime) - adds a scale action.
		- public build() - this method actually creates and returns the model.

---------------------- Model Changelog (Assignment 6 to Assignment 7) ----------------------
    - Split actions list into two:
        - updatedActions - the actions whose original values have already been updated.
        - actionsToBeUpdated - the actions that need to be updated.
    - Added updateActions() method that runs through all the actions within the actionsToBeUpdated list and set their original values to the state of the shape at the tick the action begins.
    - Shapes now have a visibility field that affects the visual display and svg inclusion.

---------------------- VIEWS ----------------------
There are four types of view:
TextView - outputs a text version of the animation.
VisualView - outputs a graphical version of the animation.
SVGView - outputs an SVG version of the animation.
InteractiveView - displays a graphical window with user interaction including these functionalities:
    * Start, pause, resume, restart, and reset. Reset and restart differ in the sense that restart simply starts the animation over without resetting visibilities and reset completely resets the animation back to its original state.
    * Change speed in ticksPerSecond - there is a slider to affect the speed of the animation.
    * Toggle looping - whether or not the animation should repeat once it ends.
    * Specify shapes to be hidden from display when rendering the animation.
    * Export the visible shapes to an SVG file.

All four extend the AbstractView class.
AbstractView implements IView.

IView has the following methods:
	- update(int currTick) - runs an update on the output using the given current tick.
	- start() - sets up the view to begin displaying the data.
	- isInteractive() - determines if this view is interactive or not.
	- setUpInteractivity(InteractiveAnimationController controller) - sets up the interactivity of this view using the methods of an InteractiveAnimationController.
	- export() - exports the data of the model into a form depending upon the view: SVG format or simple text.

AbstractView:
	Properties:
		* private ReadOnlySimpleAnimation model - this is the model whose data we use.

TextView:
	Properties:
		* private Appendable out - this is where the text is outputted to on start.
		* private double speed - this is the speed of the animation.

VisualView:
	Properties:
		* protected BasicAnimationGraphicsFrame frame - this is the visual frame in which the visuals are rendered.

SVGView:
	Properties:
		* private Appendable out - this is where the svg xml data is outputted to on start.

InteractiveView:
    Properties:
        * private Appendable out - this is where the svg is outputted when the user presses the output button.
        * private double speed - this is the speed of the animation.
        * protected InteractiveAnimationGraphicsFrame frame - this is the visual frame in which the graphical ui is rendered.

---------------------- View Changelog (Assignment 6 to Assignment 7) ----------------------
    - centralized methods to all exist within the IView interface.
    - moved UpdateListener to the listeners folder in cs3500.animation.control
    - added new view: InteractiveView - this allows the user to interact with the model through the view and the controller.
    - added new graphical frame InteractiveAnimationGraphicsFrame - this is the frame that is used by an interactive view.

---------------------- CONTROLLERS ----------------------
There are two types of controller: AnimationController and InteractiveAnimationController.

AnimationController implements the interface IAnimationController.
    - This controller performs all the actions that affect the model and view: running the animation, exporting data, etc.

InteractiveAnimationController extends AnimationController.
    - This controller contains many of the actions needed for user interactions and also performs the same role as the AnimationController.
    - Must use an InteractiveView for full functionality.

IAnimationController:
    Methods:
        - getModel() - returns the connected model as a ReadOnlyAnimationModel object.
        - getView() - returns the connected IView for this controller.
        - getSpeed() - returns the speed as a double representing ticks executed per second.
        - getCurrTick() - returns an int representing the current tick being displayed or executed.
        - go() - Starts the view and sets up various objects needed for the animation.
        - runUpdate() - runs an update on the model using the current tick and then updates the view using the new model information.
        - changeSpeed(double ticksPerSecond) - sets the speed to the given value.
        - reset(boolean originalVisibility) - resets the model's data to the original values. Uses originalVisibility to determine whether or not to reset the visibility values.

AnimationController:
    Properties:
        * IAnimationModel model - this is the model used by this controller.
        * IView view - this is the view used by this controller.
        * double ticksPerSecond - this is the speed of the animation in ticks executed per second.
        * int currTick - this is the current tick being displayed and executed by the controller.
        * Timer timer - this is the timer used to pace and update the animation.

    Methods:
        - resetTimer() - this resets and stops the timer.

InteractiveAnimationController:
    Properties:
        * private boolean loopAnimation - this is used to determine if this animation should loop on completion.
        * private List<String> selectedShapes - this is used when specifying shapes to make visible or invisible.

    Methods:
        - startAnimation() - starts the animation's timer and starts the view.
        - pauseAnimation() - pauses the animation.
        - toggleLooping() - sets the loopAnimation value to its opposite, "toggling" the visibility value.
        - setSelectedShapes(String[] shapeStates) - sets the list of selected shapes to the given list of shapes (in the from of shapeStates).
        - markSelectedShapesVisibility(boolean visible) - Update the corresponding shapes for the shape states held in the selectedShapes list with the given visibility.