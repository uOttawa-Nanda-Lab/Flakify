@Test public void multiValuesAllSet(){
  load(MultiValuesConfig.class,"simple.my-property:bar","simple.my-another-property:bar");
  assertTrue(this.context.containsBean("foo"));
}
