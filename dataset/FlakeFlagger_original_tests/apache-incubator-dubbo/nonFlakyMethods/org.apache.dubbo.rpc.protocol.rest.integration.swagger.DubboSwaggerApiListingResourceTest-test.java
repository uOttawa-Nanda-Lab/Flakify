@Test public void test() throws Exception {
  DubboSwaggerApiListingResource resource=new DubboSwaggerApiListingResource();
  app=mock(Application.class);
  sc=mock(ServletConfig.class);
  Set<Class<?>> sets=new HashSet<Class<?>>();
  sets.add(SwaggerService.class);
  when(sc.getServletContext()).thenReturn(mock(ServletContext.class));
  when(app.getClasses()).thenReturn(sets);
  Response response=resource.getListingJson(app,sc,null,new ResteasyUriInfo(new URI("http://rest.test")));
  Assert.assertNotNull(response);
  Swagger swagger=(Swagger)response.getEntity();
  Assert.assertEquals("SwaggerService",swagger.getTags().get(0).getName());
  Assert.assertEquals("/demoService/hello",swagger.getPaths().keySet().toArray()[0].toString());
}
