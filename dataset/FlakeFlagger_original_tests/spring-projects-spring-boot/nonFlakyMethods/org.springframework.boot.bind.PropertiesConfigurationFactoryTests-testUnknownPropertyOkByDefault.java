@Test public void testUnknownPropertyOkByDefault() throws Exception {
  Foo foo=createFoo("hi: hello\nname: foo\nbar: blah");
  assertEquals("blah",foo.bar);
}
