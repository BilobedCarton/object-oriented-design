Animator Readme by Matthew Hosking and Jeffrey Curran

---------------------- The Model ----------------------
The AnimationModel class is a representation of the data used in an Animation.
It uses a list of Shape objects to keep track of all the shapes and it uses a list of AnimationAction objects to keep track of the various actions being undertaken upon those shapes.

A Shape is a representation of a geometric shape. There are two implemented shapes: Rectangle, and Oval. They both use a similar structure with a position in x and y, a color, and a size in x and y. They also keep track of the time they should first appear, and the time they should disappear.

An AnimationAction is a representation of an action or chnage to be applied to a shape. There are three implemented actions: ColorShiftAction, MoveAction, and ScaleAction. They all have fields for original values, target values, target shape, and times to start and end.

Shapes are built using the ShapeBuilder class. This uses an enum to keep track of different types of shapes.
AnimationAction are build using the AnimationActionBuilder class. This also uses an enum to keep track of different types of actions.

SimpleAnimation:
	Properties:
	* actions - private ArrayList<AnimationAction> - This is the list of actions used by the animation model.
	* shapes - private ArrayList<Shape> - This is the list of shapes used by the animation model.

	Methods:
	- public getActions() - returns a immutable version of the list of actions.
	- public getShapes() - returns a immutable version of the list of shapes.
	- public addAction(AnimationAction action) - adds the given action to the list.
	- public addShape(Shape shape) - adds the given shape to the list.
	- public runCycle(int currTick) - runs a cycle of the animation. Executing all actions with a startTick before currTick and an endTick after currTime.
	- public toString(double ticksPerSecond) - converts this model into a string representation using the given value of ticksPerSecond.
	- public getShapeStateAt(int tick, Shape s) - returns a version of the given shape with its value changed to reflect its predicted state at the given tick.

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


---------------------- Model CHANGELOG ----------------------
	- All toString() methods are now toString(double ticksPerSecond) to properly calculate the time values in second when converting to a string.

	- Added interface IAnimationModel.
		- Renamed AnimationModel to SimpleAnimation.
		- SimpleAnimation implements IAnimationModel.
		- Removed animationIncomplete(int currTime) method.
		- Added getShapeStateAt(int tick, Shape s) method that gets the shape's state at the given tick (with changes by actions used by the model).
		- Changed get methods (for the list shapes and actions) to return immutable lists instead of the original lists.
		- Added static findShape(String name, List<Shape> shapes) method that searches through the list for a shape with the given name and returns it.
		- When an action is added, it should have its original values updated with the values of the target shape at the starting tick of the new action.

	- Tweaked ShapeBuilder to use a more correct builder style.
		- Creating a new Shape requires this kind of structure:
			ShapeBuilder.initialize().setVariousValues(variousValues).Build(ShapeType);
		- The name, tick values, and color all must be set for the build to succeed.
		- Also added a copy method to copy a given Shape completely.

	- Added AnimationActionBuilder to build actions.
		- Creating a new AnimationAction requires a similar structure to the ShapeBuilder:
			AnimationActionBuilder.initialize().setVariousValues(variousValues).Build(AnimationActionType);
		- The target shape, tick values, and corresponding changing values must be set for the build to succeed.
		- Also added a copy method to copy a given AnimationAction completely.

	- Added interface IAnimationPiece.
		- abstract class Shape implements IAnimationPiece.
		- Removed render() method.
		- Added get methods for the various properties of a Shape.

	- Added interface ITimedAction.
		- abstract class AnimationAction implements ITimedAction.
		- Added an executeFinal() method that executes the final change to the target. This guarantees that the target will be exactly where its meant to be.
		- Added updateOriginalValues() and setOriginalValues(Shape s) to allow for the action to have the correct original values at time of execution.
		- Added get methods for the various properties.