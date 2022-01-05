@Test public void testBindCapitals() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"FOO: bar");
  assertEquals("bar",target.getFoo());
}
