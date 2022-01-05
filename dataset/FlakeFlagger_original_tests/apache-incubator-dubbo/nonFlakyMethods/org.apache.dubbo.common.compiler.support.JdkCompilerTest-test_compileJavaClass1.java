@Test(expected=IllegalStateException.class) public void test_compileJavaClass1() throws Exception {
  JdkCompiler compiler=new JdkCompiler();
  Class<?> clazz=compiler.compile(getSimpleCodeWithSyntax(),JdkCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
