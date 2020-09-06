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

  void flock(ArrayList<Boid> boids) {
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

  PVector separation(ArrayList<Boid> boids) {
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

  PVector cohesion(ArrayList<Boid> boids) {
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

  PVector align(ArrayList<Boid> boids) {
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

  void addForce(PVector force) {
    acc.add(force);
  }

  void edges() {
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

  void update() {
    vel.add(acc);
    pos.add(vel);
    vel.limit(maxSpeed);
    acc.mult(0);
  }

  void render() {
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
