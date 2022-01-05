@Test public void testBindUnderscoreToCamelCase() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"foo_baz: bar");
  assertEquals("bar",target.getFooBaz());
}
