@Test public void loadTwoOfThreePropertiesFile() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + "classpath:application.properties," + "classpath:testproperties.properties,"+ "classpath:nonexistent.properties");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
