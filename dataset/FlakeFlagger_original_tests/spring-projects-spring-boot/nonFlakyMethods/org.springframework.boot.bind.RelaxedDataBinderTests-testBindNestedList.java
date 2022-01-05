@Test public void testBindNestedList() throws Exception {
  TargetWithNestedList target=new TargetWithNestedList();
  bind(target,"nested[0]: bar\nnested[1]: foo");
  assertEquals("[bar, foo]",target.getNested().toString());
}
