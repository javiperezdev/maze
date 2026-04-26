# MazeSolver v1.4.0

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)

## About the Project
MazeSolver is a Java-based application designed to address pathfinding problems, which are commonly found in sectors such as autonomous robotics, video game AI, and global navigation systems (GPS). The core of the project lies in the implementation of two search algorithms to solve plain-text mazes: Depth-First Search (DFS) and Breadth-First Search (BFS). 

## Key Features

* **User Authentication & Security:** Includes a complete registration and login system. Passwords are not stored in plain text; they are encrypted using the MD5 algorithm for enhanced security.
* **Robust Data Validation:** Utilizes a dedicated utility class to validate inputs in real-time. This includes Regex validation for emails and mathematical validation for the Spanish NIF (Modulo 23 algorithm), ensuring database integrity before any transaction occurs.
* **User Profile Management:** Authenticated users can modify their personal data. 
    * Editable fields include password, full name, NIF, email, address, and birthdate. 
    * ID and username are strictly read-only to maintain relational integrity. 
    * The system verifies that a new NIF or email is not already in use by another user before updating the database. 
    * Users can also permanently delete their accounts by confirming their password, which automatically logs them out.
* **Dynamic Maze Processing:** The system loads mazes from plain text (`.txt`) files and parses them into a two-dimensional array. The `#` symbol represents physical walls, while blank spaces represent accessible corridors.
* **Custom Coordinate Configuration:** Authenticated users can establish custom starting and ending coordinates dynamically. The system prevents logical collisions, such as placing coordinates out of bounds or directly on a wall.
* **PDF Report Generation:** After successfully solving a maze, users are prompted with the option to export the results into a PDF report. 
    * The document includes a header (program title, version, and user), the maze filename, total steps taken, and a detailed list of movements. 
    * The maze grid is graphically rendered in the PDF using small 20x20 pixel square images for each box. 
    * Reports are saved in the `/assets` folder with a unique timestamp-based filename format (`year_month_day_hour_minute_second`) without interrupting the program's execution flow if an error occurs.
* **System Event Logging:** The application features an internal logging system that records user interactions and application states to a plain text file located at `./assets/files/syslog.txt`. 
    * The log file is created automatically if it does not exist. 
    * Entries are formatted chronologically: `Date and Time - Event Type - Event Data`. 
    * Tracked events include program start/end, login/registration attempts (both successful and failed), maze loading and coordinate settings, pathfinding executions, and session logouts.

## Pathfinding Algorithms
* **First Path - Depth-First Search (DFS):** This algorithm explores a branch exhaustively before backtracking. It uses a Stack (LIFO structure) to keep track of the current route, finding a fast solution by prioritizing movements in a strict order: up, right, down, and left.
* **Shortest Path - Breadth-First Search (BFS):** Unlike DFS, this algorithm expands its search radially, exploring all neighbor nodes at the same distance before moving to the next level. By utilizing a Queue (FIFO structure) and tracking parent nodes, it guarantees finding the mathematically shortest path between the origin and the goal.

## System Architecture
The application is built following Object-Oriented Programming (OOP) principles, ensuring a clean separation between business logic, data models, and interface utilities.
* **DAO Pattern (Data Access Object):** Completely decouples the business logic from the persistence logic. It securely centralizes all MySQL database operations, including login authentication, new user insertion, and duplicate record prevention.
* **Session Management:** A dedicated `Session` class acts as the State Manager. It safely handles the user's active session lifecycle, coordinates the sign-up process, and serves as the bridge between the user interface and the DAO layer.
