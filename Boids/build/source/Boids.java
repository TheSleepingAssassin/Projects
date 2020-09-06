import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Boids extends PApplet {



ArrayList<Boid> boids;
ControlP5 alignment;
// 0.25
float Alignment = 0.25f;
ControlP5 cohete;
// 0.2
float Cohesion = 0.2f;
ControlP5 separate;
// 0.3
float Separation = 0.3f;
float distt = 170;

public void setup() {
  
  boids = new ArrayList<Boid>();
  alignment = new ControlP5(this);
  cohete = new ControlP5(this);
  separate = new ControlP5(this);
  alignment.addSlider("Alignment").setPosition(0, height - 10).setRange(0, 5);
  cohete.addSlider("Cohesion").setPosition(distt, height - 10).setRange(0, 5);
  separate.addSlider("Separation").setPosition(distt * 2, height - 10).setRange(0, 5);
}

public void draw() {
  background(0);
  for (Boid b : boids) {
    b.flock(boids);
    b.render();
    b.update();
    b.edges();
  }
  if (mousePressed) {
    if (mouseY < height - 10) {
      boids.add(new Boid(new PVector(mouseX, mouseY)));
    }
  }
  if (boids.size() > 400) {
    background(255, 0, 0);
    noLoop();
  }
}
class Boid {
  PVector pos, vel, acc;
  float maxForce, maxSpeed, weight, r;
  Boid(PVector pos_) {
    pos = pos_;
    vel = PVector.random2D();
    vel.setMag(2);
    acc = new PVector();
    maxForce = 1;
    maxSpeed = 4;
    weight = 8;
    r = 4;
  }

  public void flock(ArrayList<Boid> boids) {
    PVector align = align(boids);
    PVector cohesion = cohesion(boids);
    PVector separation = separation(boids);

    align.mult(Alignment);
    cohesion.mult(Cohesion);
    separation.mult(Separation);

    addForce(align);
    addForce(cohesion);
    addForce(separation);
  }

  public PVector separation(ArrayList<Boid> boids) {
    int perception = 50;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(pos.x, pos.y, other.pos.x, other.pos.y);
      if (other != this && d < perception) {
        PVector diff = PVector.sub(pos, other.pos);
        diff.div(d);
        steering.add(diff);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.setMag(maxSpeed);
      steering.sub(vel);
      steering.limit(maxForce);
    }
    return steering;
  }

  public PVector cohesion(ArrayList<Boid> boids) {
    int perception = 100;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(pos.x, pos.y, other.pos.x, other.pos.y);
      if (other != this && d < perception) {
        steering.add(other.pos);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.sub(pos);
      steering.setMag(maxSpeed);
      steering.sub(vel);
      steering.limit(maxForce);
    }
    return steering;
  }

  public PVector align(ArrayList<Boid> boids) {
    int perception = 100;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(pos.x, pos.y, other.pos.x, other.pos.y);
      if (other != this && d < perception) {
        steering.add(other.vel);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.setMag(maxSpeed);
      steering.sub(vel);
      steering.limit(maxForce);
    }
    return steering;
  }

  public void addForce(PVector force) {
    acc.add(force);
  }

  public void edges() {
    if (pos.x > width + weight) {
      pos.x = -weight;
    }
    if (pos.x < -weight) {
      pos.x = width + weight;
    }
    if (pos.y > height + weight) {
      pos.y = -weight;
    }
    if (pos.y < -weight) {
      pos.y = height + weight;
    }
  }

  public void update() {
    vel.add(acc);
    pos.add(vel);
    vel.limit(maxSpeed);
    acc.mult(0);
  }

  public void render() {
    float theta = vel.heading() + radians(90);

    stroke(255);
    fill(200, 100);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(theta);
    beginShape(TRIANGLES);
    vertex(0, -r * 2);
    vertex(-r, r * 2);
    vertex(r, r * 2);
    endShape();
    popMatrix();
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Boids" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
