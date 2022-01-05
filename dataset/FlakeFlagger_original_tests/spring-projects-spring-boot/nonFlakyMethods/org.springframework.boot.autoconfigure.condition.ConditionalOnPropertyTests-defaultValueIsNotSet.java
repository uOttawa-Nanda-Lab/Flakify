@Test public void defaultValueIsNotSet(){
  load(DefaultValueConfig.class);
  assertTrue(this.context.containsBean("foo"));
}
