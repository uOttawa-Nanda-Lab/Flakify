@Test public void testBindNestedMapOfListOfString() throws Exception {
  TargetWithNestedMapOfListOfString target=new TargetWithNestedMapOfListOfString();
  bind(target,"nested.foo[0]: bar\n" + "nested.bar[0]: bucket\n" + "nested.bar[1]: 123\nnested.bar[2]: crap");
  assertEquals(2,target.getNested().size());
  assertEquals(3,target.getNested().get("bar").size());
  assertEquals("123",target.getNested().get("bar").get(1));
  assertEquals("[bar]",target.getNested().get("foo").toString());
}
