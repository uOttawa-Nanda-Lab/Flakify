@Test public void loadTwoPropertiesFilesWithProfiles() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + "classpath:enableprofile.properties,classpath:enableother.properties");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("other",StringUtils.arrayToCommaDelimitedString(this.environment.getActiveProfiles()));
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromotherpropertiesfile"));
}
