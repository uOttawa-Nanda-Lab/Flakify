@Test public void specificNameAndProfileFromExistingSource() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.profiles.active=specificprofile","spring.config.name=specificfile");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromspecificpropertiesfile"));
}
