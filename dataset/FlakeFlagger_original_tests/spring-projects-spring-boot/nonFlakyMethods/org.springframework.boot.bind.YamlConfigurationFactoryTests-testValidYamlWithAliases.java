@Test public void testValidYamlWithAliases() throws Exception {
  this.aliases.put(Foo.class,Collections.singletonMap("foo-name","name"));
  Foo foo=createFoo("foo-name: blah\nbar: blah");
  assertEquals("blah",foo.name);
}
