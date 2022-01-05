@Test public void multiValuesOnlyOneSet(){
  load(MultiValuesConfig.class,"simple.my-property:bar");
  assertFalse(this.context.containsBean("foo"));
}
