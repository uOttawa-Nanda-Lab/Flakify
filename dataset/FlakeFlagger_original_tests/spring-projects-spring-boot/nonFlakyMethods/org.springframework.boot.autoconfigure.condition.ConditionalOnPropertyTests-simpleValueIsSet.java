@Test public void simpleValueIsSet(){
  load(SimpleValueConfig.class,"simple.myProperty:bar");
  assertTrue(this.context.containsBean("foo"));
}
