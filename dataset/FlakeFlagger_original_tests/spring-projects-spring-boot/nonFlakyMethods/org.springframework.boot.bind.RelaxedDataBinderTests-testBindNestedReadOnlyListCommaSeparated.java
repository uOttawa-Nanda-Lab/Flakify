@Test(expected=NotWritablePropertyException.class) public void testBindNestedReadOnlyListCommaSeparated() throws Exception {
  TargetWithReadOnlyNestedList target=new TargetWithReadOnlyNestedList();
  this.conversionService=new DefaultConversionService();
  bind(target,"nested: bar,foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
