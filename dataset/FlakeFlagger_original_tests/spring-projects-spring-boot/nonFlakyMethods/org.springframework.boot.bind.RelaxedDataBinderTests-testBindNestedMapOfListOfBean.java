@Test public void testBindNestedMapOfListOfBean() throws Exception {
  TargetWithNestedMapOfListOfBean target=new TargetWithNestedMapOfListOfBean();
  bind(target,"nested.foo[0].foo: bar\n" + "nested.bar[0].foo: bucket\n" + "nested.bar[1].value: 123\nnested.bar[2].foo: crap");
  assertEquals(2,target.getNested().size());
  assertEquals(3,target.getNested().get("bar").size());
  assertEquals(123,target.getNested().get("bar").get(1).getValue());
  assertEquals("bar",target.getNested().get("foo").get(0).getFoo());
}
