@Test public void relaxedEnabledByDefault(){
  load(PrefixValueConfig.class,"simple.myProperty:bar");
  assertTrue(this.context.containsBean("foo"));
}
