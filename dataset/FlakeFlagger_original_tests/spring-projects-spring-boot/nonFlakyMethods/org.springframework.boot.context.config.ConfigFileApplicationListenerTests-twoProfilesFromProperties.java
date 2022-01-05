@Test public void twoProfilesFromProperties() throws Exception {
  this.environment.setActiveProfiles("other","dev");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromdevpropertiesfile"));
}
