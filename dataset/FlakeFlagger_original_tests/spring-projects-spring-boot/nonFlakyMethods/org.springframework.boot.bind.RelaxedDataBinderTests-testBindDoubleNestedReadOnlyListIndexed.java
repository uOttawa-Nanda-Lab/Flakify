@Test public void testBindDoubleNestedReadOnlyListIndexed() throws Exception {
  TargetWithReadOnlyDoubleNestedList target=new TargetWithReadOnlyDoubleNestedList();
  this.conversionService=new DefaultConversionService();
  bind(target,"bean.nested[0]:bar\nbean.nested[1]:foo");
  assertEquals("[bar, foo]",target.getBean().getNested().toString());
}
