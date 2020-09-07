#include <ESP8266WiFi.h>
#include <time.h>

#define WIFI_SSID   "RT200NE-ADB16E"
#define WIFI_PASSWORD   "NTTRT200NE"
#define JST     3600*9

void setup() {
  Serial.begin(115200);
  delay(100);
  Serial.print("\n\nReset:\n");

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while(WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(500);
  }
  Serial.println();
  Serial.printf("Connected, IP address: ");
  Serial.println(WiFi.localIP());

  configTime( JST, 0, "ntp.nict.jp", "ntp.jst.mfeed.ad.jp");
}

void loop() {
  time_t t;
  struct tm *tm;
  static const char *wd[7] = {"Sun","Mon","Tue","Wed","Thr","Fri","Sat"};

  t = time(NULL);
  tm = localtime(&t);
  Serial.printf(" %04d/%02d/%02d(%s) %02d:%02d:%02d\n",
        tm->tm_year+1900, tm->tm_mon+1, tm->tm_mday,
        wd[tm->tm_wday],
        tm->tm_hour, tm->tm_min, tm->tm_sec);
  delay(1000);
}
