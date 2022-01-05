/** 
 * javassist compile will find HelloService in classpath
 */
@Test public void testCompileJavaClass0() throws Exception {
  JavassistCompiler compiler=new JavassistCompiler();
  Class<?> clazz=compiler.compile(getSimpleCodeWithoutPackage(),JavassistCompiler.class.getClassLoader());
  Object instance=clazz.newInstance();
  Method sayHello=instance.getClass().getMethod("sayHello");
  Assert.assertEquals("Hello world!",sayHello.invoke(instance));
}
