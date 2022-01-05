@Test public void testValidPropertiesLoadsWithDash() throws Exception {
  Foo foo=createFoo("na-me: blah\nbar: blah");
  assertEquals("blah",foo.bar);
  assertEquals("blah",foo.name);
}
