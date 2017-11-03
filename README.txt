The AnimationModel class is a representation of the data used in an Animation.
It uses a list of Shape objects to keep track of all the shapes and it uses a list of AnimationAction objects to keep track of the various actions being undertaken upon those shapes.

A Shape is a representation of a geometric shape. There are two implemented shapes: Rectangle, and Oval. They both use a similar structure with a position in x and y, a color, and a size in x and y. They also keep track of the time they should first appear, and the time they should disappear.

An AnimationAction is a representation of an action or chnage to be applied to a shape. There are three implemented actions: ColorShiftAction, MoveAction, and ScaleAction. They all have fields for original values, target values, target shape, and times to start and end.

Shapes are built using the ShapeBuilder class. This uses an enum to keep track of different types of shapes.

AnimationModel:
	- getActions() - returns the list of actions.
	- getShapes() - returns the list of shapes.
	- addAction(AnimationAction action) - adds the given action to the list.
	- addShape(Shape shape) - adds the given shape to the list.
	- runAnimation() - runs the animation. Applying the changes done by the actions over time.
	- animationIncomplete(int currTime) - returns a boolean representing whether or not the animation has more to do.
	- runCycle(int currTime) - runs a cycle of the animation. Executing all actions with a startTime before currTime and an endTime after currTime.
	- toString() - converts this model into a string representation.
	

The 