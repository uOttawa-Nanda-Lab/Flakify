@Test public void stop() throws InterruptedException {
  final Timer timer=newTimer();
  for (int i=0; i < 10; i++) {
    timer.newTimeout(new PrintTask(),5,TimeUnit.SECONDS);
    Thread.sleep(100);
  }
  timer.stop();
  try {
    timer.newTimeout(new PrintTask(),5,TimeUnit.SECONDS);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
