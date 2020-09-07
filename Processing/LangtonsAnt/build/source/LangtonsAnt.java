import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LangtonsAnt extends PApplet {

int[][] grid;
int x;
int y;

final int ANTUP = 0;
final int ANTRIGHT = 1;
final int ANTDOWN = 2;
final int ANTLEFT = 3;

int dir;

PImage ant;

public void setup() {
  // size(600, 600);
  
  grid = new int[width][height];
  ant = createImage(width, height, RGB);
  ant.loadPixels();
  for (int i = 0; i < ant.pixels.length; i++) {
    ant.pixels[i] = color(255);
  }
  ant.updatePixels();
  x = width / 2;
  y = height / 2;
  dir = ANTUP;
}

public void right() {
  dir++;
  if (dir > ANTLEFT) {
    dir = ANTUP;
  }
}

public void left() {
  dir--;
  if (dir < ANTUP) {
    dir = ANTLEFT;
  }
}

public void forward() {
  if (dir == ANTUP) {
    y--;
  } else if (dir == ANTRIGHT) {
    x++;
  } else if (dir == ANTDOWN) {
    y++;
  } else if (dir == ANTLEFT) {
    x--;
  }

  if (x > width - 1) {
    x = 0;
  } else if (x < 0) {
    x = width - 1;
  }
  if (y > height - 1) {
    y = 0;
  } else if (y < 0) {
    y = height - 1;
  }
}

public void draw() {
  background(255);

  ant.loadPixels();
  for (int n = 0; n < 500; n++) {

    int state = grid[x][y];

    if (state == 0) {
      right();
      grid[x][y] = 1;
    } else if (state == 1) {
      left();
      grid[x][y] = 0;
    }

    int col = color(255);
    if (grid[x][y] == 1) {
      col = color(0);
    }
    int pix = x + y * ant.width;
    ant.pixels[pix] = col;
    forward();
  }
  ant.updatePixels();

  image(ant, 0, 0);
  //loadPixels();
  //for (int i = 0; i < width; i++) {
  //  for (int j = 0; j < height; j++) {
  //    int pix = i + width * j;
  //    if (grid[i][j] == 0) {
  //      pixels[pix] = color(255);
  //    } else {
  //      pixels[pix] = color(0);
  //    }
  //  }
  //}
  //updatePixels();
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LangtonsAnt" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
