class Segment {
  float x, y, len, br;
  float fil, bl;
  Segment(float x, float y, float len, float bred) {
    this.x = x;
    this.y = y;
    this.len = len;
    this.br = bred;
  }

  void update(boolean yorn) {
    if (yorn == true) {
      this.fil = 1;
      this.bl = 0;
    } else {
      this.fil = 0;
      this.bl = 51;
    }
  }

  void render() {
    stroke(40);
    fill((255 * this.fil) + bl, 0 + bl, 0 + bl);
    rect(this.x, this.y, this.len, this.br);
  }
}
