/** 
 * for example, it will print like this: before a future is create , time is : 2018-06-21 15:11:31 after a future is timeout , time is : 2018-06-21 15:11:36 <p> The exception info print like: Waiting server-side response timeout by scan timer. start time: 2018-06-21 15:12:38.337, end time: 2018-06-21 15:12:43.354...
 */
@Test public void timeoutSend() throws Exception {
  final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  System.out.println("before a future is create , time is : " + LocalDateTime.now().format(formatter));
  Channel channel=new MockedChannel();
  Request request=new Request(10);
  DefaultFuture f=DefaultFuture.newFuture(channel,request,5000);
  DefaultFuture.sent(channel,request);
  while (!f.isDone()) {
    Thread.sleep(100);
  }
  System.out.println("after a future is timeout , time is : " + LocalDateTime.now().format(formatter));
  try {
    f.get();
  }
 catch (  Exception e) {
    Assert.assertTrue("catch exception is not timeout exception!",e instanceof TimeoutException);
    System.out.println(e.getMessage());
  }
}
