@Test public void testOnlyTopLevelFields() throws Exception {
  VanillaTarget target=new VanillaTarget();
  RelaxedDataBinder binder=getBinder(target,null);
  binder.setIgnoreUnknownFields(false);
  binder.setIgnoreNestedProperties(true);
  BindingResult result=bind(binder,target,"foo: bar\n" + "value: 123\n" + "nested.bar: spam");
  assertEquals(123,target.getValue());
  assertEquals("bar",target.getFoo());
  assertEquals(0,result.getErrorCount());
}
