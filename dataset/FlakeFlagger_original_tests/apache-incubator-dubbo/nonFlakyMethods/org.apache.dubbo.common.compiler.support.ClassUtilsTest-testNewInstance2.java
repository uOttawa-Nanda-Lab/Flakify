@Test(expected=IllegalStateException.class) public void testNewInstance2(){
  ClassUtils.newInstance("org.apache.dubbo.common.compiler.support.internal.NotExistsImpl");
}
