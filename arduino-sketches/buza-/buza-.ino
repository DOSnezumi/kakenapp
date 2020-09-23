int i;

void setup() {
  // put your setup code here, to run once:

  for (i = 4; i <= 9; i++) {
    pinMode(i, OUTPUT);
  }
}

void loop() {
  // put your main code here, to run repeatedly:

  digitalWrite(4, HIGH);
  digitalWrite(7, HIGH);
}
