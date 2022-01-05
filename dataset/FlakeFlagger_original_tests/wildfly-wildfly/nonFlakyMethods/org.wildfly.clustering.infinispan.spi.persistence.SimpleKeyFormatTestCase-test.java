@Test public void test(){
  Function<String,Object> parser=mock(Function.class);
  Function<Object,String> formatter=mock(Function.class);
  KeyFormat<Object> format=new SimpleKeyFormat<>(Object.class,parser,formatter);
  Object object=new Object();
  String result="foo";
  when(formatter.apply(object)).thenReturn(result);
  when(parser.apply(result)).thenReturn(object);
  new KeyFormatTester<>(format,Assert::assertSame).test(object);
}
