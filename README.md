# Vaccine-Ninja
Tool for booking vaccine slots on cowin

Run as `java -jar Vaccine-Ninja-jar-with-dependencies.jar config.properties`

Sample config.properties:
```properties
pollingIntervalSeconds=30
#Case Insensitive
districts=Kolkata, South 24 Parganas
minAge=18

#Valid subscribers = log, file, twilio
subscribers=log, file

#File Subscriber
subscriber.file.filePath=./results.txt

#Twilio Subscriber
subscriber.twilio.sid=
subscriber.twilio.authToken=
subscriber.twilio.messagingSID=
#Need to be added to trusted numbers from the twilio console
subscriber.twilio.phoneNumbers=
#Using default of 30mins, will ovverride later
#subscriber.twilio.rateLimiter.seconds=
```

Sample file: [here](src/main/resources/config.properties)