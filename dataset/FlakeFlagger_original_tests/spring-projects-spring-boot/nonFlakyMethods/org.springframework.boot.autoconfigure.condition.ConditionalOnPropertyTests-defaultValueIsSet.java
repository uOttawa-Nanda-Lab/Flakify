@Test public void defaultValueIsSet(){
  load(DefaultValueConfig.class,"simple.myProperty:bar");
  assertTrue(this.context.containsBean("foo"));
}
