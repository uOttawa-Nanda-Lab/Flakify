@Test public void testBindNestedReadOnlyListIndexed() throws Exception {
  TargetWithReadOnlyNestedList target=new TargetWithReadOnlyNestedList();
  this.conversionService=new DefaultConversionService();
  bind(target,"nested[0]: bar\nnested[1]:foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
