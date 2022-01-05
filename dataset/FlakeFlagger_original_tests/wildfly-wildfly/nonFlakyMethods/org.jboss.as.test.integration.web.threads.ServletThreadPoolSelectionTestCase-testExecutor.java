@Test public void testExecutor() throws Exception {
  ExecutorService executor=Executors.newFixedThreadPool(10);
  try {
    final List<Future<?>> results=new ArrayList<Future<?>>();
    for (int i=0; i < 100; ++i) {
      results.add(executor.submit(new Callable<Object>(){
        @Override public Object call() throws Exception {
          HttpRequest.get(url.toExternalForm() + "/race",10,SECONDS);
          return null;
        }
      }
));
    }
    for (    Future<?> res : results) {
      res.get();
    }
    String result=HttpRequest.get(url.toExternalForm() + "/race",10,SECONDS);
    assertEquals("100",result);
  }
  finally {
    executor.shutdown();
  }
}
