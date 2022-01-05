@Test public void testCompileJavaClass() throws Exception {
  JavassistCompiler compiler=new JavassistCompiler();
  Class<?> clazz=compiler.compile(getSimpleCode(),JavassistCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
