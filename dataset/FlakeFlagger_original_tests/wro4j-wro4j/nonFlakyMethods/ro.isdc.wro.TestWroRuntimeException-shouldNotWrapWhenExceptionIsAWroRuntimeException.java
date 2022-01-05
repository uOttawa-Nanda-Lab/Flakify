@Test public void shouldNotWrapWhenExceptionIsAWroRuntimeException(){
  final String message="someMessage";
  Exception e=new WroRuntimeException(message);
  Exception result=WroRuntimeException.wrap(e);
  Assert.assertSame(e,result);
}
