@Test public void testMergeUrl() throws Exception {
  URL providerURL=URL.valueOf("dubbo://localhost:55555");
  providerURL=providerURL.setPath("path").setUsername("username").setPassword("password");
  providerURL=providerURL.addParameter(Constants.GROUP_KEY,"dubbo").addParameter(Constants.VERSION_KEY,"1.2.3").addParameter(Constants.DUBBO_VERSION_KEY,"2.3.7").addParameter(Constants.THREADPOOL_KEY,"fixed").addParameter(Constants.THREADS_KEY,Integer.MAX_VALUE).addParameter(Constants.THREAD_NAME_KEY,"test").addParameter(Constants.CORE_THREADS_KEY,Integer.MAX_VALUE).addParameter(Constants.QUEUES_KEY,Integer.MAX_VALUE).addParameter(Constants.ALIVE_KEY,Integer.MAX_VALUE).addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREADS_KEY,Integer.MAX_VALUE).addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREADPOOL_KEY,"fixed").addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.CORE_THREADS_KEY,Integer.MAX_VALUE).addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.QUEUES_KEY,Integer.MAX_VALUE).addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.ALIVE_KEY,Integer.MAX_VALUE).addParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREAD_NAME_KEY,"test");
  URL consumerURL=URL.valueOf("dubbo://localhost:55555");
  consumerURL=consumerURL.addParameter(Constants.PID_KEY,"1234");
  consumerURL=consumerURL.addParameter(Constants.THREADPOOL_KEY,"foo");
  URL url=ClusterUtils.mergeUrl(providerURL,consumerURL.getParameters());
  Assert.assertFalse(url.hasParameter(Constants.THREADS_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREADS_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREADPOOL_KEY));
  Assert.assertFalse(url.hasParameter(Constants.CORE_THREADS_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.CORE_THREADS_KEY));
  Assert.assertFalse(url.hasParameter(Constants.QUEUES_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.QUEUES_KEY));
  Assert.assertFalse(url.hasParameter(Constants.ALIVE_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.ALIVE_KEY));
  Assert.assertFalse(url.hasParameter(Constants.THREAD_NAME_KEY));
  Assert.assertFalse(url.hasParameter(Constants.DEFAULT_KEY_PREFIX + Constants.THREAD_NAME_KEY));
  Assert.assertEquals(url.getPath(),"path");
  Assert.assertEquals(url.getUsername(),"username");
  Assert.assertEquals(url.getPassword(),"password");
  Assert.assertEquals(url.getParameter(Constants.PID_KEY),"1234");
  Assert.assertEquals(url.getParameter(Constants.THREADPOOL_KEY),"foo");
}
