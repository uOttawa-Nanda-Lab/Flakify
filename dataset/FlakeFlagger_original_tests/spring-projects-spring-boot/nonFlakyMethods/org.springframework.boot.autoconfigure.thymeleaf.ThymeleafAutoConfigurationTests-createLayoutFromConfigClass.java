@Test public void createLayoutFromConfigClass() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  MockServletContext servletContext=new MockServletContext();
  context.setServletContext(servletContext);
  context.refresh();
  ThymeleafView view=(ThymeleafView)context.getBean(ThymeleafViewResolver.class).resolveViewName("view",Locale.UK);
  MockHttpServletResponse response=new MockHttpServletResponse();
  MockHttpServletRequest request=new MockHttpServletRequest();
  request.setAttribute(RequestContext.WEB_APPLICATION_CONTEXT_ATTRIBUTE,context);
  view.render(Collections.singletonMap("foo","bar"),request,response);
  String result=response.getContentAsString();
  assertTrue("Wrong result: " + result,result.contains("<title>Content</title>"));
  assertTrue("Wrong result: " + result,result.contains("<span>bar</span>"));
  context.close();
}
