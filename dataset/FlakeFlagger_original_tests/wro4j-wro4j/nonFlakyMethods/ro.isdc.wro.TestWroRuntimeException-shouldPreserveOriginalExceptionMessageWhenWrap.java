@Test public void shouldPreserveOriginalExceptionMessageWhenWrap(){
  final String message="someMessage";
  Exception e=new IllegalArgumentException(message);
  Exception result=WroRuntimeException.wrap(e);
  Assert.assertEquals(e.getMessage(),result.getMessage());
}
