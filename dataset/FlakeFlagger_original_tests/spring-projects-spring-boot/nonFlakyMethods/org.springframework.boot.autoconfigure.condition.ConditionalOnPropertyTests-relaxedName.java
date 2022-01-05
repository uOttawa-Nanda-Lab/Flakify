@Test public void relaxedName(){
  load(RelaxedPropertiesRequiredConfiguration.class,"spring.theRelaxedProperty=value1");
  assertTrue(this.context.containsBean("foo"));
}
