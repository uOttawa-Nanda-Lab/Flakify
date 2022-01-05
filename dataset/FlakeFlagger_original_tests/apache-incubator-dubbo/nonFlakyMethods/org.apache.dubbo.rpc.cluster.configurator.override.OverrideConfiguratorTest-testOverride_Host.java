@Test public void testOverride_Host(){
  OverrideConfigurator configurator=new OverrideConfigurator(URL.valueOf("override://" + NetUtils.getLocalHost() + "/com.foo.BarService?timeout=200"));
  URL url=configurator.configure(URL.valueOf(UrlConstant.URL_CONSUMER));
  Assert.assertEquals("200",url.getParameter("timeout"));
  url=configurator.configure(URL.valueOf(UrlConstant.URL_ONE));
  Assert.assertEquals("200",url.getParameter("timeout"));
  AbsentConfigurator configurator1=new AbsentConfigurator(URL.valueOf("override://10.20.153.10/com.foo.BarService?timeout=200"));
  url=configurator1.configure(URL.valueOf(UrlConstant.APPLICATION_BAR_SIDE_CONSUMER_10));
  Assert.assertNull(url.getParameter("timeout"));
  url=configurator1.configure(URL.valueOf(UrlConstant.TIMEOUT_1000_SIDE_CONSUMER_10));
  Assert.assertEquals("1000",url.getParameter("timeout"));
}
