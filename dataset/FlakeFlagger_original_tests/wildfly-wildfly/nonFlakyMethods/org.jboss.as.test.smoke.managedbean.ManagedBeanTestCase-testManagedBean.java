@Test public void testManagedBean() throws Exception {
  BeanWithSimpleInjected bean=(BeanWithSimpleInjected)context.lookup("java:module/" + BeanWithSimpleInjected.class.getSimpleName());
  Assert.assertNotNull(bean.getSimple());
  Assert.assertNotNull(bean.getSimple2());
  String s=bean.echo("Hello");
  Assert.assertEquals("#InterceptorFromParent##InterceptorBean##OtherInterceptorBean##CDIInterceptor##BeanParent##BeanWithSimpleInjected#Hello#CDIBean#CDIBean",s);
  Assert.assertEquals(100,bean.getNumber());
  Assert.assertEquals("value",bean.getValue());
  Assert.assertEquals("value",bean.getValue2());
}
