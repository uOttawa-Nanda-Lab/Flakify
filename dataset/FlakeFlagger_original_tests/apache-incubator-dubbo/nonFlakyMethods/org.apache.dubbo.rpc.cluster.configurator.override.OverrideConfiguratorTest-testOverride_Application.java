@Test public void testOverride_Application(){
  OverrideConfigurator configurator=new OverrideConfigurator(URL.valueOf("override://foo@0.0.0.0/com.foo.BarService?timeout=200"));
  URL url=configurator.configure(URL.valueOf(UrlConstant.URL_CONSUMER));
  Assert.assertEquals("200",url.getParameter("timeout"));
  url=configurator.configure(URL.valueOf(UrlConstant.URL_ONE));
  Assert.assertEquals("200",url.getParameter("timeout"));
  url=configurator.configure(URL.valueOf(UrlConstant.APPLICATION_BAR_SIDE_CONSUMER_11));
  Assert.assertNull(url.getParameter("timeout"));
  url=configurator.configure(URL.valueOf(UrlConstant.TIMEOUT_1000_SIDE_CONSUMER_11));
  Assert.assertEquals("1000",url.getParameter("timeout"));
}
