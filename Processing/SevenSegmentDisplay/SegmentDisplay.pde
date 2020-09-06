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

  void updatei(int num) {
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

  void renderi() {
    u.render();
    ru.render();
    rd.render();
    dow.render();
    ld.render();
    lu.render();
    m.render();
  }
}
