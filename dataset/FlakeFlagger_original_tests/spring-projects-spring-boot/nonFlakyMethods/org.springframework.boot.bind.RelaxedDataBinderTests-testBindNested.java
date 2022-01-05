@Test public void testBindNested() throws Exception {
  TargetWithNestedObject target=new TargetWithNestedObject();
  bind(target,"nested.foo: bar\n" + "nested.value: 123");
  assertEquals(123,target.getNested().getValue());
}
