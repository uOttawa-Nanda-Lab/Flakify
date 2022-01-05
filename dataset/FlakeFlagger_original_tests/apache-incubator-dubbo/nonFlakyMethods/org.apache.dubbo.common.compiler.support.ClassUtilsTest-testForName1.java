@Test(expected=IllegalStateException.class) public void testForName1(){
  ClassUtils.forName(new String[]{"org.apache.dubbo.common.compiler.support"},"HelloServiceImplXX");
}
