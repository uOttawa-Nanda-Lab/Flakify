@Test(expected=IllegalStateException.class) public void testNewInstance1(){
  ClassUtils.newInstance("org.apache.dubbo.common.compiler.support.internal.HelloServiceInternalImpl");
}
