# PiMesuem-Game-ChineseChess 

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

[**Chinese Chess**](https://en.wikipedia.org/wiki/Xiangqi)

## Mathematical Model of ChineseChess
### **ChessBoard:**    
* The chessboard consists of **10** rows and **9** columns,the mapping mathematical model is a plane systems of coordinates,because of the existence of chess notation,form the first person perspective,facing chessboard，**columns 1 to 9** from left to right,**rows 1 to 10** from bottom to top,therefore, two-dimensional coordinate system of **XY** axis is established.

* From right to left, in the positive direction of the **X-axis**,From bottom to top, it is in the positive direction of the **Y axis**,the lower-left coordinates are assumed to be **(1,1)**,One-to-one correspondence with chess notation.

* Chess placement is a finite set in a two-dimensional coordinate system,that is, **10 * 9 = 90** coordinate points,it's a fixed data on the chessboard. 

### **Chessman:**  
* There are **32** chess pieces at the beginning,chessman with coordinate position attributes.The chessboard holds the chessman,we call the set of chessman **R**.

* Chessman have the function of moving,after moving according to the characteristics of different chessman,the set **R** of chessman will be processed, and before moving, it will be constrained by other chessman on the falling point,the result of moving will affect the placement of the chessboard. 

* The only way to reduce set **R** is that **red** and **black** chessman occupy the same coordinate position, and the former can only be removed by the latter.

### **Play Chess:**
* That is to say, turn system uses **mobile function** for chessboard, queries chessboard according to the **rules** of chessboard before moving, meets the conditions, then moves chessman, traverses the **possibilities of falling chessman**, and reviews the chessboard situation from the end of function execution 

## Data Structure Design of Chess

### **Mapping Data Model:**

* The chessboard is a **global object**, which can be operated in an **orderly manner**. It contains a set of chessman objects constrained by fixed coordinate data. It holds a global method or an operation object. Each round of operation produces two main behaviors for the set of chessboard objects: **deleting the chessman** objects and **changing the coordinate attributes** of the chessman objects.

* Chessman are data objects, basic attributes are coordinates, basic methods are to change coordinate values, different chessman are derived from the basic class of chessman, the **method of changing coordinates** can be rewritten, or each chess object contains a constraint class, monitor the **occupancy of coordinates of chessboard objects**, and calculate the preconditions for changing the coordinate value to be full of its movement.

* **Playing Chess** is a global method for the existence of operating objects or chessboard objects. The result of each processing of set R is rendered to Ui by using **game engine** through defined rules.

## Game Demo

![Game-Demo](pimuseum-game-chinesechess.gif)

### **Continue Optimizing**  

[Jiervs](https://github.com/Jiervs)

