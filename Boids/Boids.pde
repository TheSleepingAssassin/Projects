import controlP5.*;

ArrayList<Boid> boids;
ControlP5 alignment;
// 0.25
float Alignment = 0.25;
ControlP5 cohete;
// 0.2
float Cohesion = 0.2;
ControlP5 separate;
// 0.3
float Separation = 0.3;
float distt = 170;

void setup() {
  fullScreen();
  boids = new ArrayList<Boid>();
  alignment = new ControlP5(this);
  cohete = new ControlP5(this);
  separate = new ControlP5(this);
  alignment.addSlider("Alignment").setPosition(0, height - 10).setRange(0, 5);
  cohete.addSlider("Cohesion").setPosition(distt, height - 10).setRange(0, 5);
  separate.addSlider("Separation").setPosition(distt * 2, height - 10).setRange(0, 5);
}

void draw() {
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
