class Laser {
  constructor(spos, angle) {
    this.pos = createVector(spos.x, spos.y);
    this.vel = p5.Vector.fromAngle(angle);
    this.vel.mult(10);
    this.r = 4;
  }

  hits(ast) {
    let d = dist(this.pos.x, this.pos.y, ast.pos.x, ast.pos.y);
    return (d < ast.r);
  }

  out() {
    return (this.pos.x > width + this.r || this.pos.x < -this.r || this.pos.y > height + this.r || this.pos.y < -this.r);
  }

  update() {
    this.pos.add(this.vel);
  }

  render() {
    push();
    stroke(255);
    strokeWeight(this.r);
    point(this.pos.x, this.pos.y);
    pop();
  }
}