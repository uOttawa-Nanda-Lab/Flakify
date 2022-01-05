@Test(timeout=2000) public void lossyAppenderShouldOnlyLooseCertainEvents(){
  int bufferSize=5;
  int loopLen=bufferSize * 2;
  lossyAsyncAppender.addAppender(delayingListAppender);
  lossyAsyncAppender.setQueueSize(bufferSize);
  lossyAsyncAppender.setDiscardingThreshold(1);
  lossyAsyncAppender.start();
  for (int i=0; i < loopLen; i++) {
    lossyAsyncAppender.doAppend(i);
  }
  lossyAsyncAppender.stop();
  verify(delayingListAppender,loopLen - 2);
}
