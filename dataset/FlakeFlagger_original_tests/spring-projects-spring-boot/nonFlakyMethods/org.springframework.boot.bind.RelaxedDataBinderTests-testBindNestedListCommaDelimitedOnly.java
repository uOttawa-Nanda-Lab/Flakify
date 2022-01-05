@Test public void testBindNestedListCommaDelimitedOnly() throws Exception {
  TargetWithNestedList target=new TargetWithNestedList();
  this.conversionService=new DefaultConversionService();
  bind(target,"nested: bar,foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
