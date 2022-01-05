@Test public void testValidPropertiesLoadsWithNoErrors() throws Exception {
  Foo foo=createFoo("map.name: blah\nmap.bar: blah");
  assertEquals("blah",foo.map.get("bar"));
  assertEquals("blah",foo.map.get("name"));
}
