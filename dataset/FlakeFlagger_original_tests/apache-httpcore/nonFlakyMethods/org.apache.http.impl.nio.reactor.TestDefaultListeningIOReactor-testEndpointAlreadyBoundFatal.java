@Test public void testEndpointAlreadyBoundFatal() throws Exception {
  HttpParams params=new SyncBasicHttpParams();
  HttpProcessor httpproc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  final BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(httpproc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),params);
  final IOEventDispatch eventDispatch=new DefaultServerIOEventDispatch(serviceHandler,params);
  IOReactorConfig config=new IOReactorConfig();
  config.setIoThreadCount(1);
  final ListeningIOReactor ioreactor=new DefaultListeningIOReactor(config);
  final CountDownLatch latch=new CountDownLatch(1);
  Thread t=new Thread(new Runnable(){
    public void run(){
      try {
        ioreactor.execute(eventDispatch);
        Assert.fail("IOException should have been thrown");
      }
 catch (      IOException ex) {
        latch.countDown();
      }
    }
  }
);
  t.start();
  ListenerEndpoint endpoint1=ioreactor.listen(new InetSocketAddress(9999));
  endpoint1.waitFor();
  ListenerEndpoint endpoint2=ioreactor.listen(new InetSocketAddress(9999));
  endpoint2.waitFor();
  Assert.assertNotNull(endpoint2.getException());
  latch.await(2000,TimeUnit.MILLISECONDS);
  Assert.assertTrue(ioreactor.getStatus().compareTo(IOReactorStatus.SHUTTING_DOWN) >= 0);
  Set<ListenerEndpoint> endpoints=ioreactor.getEndpoints();
  Assert.assertNotNull(endpoints);
  Assert.assertEquals(0,endpoints.size());
  ioreactor.shutdown(1000);
  t.join(1000);
  Assert.assertEquals(IOReactorStatus.SHUT_DOWN,ioreactor.getStatus());
}
