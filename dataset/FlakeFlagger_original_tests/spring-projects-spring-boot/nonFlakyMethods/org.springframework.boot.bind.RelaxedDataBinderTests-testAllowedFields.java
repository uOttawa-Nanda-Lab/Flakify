@Test public void testAllowedFields() throws Exception {
  VanillaTarget target=new VanillaTarget();
  RelaxedDataBinder binder=getBinder(target,null);
  binder.setAllowedFields("foo");
  binder.setIgnoreUnknownFields(false);
  BindingResult result=bind(binder,target,"foo: bar\n" + "value: 123\n" + "bar: spam");
  assertEquals(0,target.getValue());
  assertEquals("bar",target.getFoo());
  assertEquals(0,result.getErrorCount());
}
