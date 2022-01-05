@Test public void testBindNestedMapOfBean() throws Exception {
  TargetWithNestedMapOfBean target=new TargetWithNestedMapOfBean();
  bind(target,"nested.foo.foo: bar\n" + "nested.bar.foo: bucket");
  assertEquals(2,target.getNested().size());
  assertEquals("bucket",target.getNested().get("bar").getFoo());
}
