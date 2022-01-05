@Test public void testCompileJavaClassWithExtends() throws Exception {
  JavassistCompiler compiler=new JavassistCompiler();
  Class<?> clazz=compiler.compile(getSimpleCodeWithWithExtends(),JavassistCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world3!",sayHello.invoke(instance));
}
