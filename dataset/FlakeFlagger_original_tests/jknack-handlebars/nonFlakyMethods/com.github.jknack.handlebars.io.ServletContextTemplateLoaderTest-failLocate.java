@Test(expected=FileNotFoundException.class) public void failLocate() throws IOException {
  ServletContext servletContext=createMock(ServletContext.class);
  expect(servletContext.getResourceAsStream("/notExist.hbs")).andReturn(null);
  replay(servletContext);
  TemplateLoader locator=new ServletContextTemplateLoader(servletContext);
  locator.load(URI.create("notExist"));
  verify(servletContext);
}
