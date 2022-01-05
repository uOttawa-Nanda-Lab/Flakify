@Test public void specificResource() throws Exception {
  String location="classpath:specificlocation.properties";
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + location);
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromspecificlocation"));
  assertThat(this.environment,containsPropertySource("applicationConfig: " + "[classpath:specificlocation.properties]"));
  assertThat(this.environment,containsPropertySource("applicationConfig: " + "[classpath:/application.properties]"));
  assertThat(this.environment.getProperty("foo"),equalTo("bucket"));
}
