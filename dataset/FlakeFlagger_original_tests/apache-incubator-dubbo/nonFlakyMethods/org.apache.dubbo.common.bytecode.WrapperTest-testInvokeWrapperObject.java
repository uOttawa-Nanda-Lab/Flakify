@Test public void testInvokeWrapperObject() throws Exception {
  Wrapper w=Wrapper.getWrapper(Object.class);
  Object instance=new Object();
  Assert.assertEquals(instance.getClass(),(Class<?>)w.invokeMethod(instance,"getClass",null,null));
  Assert.assertEquals(instance.hashCode(),(int)w.invokeMethod(instance,"hashCode",null,null));
  Assert.assertEquals(instance.toString(),(String)w.invokeMethod(instance,"toString",null,null));
  Assert.assertEquals(true,(boolean)w.invokeMethod(instance,"equals",null,new Object[]{instance}));
}
