@Test public void testBindErrorNotWritableWithPrefix() throws Exception {
  VanillaTarget target=new VanillaTarget();
  BindingResult result=bind(target,"spam: bar\n" + "vanilla.value: 123","vanilla");
  assertEquals(0,result.getErrorCount());
  assertEquals(123,target.getValue());
}
