@Test public void testBindStringWithPrefix() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"test.foo: bar","test");
  assertEquals("bar",target.getFoo());
}
