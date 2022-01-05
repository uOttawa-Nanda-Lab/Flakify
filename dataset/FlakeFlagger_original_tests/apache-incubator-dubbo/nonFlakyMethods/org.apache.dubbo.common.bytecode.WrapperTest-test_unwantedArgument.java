@Test public void test_unwantedArgument() throws Exception {
  Wrapper w=Wrapper.getWrapper(I1.class);
  Object obj=new Impl1();
  try {
    w.invokeMethod(obj,"hello",new Class<?>[]{String.class,String.class},new Object[]{"qianlei","badboy"});
    fail();
  }
 catch (  NoSuchMethodException expected) {
  }
}
