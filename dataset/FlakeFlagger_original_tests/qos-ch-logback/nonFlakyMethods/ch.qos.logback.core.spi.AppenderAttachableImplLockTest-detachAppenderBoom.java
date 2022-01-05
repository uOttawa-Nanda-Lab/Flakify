@SuppressWarnings("unchecked") @Test(timeout=5000) public void detachAppenderBoom() throws InterruptedException {
  Appender<Integer> mockAppender=mock(Appender.class);
  when(mockAppender.getName()).thenThrow(new OutOfMemoryError("oops"));
  mockAppender.doAppend(17);
  aai.addAppender(mockAppender);
  Thread t=new Thread(new Runnable(){
    public void run(){
      try {
        aai.detachAppender("foo");
      }
 catch (      OutOfMemoryError e) {
      }
    }
  }
);
  t.start();
  t.join();
  aai.appendLoopOnAppenders(17);
}
