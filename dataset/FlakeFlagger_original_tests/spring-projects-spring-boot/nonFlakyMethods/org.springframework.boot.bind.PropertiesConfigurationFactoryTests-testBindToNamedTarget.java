@Test public void testBindToNamedTarget() throws Exception {
  this.targetName="foo";
  Foo foo=createFoo("hi: hello\nfoo.name: foo\nfoo.bar: blah");
  assertEquals("blah",foo.bar);
}
