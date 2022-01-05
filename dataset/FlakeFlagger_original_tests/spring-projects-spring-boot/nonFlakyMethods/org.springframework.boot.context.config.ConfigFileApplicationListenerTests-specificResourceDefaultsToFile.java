@Test public void specificResourceDefaultsToFile() throws Exception {
  String location="src/test/resources/specificlocation.properties";
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + location);
  this.initializer.onApplicationEvent(this.event);
  assertThat(this.environment,containsPropertySource("applicationConfig: [file:" + location + "]"));
}
