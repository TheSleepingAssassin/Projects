Display d;
int i = 0;
// Display d1, d2, d3, d4, d5, d6, d7, d8, d9;

void setup() {
  size(360, 600);
  frameRate(10);
  background(51);
  stroke(255);
  text("Click the mouse to change the digit", 1, 10);
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

void mousePressed() {
  if (i < 10) {
    d.updatei(i++);
  } else {
    i = 0;
  }
}

void draw() {
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
