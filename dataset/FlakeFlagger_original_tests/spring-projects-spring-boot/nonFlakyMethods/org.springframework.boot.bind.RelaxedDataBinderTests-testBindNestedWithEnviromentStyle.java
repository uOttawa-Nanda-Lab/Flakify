@Test public void testBindNestedWithEnviromentStyle() throws Exception {
  TargetWithNestedObject target=new TargetWithNestedObject();
  bind(target,"nested_foo: bar\n" + "nested_value: 123");
  assertEquals(123,target.getNested().getValue());
}
