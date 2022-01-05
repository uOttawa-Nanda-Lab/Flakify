@Test public void testBindFromEnvironmentStyleWithNestedPrefix() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"TEST_IT_FOO: bar","test.it");
  assertEquals("bar",target.getFoo());
}
