class Ship {
  constructor() {
    this.pos = createVector(width / 2, height / 2);
    this.vel = createVector();
    this.head = 0;
    this.rotation = 0;
    this.r = 16;
    this.isBoosting = false;
  }

  hits(ast) {
    let d = dist(this.pos.x, this.pos.y, ast.pos.x, ast.pos.y);
    return (d < this.r + ast.r);
  }

  boosting(b) {
    this.isBoosting = b;
  }

  thrust() {
    let force = p5.Vector.fromAngle(this.head);
    this.vel.add(force);
  }

  edges() {
    if (this.pos.x > width + this.r) {
      this.pos.x = -this.r;
    } else if (this.pos.x < -this.r) {
      this.pos.x = width + this.r;
    }

    if (this.pos.y > height + this.r) {
      this.pos.y = -this.r;
    } else if (this.pos.y < -this.r) {
      this.pos.y = height + this.r;
    }
  }

  setRot(a) {
    this.rotation = a;
  }

  update() {
    if (this.isBoosting) {
      this.thrust();
    }

    this.pos.add(this.vel);
    this.vel.mult(0.95);
  }

  render() {
    push();
    translate(this.pos.x, this.pos.y);
    rotate(this.head + PI / 2);
    triangle(-this.r, this.r, this.r, this.r, 0, -this.r);
    pop();
  }

  turn() {
    this.head += this.rotation;
  }
}