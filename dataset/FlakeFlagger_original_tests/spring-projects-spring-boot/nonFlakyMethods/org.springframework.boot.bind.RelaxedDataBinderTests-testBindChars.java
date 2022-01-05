@Test public void testBindChars() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"bar: foo");
  assertEquals("foo",new String(target.getBar()));
}
