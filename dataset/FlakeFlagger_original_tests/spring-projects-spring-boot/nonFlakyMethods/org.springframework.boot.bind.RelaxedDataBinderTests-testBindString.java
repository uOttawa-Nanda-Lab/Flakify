@Test public void testBindString() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"foo: bar");
  assertEquals("bar",target.getFoo());
}
