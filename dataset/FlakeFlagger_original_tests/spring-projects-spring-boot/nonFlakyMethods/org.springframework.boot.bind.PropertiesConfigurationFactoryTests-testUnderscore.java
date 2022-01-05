@Test public void testUnderscore() throws Exception {
  Foo foo=createFoo("spring_foo_baz: blah\nname: blah");
  assertEquals("blah",foo.spring_foo_baz);
  assertEquals("blah",foo.name);
}
