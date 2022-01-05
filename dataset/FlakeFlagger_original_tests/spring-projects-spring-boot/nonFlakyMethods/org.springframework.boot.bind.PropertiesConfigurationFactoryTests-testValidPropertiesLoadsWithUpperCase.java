@Test public void testValidPropertiesLoadsWithUpperCase() throws Exception {
  Foo foo=createFoo("NAME: blah\nbar: blah");
  assertEquals("blah",foo.bar);
  assertEquals("blah",foo.name);
}
