@Test public void prefixWithoutPeriod() throws Exception {
  load(RelaxedPropertiesRequiredConfigurationWithShortPrefix.class,"spring.property=value1");
  assertTrue(this.context.containsBean("foo"));
}
