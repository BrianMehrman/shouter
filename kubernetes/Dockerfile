FROM clojure:openjdk-11-lein

COPY . /build-dir
WORKDIR /build-dir

RUN lein uberjar
COPY target/shouter-0.0.2-standalone.jar /shouter-0.0.2-standalone.jar

WORKDIR /

RUN rm -rf /build-dir

EXPOSE 8080

CMD java $JVM_OPTS -jar shouter-0.0.2-standalone.jar
