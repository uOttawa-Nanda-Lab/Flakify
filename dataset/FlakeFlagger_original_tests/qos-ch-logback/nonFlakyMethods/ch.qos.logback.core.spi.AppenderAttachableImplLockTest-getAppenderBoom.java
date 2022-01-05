@SuppressWarnings("unchecked") @Test(timeout=5000) public void getAppenderBoom(){
  Appender<Integer> mockAppender1=mock(Appender.class);
  when(mockAppender1.getName()).thenThrow(new OutOfMemoryError("oops"));
  aai.addAppender(mockAppender1);
  try {
    aai.getAppender("foo");
  }
 catch (  OutOfMemoryError e) {
  }
  Appender<Integer> mockAppender2=mock(Appender.class);
  aai.addAppender(mockAppender2);
}
