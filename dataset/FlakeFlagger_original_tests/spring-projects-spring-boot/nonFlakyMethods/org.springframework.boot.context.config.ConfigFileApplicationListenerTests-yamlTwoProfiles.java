@Test public void yamlTwoProfiles() throws Exception {
  this.initializer.setSearchNames("testprofiles");
  this.environment.setActiveProfiles("other","dev");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromdevprofile"));
  property=this.environment.getProperty("my.other");
  assertThat(property,equalTo("notempty"));
}
