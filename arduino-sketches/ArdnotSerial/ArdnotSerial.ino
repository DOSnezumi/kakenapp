// https://scrapbox.io/kadaikeUnkyuu/

#define boundary 0  //アナログ入力における電圧の境界
#define ko 1

int i,j, tag, flag = 0;
float v_bef = 0, v_aft = 0;

int searchTag(void);

void setup() {

  //ボーレートの設定をしてね。詳細は http://discexuno.wp.xdomain.jp/arduino/arduino_and_esp8266_1/

  pinMode(2, INPUT);  //esp12
  for(i=4;i<=9;i++){
    pinMode(i, OUTPUT);   // 2,4~9ピンをOUTPUTに設定
  }

  pinMode(3, OUTPUT); //esp13
  for(i=10;i<=15;i++){    // 3,10~13,A0(D14),A1(D15)をINPUTに設定
    pinMode(i, INPUT);
  }

  for(i=4;i<=9;i++){
    digitalWrite(i, HIGH);
  }

  Serial.begin(9600);

  pinMode(17, INPUT);     //17=A03→スイッチ用

  do{
    i = digitalRead(17);  // スイッチ押すまで待とうね
  }while(i == LOW);

  delay(2000);

  
  int fromESP[ko],kensaku,val;

  digitalWrite(3, HIGH);
  
  delay(1000);

  fromESP[0] = digitalRead(2); //こっから子機探す
  Serial.println(fromESP[0]);
 
  delay(1000);
    
  if(fromESP[0] == HIGH){
        
    j = analogRead(2);          //今の受信機の値を読む
    v_bef = j * 5.0 / 1023.0;       //arduinoの読んだ値を電圧にかえる   
    digitalWrite(7, LOW);           //子機発振1回目
    digitalWrite(5, LOW);
    delay(240);    
    j = analogRead(2);              //子機を発振させた後の受信機の値を読む
    digitalWrite(7, HIGH);
    digitalWrite(5, HIGH);          //子機発振2回目
    v_aft = j * 5.0 / 1023.0;
    v_bef = v_bef + 0.3;

    Serial.println(v_bef);
    Serial.println(v_aft);
          
    if(v_aft < v_bef){              //計測後が計測前より0.3V低かったら
      digitalWrite(3, LOW);
      delay(2000);
    }
  }
}

void loop(){

  int buzzerSound, buzzerStop[3];

  buzzerSound = digitalRead(2); //こっから子機探す

  // ブザーを鳴らす
  if(buzzerSound == HIGH){
    digitalWrite(4, LOW);
    digitalWrite(7, LOW);
  }

  // 子機のスイッチ押されたら音出すの止めるよ
  buzzerStop[0] = digitalRead(13);
  buzzerStop[1] = digitalRead(14);
  buzzerStop[2] = digitalRead(15);
  if(buzzerStop[0] == LOW){
    digitalWrite(4, HIGH);
    digitalWrite(7, HIGH);
  }

}

