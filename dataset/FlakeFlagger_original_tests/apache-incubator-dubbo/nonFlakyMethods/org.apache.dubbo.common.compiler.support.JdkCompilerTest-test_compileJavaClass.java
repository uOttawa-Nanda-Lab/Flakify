@Test public void test_compileJavaClass() throws Exception {
  JdkCompiler compiler=new JdkCompiler();
  Class<?> clazz=compiler.compile(getSimpleCode(),JdkCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
