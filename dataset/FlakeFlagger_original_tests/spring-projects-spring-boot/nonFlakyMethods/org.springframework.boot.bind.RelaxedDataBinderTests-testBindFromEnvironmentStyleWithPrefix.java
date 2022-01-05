@Test public void testBindFromEnvironmentStyleWithPrefix() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"TEST_FOO: bar","test");
  assertEquals("bar",target.getFoo());
}
