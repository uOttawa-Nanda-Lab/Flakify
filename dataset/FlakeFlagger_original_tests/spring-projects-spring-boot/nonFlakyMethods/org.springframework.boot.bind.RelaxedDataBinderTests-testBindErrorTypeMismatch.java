@Test public void testBindErrorTypeMismatch() throws Exception {
  VanillaTarget target=new VanillaTarget();
  BindingResult result=bind(target,"foo: bar\n" + "value: foo");
  assertEquals(1,result.getErrorCount());
}
