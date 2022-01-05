@Test public void testBindToNamedTarget() throws Exception {
  this.targetName="foo";
  Foo foo=createFoo("hi: hello\nfoo.map.name: foo\nfoo.map.bar: blah");
  assertEquals("blah",foo.map.get("bar"));
}
