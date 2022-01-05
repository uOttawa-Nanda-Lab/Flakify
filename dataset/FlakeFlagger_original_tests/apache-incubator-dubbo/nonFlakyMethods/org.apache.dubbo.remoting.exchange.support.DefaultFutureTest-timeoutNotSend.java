/** 
 * for example, it will print like this: before a future is create , time is : 2018-06-21 15:06:17 after a future is timeout , time is : 2018-06-21 15:06:22 <p> The exception info print like: Sending request timeout in client-side by scan timer. start time: 2018-06-21 15:13:02.215, end time: 2018-06-21 15:13:07.231...
 */
@Test public void timeoutNotSend() throws Exception {
  final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  System.out.println("before a future is create , time is : " + LocalDateTime.now().format(formatter));
  DefaultFuture f=defaultFuture(5000);
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
