@Test public void specificResourceAsFile() throws Exception {
  String location="file:src/test/resources/specificlocation.properties";
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + location);
  this.initializer.onApplicationEvent(this.event);
  assertThat(this.environment,containsPropertySource("applicationConfig: [" + location + "]"));
}
