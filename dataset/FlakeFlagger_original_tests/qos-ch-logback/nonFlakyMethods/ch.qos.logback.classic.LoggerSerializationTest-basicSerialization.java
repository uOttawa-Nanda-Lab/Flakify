@Test public void basicSerialization() throws IOException, ClassNotFoundException {
  Foo foo=new Foo(logger);
  foo.doFoo();
  Foo fooBack=writeAndRead(foo);
  fooBack.doFoo();
}
