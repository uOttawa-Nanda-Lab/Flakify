@Test public void prefix(){
  load(PrefixValueConfig.class,"simple.myProperty:bar");
  assertTrue(this.context.containsBean("foo"));
}
