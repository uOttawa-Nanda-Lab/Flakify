@Test public void loadPropertiesThenProfilePropertiesWithOverride() throws Exception {
  this.environment.setActiveProfiles("other");
  this.initializer.setSearchNames("enableprofile");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("other.property");
  assertThat(property,equalTo("fromotherpropertiesfile"));
  property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromprofilepropertiesfile"));
}
