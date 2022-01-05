@Test public void shouldLoadWroManagerFactoryFromServletContextAttribute() throws Exception {
  final WroFilter filter=new WroFilter();
  final WroManagerFactory expectedManagerFactory=new BaseWroManagerFactory();
  final ServletContextAttributeHelper helper=new ServletContextAttributeHelper(mockServletContext);
  Mockito.when(mockServletContext.getAttribute(helper.getAttributeName(Attribute.MANAGER_FACTORY))).thenReturn(expectedManagerFactory);
  filter.setWroManagerFactory(null);
  filter.init(mockFilterConfig);
  Assert.assertSame(expectedManagerFactory,AbstractDecorator.getOriginalDecoratedObject(filter.getWroManagerFactory()));
}
