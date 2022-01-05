@Test public void testClient() throws Throwable {
  if (PerformanceUtils.getProperty("server",null) == null) {
    logger.warn("Please set -Dserver=127.0.0.1:9911");
    return;
  }
  final String server=System.getProperty("server","127.0.0.1:9911");
  final String transporter=PerformanceUtils.getProperty(Constants.TRANSPORTER_KEY,Constants.DEFAULT_TRANSPORTER);
  final String serialization=PerformanceUtils.getProperty(Constants.SERIALIZATION_KEY,Constants.DEFAULT_REMOTING_SERIALIZATION);
  final int timeout=PerformanceUtils.getIntProperty(Constants.TIMEOUT_KEY,Constants.DEFAULT_TIMEOUT);
  final int concurrent=PerformanceUtils.getIntProperty("concurrent",1);
  final int runs=PerformanceUtils.getIntProperty("runs",Integer.MAX_VALUE);
  final String onerror=PerformanceUtils.getProperty("onerror","continue");
  final String url="exchange://" + server + "?transporter="+ transporter+ "&serialization="+ serialization+ "&timeout="+ timeout;
  final AtomicInteger count=new AtomicInteger();
  final AtomicInteger error=new AtomicInteger();
  for (int n=0; n < concurrent; n++) {
    new Thread(new Runnable(){
      public void run(){
        for (int i=0; i < runs; i++) {
          ExchangeClient client=null;
          try {
            client=Exchangers.connect(url);
            int c=count.incrementAndGet();
            if (c % 100 == 0) {
              System.out.println("count: " + count.get() + ", error: "+ error.get());
            }
          }
 catch (          Exception e) {
            error.incrementAndGet();
            e.printStackTrace();
            System.out.println("count: " + count.get() + ", error: "+ error.get());
            if ("exit".equals(onerror)) {
              System.exit(-1);
            }
 else             if ("break".equals(onerror)) {
              break;
            }
 else             if ("sleep".equals(onerror)) {
              try {
                Thread.sleep(30000);
              }
 catch (              InterruptedException e1) {
              }
            }
          }
 finally {
            if (client != null) {
              client.close();
            }
          }
        }
      }
    }
).start();
  }
synchronized (PerformanceServerTest.class) {
    while (true) {
      try {
        PerformanceServerTest.class.wait();
      }
 catch (      InterruptedException e) {
      }
    }
  }
}
