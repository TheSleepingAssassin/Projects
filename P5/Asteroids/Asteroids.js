class Asteroid {
  constructor(pos, r) {
    if (pos) {
      this.pos = pos.copy();
    } else {
      this.pos = createVector(random(width), random(height));
    }

    if (r) {
      this.r = r * 0.5;
    } else {
      this.r = random(16, 70);
    }

    this.vel = p5.Vector.random2D();
    this.total = random(5, 15);
    this.offset = [];
    for (let i = 0; i < this.total; i++) {
      this.offset[i] = random(-5, 5);
    }
  }

  genAst() {
    let asts = [];
    asts.push(new Asteroid(this.pos, this.r));
    asts.push(new Asteroid(this.pos, this.r));
    return asts;
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

  update() {
    this.pos.add(this.vel);
  }

  render() {
    push();
    translate(this.pos.x, this.pos.y);
    stroke(255);
    noFill();
    beginShape();
    for (let i = 0; i < this.total; i++) {
      let a = map(i, 0, this.total, 0, TWO_PI);
      let r = this.r + this.offset[i];
      let x = r * cos(a);
      let y = r * sin(a);
      vertex(x, y);
    }
    endShape(CLOSE);
    pop();
  }
}