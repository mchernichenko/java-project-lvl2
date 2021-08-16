setup:
	./gradlew wrapper --gradle-version 7.1

clean:
	./gradlew clean

install:
	./gradlew clean install

test:
	./gradlew test

run:
	./build/install/app/bin/app build/resources/main/fixtures/file_complex.json build/resources/main/fixtures/file_complex.json

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

build:
	./gradlew build
