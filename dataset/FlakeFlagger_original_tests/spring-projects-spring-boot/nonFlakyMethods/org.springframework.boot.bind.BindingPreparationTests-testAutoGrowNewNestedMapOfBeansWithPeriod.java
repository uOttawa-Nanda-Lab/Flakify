@Test public void testAutoGrowNewNestedMapOfBeansWithPeriod() throws Exception {
  TargetWithNestedMapOfBean target=new TargetWithNestedMapOfBean();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  String result=binder.normalizePath(wrapper,"nested.foo.foo");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertEquals("nested[foo].foo",result);
}
