@Test public void newTimeout() throws InterruptedException {
  final Timer timer=newTimer();
  for (int i=0; i < 10; i++) {
    timer.newTimeout(new PrintTask(),1,TimeUnit.SECONDS);
    Thread.sleep(1000);
  }
  Thread.sleep(5000);
}
