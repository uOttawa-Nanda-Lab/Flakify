@Test public void testLoadPropertiesMultiFileNotRootPath() throws Exception {
  Properties p=ConfigUtils.loadProperties("META-INF/dubbo/internal/org.apache.dubbo.common.status.StatusChecker",true);
  Properties expected=new Properties();
  expected.put("memory","org.apache.dubbo.common.status.support.MemoryStatusChecker");
  expected.put("load","org.apache.dubbo.common.status.support.LoadStatusChecker");
  expected.put("aa","12");
  assertEquals(expected,p);
}
