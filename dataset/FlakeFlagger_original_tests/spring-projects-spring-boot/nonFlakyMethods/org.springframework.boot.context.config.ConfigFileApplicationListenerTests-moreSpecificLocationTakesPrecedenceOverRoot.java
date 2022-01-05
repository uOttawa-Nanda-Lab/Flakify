@Test public void moreSpecificLocationTakesPrecedenceOverRoot() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.name:specific");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("specific"));
}
