@Test public void testBindNestedUntypedMap() throws Exception {
  TargetWithNestedUntypedMap target=new TargetWithNestedUntypedMap();
  bind(target,"nested.foo: bar\n" + "nested.value: 123");
  assertEquals("123",target.getNested().get("value"));
}
