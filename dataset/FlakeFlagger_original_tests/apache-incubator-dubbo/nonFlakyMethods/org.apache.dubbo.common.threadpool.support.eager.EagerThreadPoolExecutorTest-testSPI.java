@Test public void testSPI(){
  ExecutorService executorService=(ExecutorService)ExtensionLoader.getExtensionLoader(ThreadPool.class).getExtension("eager").getExecutor(URL);
  Assert.assertTrue("test spi fail!",executorService.getClass().getSimpleName().equals("EagerThreadPoolExecutor"));
}
