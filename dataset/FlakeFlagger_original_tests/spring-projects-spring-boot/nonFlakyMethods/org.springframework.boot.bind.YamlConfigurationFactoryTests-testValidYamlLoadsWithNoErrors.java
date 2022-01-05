@Test public void testValidYamlLoadsWithNoErrors() throws Exception {
  Foo foo=createFoo("name: blah\nbar: blah");
  assertEquals("blah",foo.bar);
}
