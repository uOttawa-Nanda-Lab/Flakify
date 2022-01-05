public void testOutOfOrderResponses() throws Exception {
  AtomicInteger firstResponseCode=new AtomicInteger();
  AtomicInteger secondResponseCode=new AtomicInteger();
  mockWebServer.play();
  final String secondRequest="/bar";
  final String firstRequest="/foo";
  final CountDownLatch latch=new CountDownLatch(1);
  final Dispatcher dispatcher=new Dispatcher(){
    @Override public MockResponse dispatch(    RecordedRequest request) throws InterruptedException {
      if (request.getPath().equals(firstRequest)) {
        latch.await();
      }
      return new MockResponse();
    }
  }
;
  mockWebServer.setDispatcher(dispatcher);
  final Thread startsFirst=buildRequestThread(firstRequest,firstResponseCode);
  startsFirst.start();
  final Thread endsFirst=buildRequestThread(secondRequest,secondResponseCode);
  endsFirst.start();
  endsFirst.join();
  assertEquals(0,firstResponseCode.get());
  assertEquals(200,secondResponseCode.get());
  latch.countDown();
  startsFirst.join();
  assertEquals(200,firstResponseCode.get());
  assertEquals(200,secondResponseCode.get());
}
