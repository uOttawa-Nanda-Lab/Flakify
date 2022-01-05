@Test public void testBindNestedMapOfString() throws Exception {
  TargetWithNestedMapOfString target=new TargetWithNestedMapOfString();
  bind(target,"nested.foo: bar\n" + "nested.value.foo: 123");
  assertEquals("bar",target.getNested().get("foo"));
  assertEquals("123",target.getNested().get("value.foo"));
}
