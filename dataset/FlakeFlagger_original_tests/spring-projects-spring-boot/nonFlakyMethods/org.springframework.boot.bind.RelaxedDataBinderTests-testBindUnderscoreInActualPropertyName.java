@Test public void testBindUnderscoreInActualPropertyName() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"foo-bar: bar");
  assertEquals("bar",target.getFoo_bar());
}
