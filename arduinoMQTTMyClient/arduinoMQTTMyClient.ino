    
    #include <DHT.h>

    int displayPin = 4;  //the Digital pin to control the switch on/off of the monitor
    int windlerPin = 5; //the digital pin attached to windler
    int alarmPin = 6; //digital alarm
    int thermoPin = 8; //the DIGITAL pin of the thermometer and hygro

    int ledPin = 13;
    int pinOnOff = 2;
    
    int photocellPin = 0;     // the cell and 10K pulldown are connected to a0

    boolean displayOnOff = false; //in the initial state is off, MORE light passes
    boolean windlerOnOff = false; //off
    boolean alarmOnOff = false;

    boolean onOff = true;
    boolean didWhois = false;

    int photocellReading;     // the analog reading from the sensor divider
    int thermoReading;
    int humidReading;

    String idTeca = "123456";
    String tipoTeca = "T";
    String idAnimale = "1";

    DHT dht(thermoPin, DHT11);

    void setup(void) {
    Serial.begin(9600);   

    pinMode(displayPin, OUTPUT);
    pinMode(windlerPin, OUTPUT);
    pinMode(alarmPin, OUTPUT);

    pinMode(ledPin, OUTPUT);
    pinMode(pinOnOff, INPUT_PULLUP);
    onOff = true;

    digitalWrite(displayPin, LOW);
    }
     
    void loop(void) {

      String inputSerial;
      while(Serial.available()) {
        
        inputSerial = Serial.readString();// read the incoming data as string

        if (inputSerial.indexOf("whoIs") >= 0) {
          doWhoIs();
        }    

        if(isThisTeca(inputSerial) && onOff) {
  
          //analyze the input and send the response back
          if(inputSerial.indexOf("readThemperature") >= 0) {
            readThemperature();
          }
          else if (inputSerial.indexOf("readHumidity") >= 0) {
            readHumidity();
          }
          else if (inputSerial.indexOf("readLight") >= 0) {
            readLight();
          }
          else if (inputSerial.indexOf("readWindler") >= 0) {
            readWindler();
          }
          else if (inputSerial.indexOf("readDisplay") >= 0) {
            readDisplay();
          }               
          else if (inputSerial.indexOf("readAlarm") >= 0) {
            readAlarm();
          }
          else if (inputSerial.indexOf("readAll") >= 0) {
            readAll();
          }          
          else if (inputSerial.indexOf("setWindler") >= 0) {
            if(isTrue(inputSerial)) {
              setWindler(true);
            }
            else {
              setWindler(false);  
            }
          }
          else if (inputSerial.indexOf("setDisplay") >= 0) {
            if(isTrue(inputSerial)) {
              setDisplay(true);
            }
            else {
              setDisplay(false);  
            }
          }
          else if (inputSerial.indexOf("setAlarm") >= 0) {
            if(isTrue(inputSerial)) {
              setAlarm(true);
            }
            else {
              setAlarm(false);  
            }
          }                               
              
        }

      }

      if(alarmOnOff) {
        tone(alarmPin, 1000, 30);
        delay(30);
        tone(alarmPin, 3000, 300);
        delay(300);

      }

      if(onOff == true)
        digitalWrite(ledPin, HIGH);
      else
        digitalWrite(ledPin, LOW); 
      
      if(digitalRead(pinOnOff) == LOW) {
        onOff = !onOff;
        if(!onOff) {
          digitalWrite(ledPin, LOW);
          doSwitchOff();
        }
        else {
          digitalWrite(ledPin, HIGH);
          didWhois = false;
        }
        delay(2000);
      }

      if(onOff)
        readAll();
      
      delay(1200);
    }



    void readThemperature() {
      thermoReading = dht.readTemperature();
      Serial.println(idTeca + " " + thermoReading);
    }

    void readHumidity() {
      humidReading = dht.readHumidity();
      Serial.println(idTeca + " " + humidReading);
    }

    void readLight() {//under 300 is considered full dark
      photocellReading = analogRead(photocellPin);   
      Serial.println(idTeca + " " + photocellReading);
    }

    void readWindler() {
      Serial.println(idTeca + " " + windlerOnOff);
    }

    void readDisplay() {
      Serial.println(idTeca + " " + displayOnOff);
    }

    void readAlarm() {
      Serial.println(idTeca + " " + alarmOnOff);
    }

    void readAll() {
      thermoReading = dht.readTemperature();
      humidReading = dht.readHumidity();
      photocellReading = analogRead(photocellPin);   

      Serial.println(idTeca + " " + thermoReading + " " + humidReading + " " + photocellReading + " " + alarmOnOff + " " + displayOnOff + " " + windlerOnOff + " " + tipoTeca + " " + idAnimale);
    }

    void doWhoIs() {//id teca, tipo animale, tipo teca
      if(!didWhois) {
        didWhois = true;
        Serial.println(idTeca + " " + tipoTeca + " " + idAnimale);
      }
    }

    void doSwitchOff() {
      Serial.println(idTeca);
    }
    
    void setWindler(boolean onoff) {
      if(onoff) {
        digitalWrite(windlerPin, HIGH);
        windlerOnOff = true;
      }
      else {
        digitalWrite(windlerPin, LOW);
        windlerOnOff = false;
      }
    }

    void setDisplay(boolean onoff) {//TODO: this function must enable the pin for 0.5 seconds and then return to 0
      if(onoff) {
        
        digitalWrite(displayPin, HIGH);
        delay(500);
        digitalWrite(displayPin, LOW);
        
        displayOnOff = true;
      }
      else {
        
        digitalWrite(displayPin, HIGH);
        delay(500);
        digitalWrite(displayPin, LOW);
        
        displayOnOff = false;
      }
    }

    void setAlarm(boolean onoff) {
      if(onoff) {
        digitalWrite(alarmPin, HIGH);
        alarmOnOff = true;
      }
      else {
        digitalWrite(alarmPin, LOW);
        alarmOnOff = false;
      }
    }

  boolean isThisTeca(String stringa) {
    if(stringa.indexOf(idTeca) >= 0)
      return true;
    else
      return false;
  }

  boolean isTrue(String stringa) {
    if(stringa.indexOf("true") >= 0)
      return true;
    else
      return false;
  }

