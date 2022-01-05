@SuppressWarnings("unchecked") @Test public void testBindDoubleNestedMap() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  bind(target,"nested.foo: bar\n" + "nested.bar.spam: bucket\n" + "nested.bar.value: 123\nnested.bar.foo: crap");
  assertEquals(2,target.getNested().size());
  assertEquals(3,((Map<String,Object>)target.getNested().get("bar")).size());
  assertEquals("123",((Map<String,Object>)target.getNested().get("bar")).get("value"));
  assertEquals("bar",target.getNested().get("foo"));
  assertFalse(target.getNested().containsValue(target.getNested()));
}
