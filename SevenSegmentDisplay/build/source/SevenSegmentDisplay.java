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

public class SevenSegmentDisplay extends PApplet {

Display d;
int i = 0;
// Display d1, d2, d3, d4, d5, d6, d7, d8, d9;

public void setup() {
  
  frameRate(10);
  background(51);
  stroke(255);
  text("Click the mouse to change the digit", 10, 10);
  float r = 40;
  d = new Display(r, r, 200, 40);
  // d1 = new Display(r * 5, 10, 20, 10);
  // d2 = new Display(r * 10, 10, 20, 10);
  // d3 = new Display(r * 15, 10, 20, 10);
  // d4 = new Display(r * 20, 10, 20, 10);
  // d5 = new Display(r * 25, 10, 20, 10);
  // d6 = new Display(r * 30, 10, 20, 10);
  // d7 = new Display(r * 35, 10, 20, 10);
  // d8 = new Display(r * 40, 10, 20, 10);
  // d9 = new Display(r * 45, 10, 20, 10);
  d.updatei(0);
}

public void mousePressed() {
  if (i < 10) {
    d.updatei(i++);
  } else {
    i = 0;
  }
}

public void draw() {
  d.renderi();
  // d1.renderi();
  // d1.updatei(1);
  // d2.renderi();
  // d2.updatei(2);
  // d3.renderi();
  // d3.updatei(3);
  // d4.renderi();
  // d4.updatei(4);
  // d5.renderi();
  // d5.updatei(5);
  // d6.renderi();
  // d6.updatei(6);
  // d7.renderi();
  // d7.updatei(7);
  // d8.renderi();
  // d8.updatei(8);
  // d9.renderi();
  // d9.updatei(9);
}
class Segment {
  float x, y, len, br;
  float fil, bl;
  Segment(float x, float y, float len, float bred) {
    this.x = x;
    this.y = y;
    this.len = len;
    this.br = bred;
  }

  public void update(boolean yorn) {
    if (yorn == true) {
      this.fil = 1;
      this.bl = 0;
    } else {
      this.fil = 0;
      this.bl = 51;
    }
  }

  public void render() {
    stroke(40);
    fill((255 * this.fil) + bl, 0 + bl, 0 + bl);
    rect(this.x, this.y, this.len, this.br);
  }
}
class Display {
  Segment u, ru, rd, dow, ld, lu, m;
  float len, br;
  Display(float x, float y, float len, float br) {
    float d = 5;
    u = new Segment(x + br, y, len - d, br - d);
    ru = new Segment(x + br + len, y + br, br - d, len - d);
    rd = new Segment(x + br + len, y + br + len + br, br - d, len - d);
    dow = new Segment(x + br, y + br + ((len * 2) + br), len - d, br - d);
    ld = new Segment(x, y + br + len + br, br - d, len - d);
    lu = new Segment(x, y + br, br - d, len - d);
    m = new Segment(x + br, y + br + len, len - d, br - d);
  }

  public void updatei(int num) {
    if (num == 0) {
      u.update(true);
      ru.update(true);
      rd.update(true);
      dow.update(true);
      ld.update(true);
      lu.update(true);
      m.update(false);
    } else if (num == 1) {
      ru.update(true);
      rd.update(true);
      dow.update(false);
      u.update(false);
      ld.update(false);
      lu.update(false);
      m.update(false);
    } else if (num == 2) {
      u.update(true);
      ru.update(true);
      m.update(true);
      ld.update(true);
      dow.update(true);
      lu.update(false);
      rd.update(false);
    } else if (num == 3) {
      u.update(true);
      ru.update(true);
      m.update(true);
      rd.update(true);
      dow.update(true);
      lu.update(false);
      ld.update(false);
    } else if (num == 4) {
      lu.update(true);
      m.update(true);
      ru.update(true);
      rd.update(true);
      u.update(false);
      ld.update(false);
      dow.update(false);
    } else if (num == 5) {
      u.update(true);
      lu.update(true);
      m.update(true);
      rd.update(true);
      dow.update(true);
      ru.update(false);
      ld.update(false);
    } else if (num == 6) {
      u.update(true);
      lu.update(true);
      ld.update(true);
      m.update(true);
      rd.update(true);
      dow.update(true);
      ru.update(false);
    } else if (num == 7) {
      u.update(true);
      ru.update(true);
      rd.update(true);
      ld.update(false);
      lu.update(false);
      dow.update(false);
      m.update(false);
    } else if (num == 8) {
      u.update(true);
      ru.update(true);
      rd.update(true);
      dow.update(true);
      ld.update(true);
      lu.update(true);
      m.update(true);
    } else if (num == 9) {
      u.update(true);
      lu.update(true);
      ru.update(true);
      m.update(true);
      rd.update(true);
      ld.update(false);
      dow.update(false);
    }
  }

  public void renderi() {
    u.render();
    ru.render();
    rd.render();
    dow.render();
    ld.render();
    lu.render();
    m.render();
  }
}
  public void settings() {  size(360, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SevenSegmentDisplay" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
