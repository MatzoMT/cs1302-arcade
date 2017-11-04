# CSCI 1302 - Arcade

This document contains the description for the `cs1302-arcade` project
assigned to the students in the Fall 2017 CSCI 1302 classes
at the University of Georgia.

**Please read the entirety of this file before
beginning your project.** 

## Due Dates

* Team Application is due by **SUN 2017-11-12 @ 11:55 PM EST**.
* Team Push due by **FRI 2017-11-17 @ 11:55 PM EST**.
* Team Push due by **FRI 2017-12-01 @ 11:55 PM EST**.
* Team Push due by **FRI 2017-12-08 @ 11:55 PM EST**.
* Final Team Push and Nike Submission by **DUE TUE 2017-12-12 @ 08:00 AM EST**. 

## Academic Honesty

You agree to the Academic Honesty policy as outlined in the course syllabus, 
course website, and your team application. Furthermore, you must adhere to
the copyright notice and licensing information at the bottom of this document.

## Updates

Updates to the skeleton project will be posted here. Please check this section 
periodically. Instructions to incorporate changes into your local repository
are provided [here]().

* TODO 

## Project Description

Your goal is to implement a GUI application in Java using JavaFX 8 that provides
an arcade with multiple, playable games.

Part of software development is being given a goal but not necessarily being 
given instruction on all of the details needed to accomplish that goal. For example,
even though working with things like keyoard and mouse-related events 
haven't been covered in class, you are going to need to lookup how to do these things 
in order to complete this project. Starter code and a generously helpful [FAQ](#faq) 
are provided.

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

* TODO

### Functional Requirements

* **Main Requirements (100 points):** TODO

* **Extra Credit 1 (5 points):** TODO

* **Extra Credit 2 (5 points):** TODO

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

### Grading

The graders will compile and run your code on Nike using Maven. They will test
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
   
3. Set the upstream for your local `master` branch to `team/master` so that `push`, 
   `fetch`, and `pull` connect with your team repository by default:
   ```
   $ git branch --set-upstream master team/master
   ```
   
4. Explicitly `pull` skeleton code from `skeleton/master`:
   ```
   $ git pull skeleton master
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

1. **TODO**

Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; 2017 Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
