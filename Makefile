setup:
	./gradlew wrapper --gradle-version 7.1

clean:
	./gradlew clean

install:
	./gradlew clean install

test:
	./gradlew test

run:
	./build/install/app/bin/app build/resources/main/fixtures/file11.json build/resources/main/fixtures/file12.json -f plain

runh:
	./build/install/app/bin/app -h

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

build:
	./gradlew build

#./build/install/app/bin/app build/resources/main/fixtures/file1.yml build/resources/main/fixtures/file2.yml -f yml
#./build/install/app/bin/app build/resources/main/fixtures/file1.json build/resources/main/fixtures/file2.json -f json