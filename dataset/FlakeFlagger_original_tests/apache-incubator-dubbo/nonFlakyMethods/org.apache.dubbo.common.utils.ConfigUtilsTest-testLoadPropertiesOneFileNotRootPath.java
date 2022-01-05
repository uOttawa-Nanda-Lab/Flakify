@Test public void testLoadPropertiesOneFileNotRootPath() throws Exception {
  Properties p=ConfigUtils.loadProperties("META-INF/dubbo/internal/org.apache.dubbo.common.threadpool.ThreadPool",false);
  Properties expected=new Properties();
  expected.put("fixed","org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool");
  expected.put("cached","org.apache.dubbo.common.threadpool.support.cached.CachedThreadPool");
  expected.put("limited","org.apache.dubbo.common.threadpool.support.limited.LimitedThreadPool");
  expected.put("eager","org.apache.dubbo.common.threadpool.support.eager.EagerThreadPool");
  assertEquals(expected,p);
}
