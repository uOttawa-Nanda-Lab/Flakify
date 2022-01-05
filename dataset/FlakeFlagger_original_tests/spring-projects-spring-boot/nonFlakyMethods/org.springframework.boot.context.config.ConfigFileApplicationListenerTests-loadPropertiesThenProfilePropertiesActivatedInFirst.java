@Test public void loadPropertiesThenProfilePropertiesActivatedInFirst() throws Exception {
  this.initializer.setSearchNames("enableprofile");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromprofilepropertiesfile"));
}
