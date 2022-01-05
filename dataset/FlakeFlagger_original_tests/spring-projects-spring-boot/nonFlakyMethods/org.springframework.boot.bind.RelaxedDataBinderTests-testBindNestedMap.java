@Test public void testBindNestedMap() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  bind(target,"nested.foo: bar\n" + "nested.value: 123");
  assertEquals("123",target.getNested().get("value"));
}
