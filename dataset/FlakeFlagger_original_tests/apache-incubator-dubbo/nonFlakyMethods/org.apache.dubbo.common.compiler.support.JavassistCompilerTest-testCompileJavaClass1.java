@Test(expected=IllegalStateException.class) public void testCompileJavaClass1() throws Exception {
  JavassistCompiler compiler=new JavassistCompiler();
  Class<?> clazz=compiler.compile(getSimpleCodeWithSyntax0(),JavassistCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
