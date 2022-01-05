@Test public void defaultLoad() throws IOException {
  InputStream is=createMock(InputStream.class);
  is.close();
  expectLastCall();
  ServletContext servletContext=createMock(ServletContext.class);
  expect(servletContext.getResourceAsStream("/template.hbs")).andReturn(is);
  replay(servletContext,is);
  TemplateLoader locator=new ServletContextTemplateLoader(servletContext);
  Reader reader=locator.load(URI.create("template"));
  assertNotNull(reader);
  IOUtils.closeQuietly(reader);
  verify(servletContext,is);
}
