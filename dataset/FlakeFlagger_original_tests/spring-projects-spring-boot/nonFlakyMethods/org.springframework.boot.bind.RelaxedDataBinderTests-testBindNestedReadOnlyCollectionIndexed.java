@Test public void testBindNestedReadOnlyCollectionIndexed() throws Exception {
  TargetWithReadOnlyNestedCollection target=new TargetWithReadOnlyNestedCollection();
  this.conversionService=new DefaultConversionService();
  bind(target,"nested[0]: bar\nnested[1]:foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
