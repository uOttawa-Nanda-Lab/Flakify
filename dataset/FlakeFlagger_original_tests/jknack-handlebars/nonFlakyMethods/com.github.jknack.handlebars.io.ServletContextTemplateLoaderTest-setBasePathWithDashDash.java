@Test public void setBasePathWithDashDash() throws IOException {
  InputStream is=createMock(InputStream.class);
  is.close();
  expectLastCall();
  ServletContext servletContext=createMock(ServletContext.class);
  expect(servletContext.getResourceAsStream("/mustache/specs/comments.yml")).andReturn(is);
  replay(servletContext,is);
  TemplateLoader locator=new ServletContextTemplateLoader(servletContext,"/mustache/specs",".yml");
  Reader reader=locator.load(URI.create("comments"));
  assertNotNull(reader);
  IOUtils.closeQuietly(reader);
  verify(servletContext,is);
}
