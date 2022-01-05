@Test public void loadTwoPropertiesFilesWithProfilesAndSwitchOneOffFromSpecificLocation() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.name:enabletwoprofiles","spring.config.location:classpath:enableprofile.properties");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("myprofile",StringUtils.arrayToCommaDelimitedString(this.environment.getActiveProfiles()));
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
