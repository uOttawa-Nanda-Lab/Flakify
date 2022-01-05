@Test(expected=IllegalStateException.class) public void test_compileJavaClass0() throws Exception {
  JdkCompiler compiler=new JdkCompiler();
  Class<?> clazz=compiler.compile(getSimpleCodeWithoutPackage(),JdkCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
