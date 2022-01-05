@Test public void testValidPropertiesLoadsWithNoErrors() throws Exception {
  Foo foo=createFoo("name: blah\nbar: blah");
  assertEquals("blah",foo.bar);
  assertEquals("blah",foo.name);
}
