MAIN_CLASS = cs1302.arcade.ArcadeDriver

run: compile
	export MAVEN_OPTS=-Dprism.order=sw; \
	mvn -e exec:java -Dexec.mainClass="$(MAIN_CLASS)"

compile:
	mvn -e compile

clean:
	mvn clean

doc:
	mvn javadoc:javadoc
