@Test public void strictNameNoMatch(){
  load(StrictNameConfig.class,"simple.myProperty:bar");
  assertFalse(this.context.containsBean("foo"));
}
