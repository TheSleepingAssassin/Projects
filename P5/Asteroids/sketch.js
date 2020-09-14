let s;
let asts = [];
let lasers = [];

function setup() {
  createCanvas(windowWidth, windowHeight);
  s = new Ship();
  noStroke();
  for (let i = 0; i < 10; i++) {
    asts.push(new Asteroid());
  }
}

function keyReleased() {
  if (keyCode == RIGHT_ARROW || keyCode == LEFT_ARROW) {
    s.setRot(0);
  }

  if (keyCode == UP_ARROW) {
    s.boosting(false);
  }
}

function keyPressed() {
  if (key == ' ') {
    lasers.push(new Laser(s.pos, s.head));
  } else if (keyCode == RIGHT_ARROW) {
    s.setRot(0.1);
  } else if (keyCode == LEFT_ARROW) {
    s.setRot(-0.1);
  } else if (keyCode == UP_ARROW) {
    s.boosting(true);
  }
}

function draw() {
  background(51);

  for (let i = lasers.length - 1; i >= 0; i--) {
    lasers[i].render();
    lasers[i].update();
    if (lasers[i].out()) {
      lasers.splice(i, 1);
    } else {
      for (let j = asts.length - 1; j >= 0; j--) {
        if (lasers[i].hits(asts[j])) {
          if (asts[j].r > 30) {
            let newAst = asts[j].genAst();
            asts = asts.concat(newAst);
          }

          asts.splice(j, 1);
          lasers.splice(i, 1);
          break;
        }
      }
    }
  }

  s.render();
  s.update();
  s.turn();
  s.edges();

  for (let a of asts) {
    a.render();
    a.update();
    a.edges();
    if (s.hits(a)) {
      background(255, 0, 0);
      textSize(50);
      textAlign(CENTER);
      fill(255);
      stroke(255);
      text("Wasted", width / 2, height / 2);
      noLoop();
    }
  }

  if (asts.length == 0) {
    background(0, 255, 0);
    textSize(50);
    textAlign(CENTER);
    fill(255);
    stroke(255);
    text("WIN!", width / 2, height / 2);
    noLoop();
  }
}