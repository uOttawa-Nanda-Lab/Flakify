@Test public void testBindCamelCase() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"fooBaz: bar");
  assertEquals("bar",target.getFooBaz());
}
