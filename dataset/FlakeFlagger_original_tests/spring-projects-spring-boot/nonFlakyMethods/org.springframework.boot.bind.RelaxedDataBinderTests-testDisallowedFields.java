@Test public void testDisallowedFields() throws Exception {
  VanillaTarget target=new VanillaTarget();
  RelaxedDataBinder binder=getBinder(target,null);
  binder.setDisallowedFields("foo","bar");
  binder.setIgnoreUnknownFields(false);
  BindingResult result=bind(binder,target,"foo: bar\n" + "value: 123\n" + "bar: spam");
  assertEquals(123,target.getValue());
  assertNull(target.getFoo());
  assertEquals(0,result.getErrorCount());
}
