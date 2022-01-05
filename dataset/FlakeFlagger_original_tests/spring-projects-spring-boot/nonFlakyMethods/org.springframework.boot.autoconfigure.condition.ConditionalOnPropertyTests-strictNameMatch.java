@Test public void strictNameMatch(){
  load(StrictNameConfig.class,"simple.my-property:bar");
  assertTrue(this.context.containsBean("foo"));
}
