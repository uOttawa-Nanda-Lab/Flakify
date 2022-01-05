@Test public void loadPropertiesThenProfilePropertiesActivatedInSpringApplication() throws Exception {
  this.environment.setActiveProfiles("other");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromotherpropertiesfile"));
}
