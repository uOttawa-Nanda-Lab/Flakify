@Test public void loadTwoPropertiesFilesWithProfilesAndSwitchOneOff() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + "classpath:enabletwoprofiles.properties," + "classpath:enableprofile.properties");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("myprofile",StringUtils.arrayToCommaDelimitedString(this.environment.getActiveProfiles()));
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
