@Test public void testBindNumber() throws Exception {
  VanillaTarget target=new VanillaTarget();
  bind(target,"foo: bar\n" + "value: 123");
  assertEquals(123,target.getValue());
}
