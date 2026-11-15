# 🚗 Waze For Parking

A desktop navigation application built in Java Swing that visualizes a parking lot graph and calculates the shortest path from the entrance to the nearest available parking spot.

## ✨ Features
* **Shortest Path Routing:** Implements Dijkstra's algorithm to dynamically bypass occupied spots and route drivers to the closest free space.
* **Custom Graph Visualization:** Interactive 2D rendering using `Graphics2D`, displaying color-coded nodes (Entrance, Roads, Available/Occupied spots) and a live route overlay.
* **Standalone Execution:** Packaged as an executable `.jar` for cross-platform desktop usage.

## 🛠️ Architecture
* `Graph`, `Node`, `Edge`: Core data structures representing the parking lot topology.
* `Router`: Dijkstra-based pathfinding engine.
* `ParkingFrame` & `ParkingPanel`: Java Swing UI and custom rendering canvas.
* `Tests`: Simulation runner with complex multi-wing parking scenarios.
