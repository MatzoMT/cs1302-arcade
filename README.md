# CSCI 1302 - Arcade

This document contains the description for the `cs1302-arcade` project
assigned to the students in the Fall 2017 CSCI 1302 classes
at the University of Georgia.

**Please read the entirety of this file before
beginning your project.** 

## Due Dates

* Team Application is due by **SUN 2017-11-12 @ 11:55 PM EST**.
* Team Push due by **FRI 2017-11-17 @ 11:55 PM EST**.
* Team Push due by **TUE 2017-11-28 @ 11:55 PM EST**.
* Team Push due by **TUE 2017-12-05 @ 11:55 PM EST**.
* Final Team Push and Nike Submission by **DUE TUE 2017-12-12 @ 08:00 AM EST**. 

## Academic Honesty

You agree to the Academic Honesty policy as outlined in the course syllabus, 
course website, and your team application. Furthermore, you must adhere to
the copyright notice and licensing information at the bottom of this document.

## Updates

Updates to the skeleton project will be posted here. Please check this section 
periodically. Instructions to incorporate changes into your local repository
are provided [here](#getting-updates-from-skeleton-project).

## Project Description

Your goal is to implement a single GUI application in Java using JavaFX 8 that 
provides an arcade with your own Java+JavaFX implementations of at least two 
playable games. Here is an example of what your program might look like
when it first launches:

![screenshot1](https://i.imgur.com/JSLOIpF.png)

Your team must choose one game from each of the following groups:

* **Group 1**
  * [Breakout](https://en.wikipedia.org/wiki/Breakout_(video_game))
  * [Tetris](https://en.wikipedia.org/wiki/Tetris) 
  * [Space Invaders](https://en.wikipedia.org/wiki/Space_Invaders)
* **Group 2**
  * [Minesweeper](https://en.wikipedia.org/wiki/Minesweeper_(video_game)) *(requires recursion)*
  * [Reversi](https://en.wikipedia.org/wiki/Reversi)
  * [American Checkers](https://en.wikipedia.org/wiki/Draughts)

You have a lot of flexibility with regard to the visuals of your games. As long
as the functional requirements are met and the game mechanics are easily
recognizable, you are free to make each game look and feel however you want
(please keep it appropriate).
The general functional requirements for each group are provided later in this document

Part of software development is being given a goal but not necessarily being 
given instruction on all of the details needed to accomplish that goal. For example,
even though working with things like keyoard and mouse-related events 
haven't been explicitly covered in class, you are going to need to lookup how to do 
these things in order to complete this project. Starter code and a generously 
helpful [FAQ](#faq) are provided.

This project is also designed to help you better understand the usefulness of good
class design. While you can technically write your entire JavaFX-based
GUI application entirely in the `start` method, this will make your code messy, 
hard to read, possibly redundant, and likely more prone to errors.
Before you write any code, you should plan out your application's scene graph
(i.e., the containment hierarchy), and design custom components as needed.
If you find that you are writing a lot of code related to a specific component
(e.g., setting styling, adding event handlers, etc.), then it's probably 
a good idea to make a custom version of that component in order to reduce
clutter. You are strongly encouraged to consider swapping out multiple scenes
for this project. 

### Learning Outcomes

* Plan, design, implement, test, debug, and deploy a complete object-oriented
  software solution in Linux/Unix environment (1302-LO1)
* Utilitze inheritance and polymorphism in a software project (1302-LO3-LO4).
* Develop a GUI for a software project (1302-LO7).
* Implement exception-handling in a software project (1302-LO8).
* Understand and apply langauge basics using an OOP language (1302-LO11).

### Functional Requirements

* **Main Application Requirements (10 points):** The main part of your 
  application needs to fulfill the following functional requirements:

  * *Game List (5 points):* Your application should present
  the user with a visual list of available games. Starting a game should either
  swap the scene in the current stage or create an application modal stage on
  which to display the game's scene graph.
  
  * *Multiple Games per Execution (5 points):* Your application should allow
  users to exit one game (without exiting the entire application) and start
  the same game again (with its state reset) or start the other game. 

* **Group 1 Game Requirements (45 points):** Your Group 1 game implementation
  needs to fulfill the following functional requirements:

  * *UI, Mechanics, and Scoring (40 points):* The user interface and game mechanics
    must be easily recognizable and consistent with traditional implementations of
    the game you chose (see the Wikipedia link for more information). Your game
    must provide a consistent scoring mechanism and display the score or scores to
    the user somewhere in the user interface. Furthermore, a game in this category
    must support multiple levels of difficulty that a user will encounter as they
    play the game. The first level should be designed so that it is easily attainable
    by the grader. The current level of difficulty should always be visible to
    the user somewhere in the user interface.

  * *Controls (5 points):* You are required to provide keyboard controls 
    for a game in this group. If anything is not intuitive, then you need to let
    the user know before the game starts. 

* **Group 2 Game Requirements (45 points):** Your Group 2 game implementation
  needs to fulfill the following functional requirements:

  * *UI, Mechanics, and Scoring (40 points):* The user interface and game mechanics
    must be easily recognizable and consistent with traditional implementations of
    the game you chose (see the Wikipedia link for more information). Your game
    must provide a consistent scoring mechanism and display the score or scores to
    the users somewhere in the user interface. 

  * *Controls (5 points):* You are required to provide intuitive mouse controls 
    for a game in this group. If anything is not intuitive, then you need to let
    the user know before the game starts. 
    
  * *Note:* You are only required to provide a human player vs. human player 
    mode for a game in this category. Feel free to add support for computer 
    players, however, such support will not contribute to your grade. 

* **Extra Credit 1 (5 points):** Add some kind of animated intro to your application.
  One potential way to accomplish this is by using a separate scene for your intro.
  This intro should include, in addition to some kind of animated element, the 
  application title (i.e., `cs1302-arcade`), your team name, and the name of each
  team member. If you want the grader to check for this requirement, then please
  make sure it is included in your last update to `REFLECTION.md`.

* **Extra Credit 2 (5 points):** Add a [high score table](https://en.wikipedia.org/wiki/Score_(game)#High_score)
  to your application, accessible via your application's menu (menu bar or otherwise).
  The table needs to actually keep track of the high scores for each game and include
  player initials. This may involve modifications in other areas of your application to
  accomodate this. In order to receive full credit for this extra credit functional
  requirement, the high score table must persist over time and over separate executions
  of your application. If you want the grader to check for this requirement, then please
  make sure it is included in your last update to `REFLECTION.md`.

### Non-Functional Requirements

Points indicated for non-functional requirements are not added to the grade total
if satisfied but are subtracted from the grade total if not satisfied.

* **User-Friendly (5 points):** Except for reasonable delays resulting from X forwarding, 
  your application should not hang/freeze or crash during execution.
  
* **Attribution (5 points):** Proper attribution should be given for all assets (e.g.,
  art, sound, music, etc.) that is not authored by members of your project team. 
  You may consider making an `ATTRIBUTIONS.md` file that contains this information. 

* **Javadoc Documentation (5 points):** Each method and class needs to be documented
  using Javadoc comments. If a method overrides an inheritted method that is
  already documented, then that method only needs a Javadoc comment if the
  implementation differs from the existing documentation. In such cases, the use of
  `@inheritDoc` is encouraged. 

* **In-line Documentation (5 points):** Code blocks should be adequately documented
  using in-line comments. This is especially necessary when a block of code
  is not immediately understood by a reader (e.g., the grader). 
  
* **Reflection Updates (20 points; 5 points each):** Before each deadline mentioned
  towards the beginning of this document (except the application deadline), you
  will need to update your project's `REFLECTION.md` file to include a new 
  section describing: i) what work has been done; ii) what work do you plan
  to complete before the next deadline; and what problems, if any, you have
  encountered related to this project. These changes must be committed and pushed 
  to your team repository before each deadline for them to count.
  
* **Team Application Agreement Adherance (50 points):** You must adhere to the
  agreement in the Pair Programming Team Application you submitted for this
  project. Deviations will very likely result in this non-functional requirement
  being unsatisfied. If you have any problems with regard to this requirement, 
  then please contact the CSCI 1302 Support Team by sending a private post to 
  "Instructors" via the course Piazza as soon as possible.

### Grading

The graders will compile and run your code on Nike using Maven (likely via
the provided `Makefile`). They will test
each of the functional and non-functional requirements and total up the points
earned. This project is worth 100 points. Students have an opportunity to earn
an additional 10 points via extra credit. 

## Setup and Submission

### Setting up Your Local Repository

Each team member will need to perform the following instructions on their
Nike account. You will need the following information to get started:

* **`SKELETON_REPO_URL`:** `git@github.com:cs1302uga/cs1302-arcade.git`
* **`TEAM_REPO_URL`:** *provided to you by the support team.*

On Nike, execute the following commands to create an empty, local repository for 
your poject and connect it with the two remote repositories mentioned above.
These instructions will place the files into sub-directory called `cs1302-arcade` 
within your present working directory:

1. Create a new local repository:
   ```
   $ mkdir cs1302-arcade
   $ cd cs1302-arcade
   $ git init
   ```

2. Add the remote repositories (replace `SKELETON_REPO_URL` and `TEAM_REPO_URL` 
   appropriately):
   ```
   $ git remote add skeleton SKELETON_REPO_URL
   $ git remote add team TEAM_REPO_URL
   ```
   
3. Explicitly `pull` skeleton code from `skeleton/master`:
   ```
   $ git pull skeleton master
   ```
   
4. *NOTE*: To set the upstream for your local `master` branch to `team/master` so that 
   `push`, `fetch`, and `pull` connect with your team repository by default, make sure 
   that you use `-u` the first time you call the command (after grabbing the skeleton
   code). For example:
   ```
   $ git push -u team master
   ```   

5. At this point, you should be good to go! Changes won't appear on your team's
   GitHub repository website until you our your partner `push` to it. 
   
If you have any problems with any of these procedures, then please contact the CSCI
1302 Support Team by sending a private post to "Instructors" via the course 
Piazza as soon as possible.

### Getting Updates from Skeleton Project

If any updates to the skeleton project are announced by your instructor, you can
`fetch` those changes into your local repository by changing into your project 
directory on Nike and issuing the following command:
```
$ git fetch skeleton master
```

Once you're ready to merge the fetched skeleton project changes into your local 
`master` branch, you might issue the following commands:
```
$ git checkout master
$ git merge skeleton/master
```

Remember, you can comine `fetch` and `merge` using `pull`, if desired:
```
$ git checkout master
$ git pull skeleton master
```

If you have any problems with any of these procedures, then please contact the CSCI
1302 Support Team by sending a private post to "Instructors" via the course 
Piazza as soon as possible.

### Submission Instructions

You will be submitting your project via Nike before the deadline indicated
near the top of this document. Make sure your project files are on 
`nike.cs.uga.edu`. Change into the parent directory of your project directory. 
If you've followed the instructions provided in this document, then the name 
of your project directory is likely `cs1302-arcade`. 
While in your project's parent directory, execute the following command: 
```
$ submit cs1302-arcade cs1302a
```

It is also a good idea to email a copy to yourself. To do this, simply execute 
the following command, replacing the email address with your email address:
```
$ tar zcvf cs1302-arcade.tar.gz cs1302-arcade
$ mutt -s "[cs1302] cs1302-arcade" -a cs1302-arcade.tar.gz -- your@email.com < /dev/null
```

If you have any problems submitting your project, then please contact the CSCI
1302 Support Team by sending a private post to "Instructors" via the course 
Piazza as soon as possible. 

# Supplement

## Useful Links

### JavaFX 8

* [Package: `javafx`](https://docs.oracle.com/javase/8/javafx/api/toc.htm)
* [JavaFX CSS Reference Guide](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html)
* [Styling UI Controls with CSS](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/apply-css.htm)

## FAQ

Below are some frequently asked questions related to this project.

1. **How do I make a code snippet execute repeatedly with a delay between executions?**

   The easiest way to accomplish this in a JavaFX application is using the
   [`Timeline`](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html) 
   and [`KeyFrame`](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/KeyFrame.html) 
   classes. Here is an example that prints the current time (using 
   [`LocalTime`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)) to 
   standard output every two (2) seconds (specified using
   [`Duration`](https://docs.oracle.com/javase/8/javafx/api/javafx/util/Duration.html)), indefinitely:
   ```java
   EventHandler<ActionEvent> handler = event -> System.out.println(LocalTime.now());
   KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), handler);
   TimeLine timeline = new Timeline();
   timeline.setCycleCount(Timeline.INDEFINITE);
   timeline.getKeyFrames().add(keyFrame);
   timeline.play();
   ```
   The `Timeline` object also has a `pause` method to pause the execution of the timeline.

2. **How do I make my game run at *x* frames per second (e.g., 60 FPS)?**

   If you are using a single `Timeline` object for your main game loop, then duration of all
   key frames in the main game loop should sum to exactly `1000 / x` ms (e.g., 
   `Duration.ofMillis(1000 / 60)`). 
   The easiest way to ensure this is to have only one key frame with that duration.

3. **What is a good GUI container for games?**

   You already know that you can setup your scene graph however you want by using and nesting
   different types of layour panes. If you need explicit control over the `x` and `y` positions
   of some nodes, then I reccommend using the 
   [`Group`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Group.html) class.
   Since `Group` is subsumed by `Node`, you should be able to add a `Group` object anywhere in
   your scene graph just like any other node.
   
4. **How do I setup event handlers related to mouse and keyboard events?**

   Look at the Javadoc documentation for the 
   [`Node`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html) class,
   specifically the methods that start with `setOnMouse` and `setOnKey`, to see the 
   different types of mouse and keyboard events you can handle. Here is a simple example 
   that prints the current time (using 
   [`LocalTime`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)) to 
   standard output when a user clicks on an `ImageView` object (referenced by `iv`) that 
   is assumed to be visible within the current scene graph:
   ```java
   iv.setOnMouseClicked(event -> System.out.println(LocalTime.now()));
   ```
   Remember, you can setup these event handlers for any node in the scene graph, including
   the containers.
   
5. **How can I detect if two nodes in my scene collide?**

   Look at the "Bounding Rectangles" section in the Javadoc documentation for the 
   [`Node`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html) class.
   The getter methods described in that section return references to 
   [`Bounds`](https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html)
   objects, which likely contain useful information regarding the whereabouts of
   node. I would reccommend taking a very close look at the methods in that class.
   
6. **How do I add sound?**

   While JavaFX does support audio playback of various formats, this feature is not
   currently available over X11 forwarding from Nike. If you incorporate audio into
   your application, then take measures to properly deal with the unchecked exceptions
   that might get thrown if audio playback is not available. 
   
7. **How do I make basic shapes appear?**

   Look at the classes in the 
   [`javafx.scene.shape`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html)
   package. 

Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; 2017 Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
