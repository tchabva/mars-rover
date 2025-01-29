# Mars Rover
A Java application that simulates the movement of rovers on a rectangular plateau on Mars. Users can define the plateau size, deploy multiple rovers, and control their movements using simple commands.

## Description

This program allows users to:
- Create a rectangular plateau with custom dimensions
- Deploy multiple rovers on the plateau with specific coordinates and orientations
- Control rovers using simple movement commands (L, R, M)
- Ensure rovers don't collide by validating landing positions
- View the final positions of all rovers after executing movement commands

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- An IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
- Git (for cloning the repository)

## Installation

1. Clone or download zip file of the repository:
```bash
git clone https://github.com/tchabva/mars-rover.git
```

2. (If required unzip file first) Open the project in your preferred IDE:

### IntelliJ IDEA
- Go to `File -> Open`
- Navigate to the cloned repository
- Select the root folder and click `OK`
- Wait for the IDE to import and build the project

### Eclipse
- Go to `File -> Import`
- Select `Existing Maven Projects`
- Navigate to the cloned repository
- Select the root folder and click `Finish`

### VS Code
- Open VS Code
- Click `File -> Open Folder`
- Navigate to the cloned repository
- Select the folder and click `Select Folder`

## Usage

1. Run the `Main` class located in `src/main/java/org/northcoders/Main.java`

2. Follow the interactive prompts:

   a. Enter plateau dimensions (e.g., "5 5")

   b. For each rover:
   - Enter starting position and orientation (e.g., "1 2 N")
   - Enter movement instructions using L (turn left), R (turn right), and M (move forward) (e.g., "LMLMLMLMM")
   - Choose whether to add another rover (Y/N)

3. The program will display:
   - Initial positions of all rovers
   - Movement execution confirmation
   - Final positions of all rovers

### Example Interactive Session
```
Welcome to Mars!
Get ready to deploy...

Choose the size of the plateau you want to explore
Input the length and the width of the area in the following format "10 10" or "4 4":
5 5
You have created a plateau with dimensions: X = 5,  Y = 5

Choose where you want to deploy your Rover!
Input the X and Y coordinates that are within on Plateau and the direction your Rover is facing.
The possible directions are the Compass directions: N = North, E = East, S = South and W = West
Please use the following format "0 0 N" or "1 3 E":
1 2 N
Position: (1, 2) Direction: North

Now it is time to choose how you want to move your Rover!
The the three possible instructions are:
R = turn your Rover by one compass direction to the right e.g If your is facing North, it will now face West.
L = turn your Rover by one compass direction to the left e.g. If your Rover's facing North, it will now face East.
M = move your Rover one position in the direction it is facing.
Please enter your instructions in the following format "RMLRM":
LMLMLMLMM
[L, M, L, M, L, M, L, M, M]

Do you want to add another Rover to your plateau?
If so, please input "Y" or "Yes", otherwise press enter to proceed:
Y

Choose where you want to deploy your Rover!
Input the X and Y coordinates that are within on Plateau and the direction your Rover is facing.
The possible directions are the Compass directions: N = North, E = East, S = South and W = West
Please use the following format "0 0 N" or "1 3 E":
3 3 E
Position: (3, 3) Direction: EAST

Now it is time to choose how you want to move your Rover!
The the three possible instructions are:
R = turn your Rover by one compass direction to the right e.g If your is facing North, it will now face West.
L = turn your Rover by one compass direction to the left e.g. If your Rover's facing North, it will now face East.
M = move your Rover one position in the direction it is facing.
Please enter your instructions in the following format "RMLRM":
MMRMMRMRRM
[M, M, R, M, M, R, M, R, R, M]

Do you want to add another Rover to your plateau?
If so, please input "Y" or "Yes", otherwise press enter to proceed:
[Enter]

The initial position(s) of your Rovers
1 2 N
3 3 E

Moving the Rovers...

The final position(s) of your Rovers
1 3 N
5 1 E
```

## Movement Rules
- Rovers move sequentially (one at a time)
- Multiple rovers cannot occupy the same position
- Rovers cannot move outside the plateau boundaries
- Each rover maintains its position and orientation until instructed to move

## Input Format

### Plateau Size
- Two integers separated by a space
- Represents the upper-right coordinates of the plateau
- Lower-left coordinates are assumed to be (0,0)
- Example: "5 5" creates a 6x6 plateau (0-5 on each axis)

### Rover Position
- Two integers and one letter separated by spaces
- Integers represent X and Y coordinates
- Letter represents direction (N = North, E = East, S = South, W = West)
- Example: "1 2 N" places rover at (1,2) facing North

### Movement Instructions
- String of letters (L, R, M)
- L: Turn left 90 degrees
- R: Turn right 90 degrees
- M: Move forward one grid point
- Example: "LMLMLMLMM"