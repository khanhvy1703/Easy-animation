#Assignment 9 (Extra Credit)

##Level 1
User is able to toggle between the outline and fill modes using Jbutton in the Interactive view. User can now add neww shape, Plus, in to the animation. Either with the input file, or addShape button in the interactive view. User can modify any shapes including the new shape, Plus, with using change dimension, move, and change color button in the interactive view. User can export the animation to SVG file. 
####Changes
#####Shape
We created a new shape class, Plus. The shape has one restriction, The shape's width and height need to be same in order to draw proper plus shape. To give the feature in the class, we over-rided our setAModelShape() method and gave restriction. Also AModelShape now has a variable isOutline. It is boolean. It is very useful to know if the shape is currently outlined or filled. 
#####View
In the interactive view, we created 5 new JButtons: Add shape, move, change dimension, change color, outline mode, export svg. We set action command on each of them and added listener to each. 
#####Controller
We created four new methods, newShapeAdder(), newMotionAdder(), outlineToggle(), exportSVG(). These are the helper functions to allow user can control animation. AddShape Method allows user to add animation with the textual input, add motion method allow user to modify the animation, outline toggle help user to build outlined animation, and svg export allow user to export the animation to svg file. 

##Level 2
User can do the discrete skipping (fast forwad) in the interactive view. User can use discrete feature without restarting the application,also user is allowed to use all the previous features from the interactive view. 
####Changes
#####View
We created JFrame button discrete
#####Controller
We created discrete Mode method that takes input in the textual box and user can control how much time they wanna skip. Also, they can disable a discrete feature if they want to. 

##Level 3
User can input file with tempo feature which can build fast forward and slow mode. Our tempo templete is this "tempo starting time ending time speed" example) tempo 20 50 -5 This means the slow mode with speed of 5 will start on tick 20 and end on tick 50.
####Changes
#####tools
We create new class tempo. It has the format with start, end, and speed. the data from the input file will be inserted in to this format. 
#####utils
We modify animation builder and reader so that the application can take a word, "temp", and its input and read and build.
#####Model
model now has new variable, the list of tempo. It stores the information about the tempo in the model and helps in the controller. 
#####Controller
We created a method called tempoChecker(). It created slow mode or fast mode depend on the input tempo. If it is not tempo animation, it shows in the normal speed. 