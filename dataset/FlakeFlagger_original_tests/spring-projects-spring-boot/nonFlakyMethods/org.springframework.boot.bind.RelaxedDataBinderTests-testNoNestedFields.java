@Test public void testNoNestedFields() throws Exception {
  VanillaTarget target=new VanillaTarget();
  RelaxedDataBinder binder=getBinder(target,"foo");
  binder.setIgnoreUnknownFields(false);
  binder.setIgnoreNestedProperties(true);
  BindingResult result=bind(binder,target,"foo.foo: bar\n" + "foo.value: 123\n" + "foo.nested.bar: spam");
  assertEquals(123,target.getValue());
  assertEquals("bar",target.getFoo());
  assertEquals(0,result.getErrorCount());
}
