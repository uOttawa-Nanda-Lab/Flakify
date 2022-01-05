@Test public void caseInsensitive(){
  load(SimpleValueConfig.class,"simple.myProperty:BaR");
  assertTrue(this.context.containsBean("foo"));
}
