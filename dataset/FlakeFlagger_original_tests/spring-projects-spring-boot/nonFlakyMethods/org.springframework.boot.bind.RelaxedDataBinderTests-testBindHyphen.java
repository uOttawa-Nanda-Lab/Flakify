@Test public void testBindHyphen() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"foo-baz: bar");
  assertEquals("bar",target.getFooBaz());
}
