@Test public void testBindErrorNotWritable() throws Exception {
  this.expected.expectMessage("property 'spam'");
  this.expected.expectMessage("not writable");
  VanillaTarget target=new VanillaTarget();
  BindingResult result=bind(target,"spam: bar\n" + "value: 123");
  assertEquals(1,result.getErrorCount());
}
