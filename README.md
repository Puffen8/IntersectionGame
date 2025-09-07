# Intersection

A 2D infinite game where the player scores by avoiding vehicle collisions.  
Built with a simple game loop and a responsive Swing UI.

![Java](https://img.shields.io/badge/Java-22-blue) ![2D Game](https://img.shields.io/badge/Type-2D%20Game-green)

## Features
- Addictive gameplay loop: scoring and menu system.
- Responsive Swing UI with basic animation.
- Mouse input handling.

## Tech stack
- Java (JDK 22)
- Java Swing (UI)

## Why this repo
- Shows object-oriented design, separation of concerns, and event-driven UI.
- Took an idea to a finished product.
- Demonstrates a simple yet complete game loop with Java Swing.

## Requirements
- OpenJDK / Oracle JDK 22 installed
- Recommended IDE: IntelliJ IDEA

## Quick start

```bash
# 1) Clone the repository
git clone git@github.com:Puffen8/IntersectionGame.git
cd IntersectionGame

# 2a) Compile
javac -d bin src/*.java

# Run
java -cp bin:res Main     # Linux/Mac
java -cp bin;res Main     # Windows (note the semicolon)

