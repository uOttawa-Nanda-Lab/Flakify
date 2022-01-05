@Test public void testBindNestedSetCommaDelimitedOnly() throws Exception {
  TargetWithNestedSet target=new TargetWithNestedSet();
  this.conversionService=new DefaultConversionService();
  bind(target,"nested: bar,foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
