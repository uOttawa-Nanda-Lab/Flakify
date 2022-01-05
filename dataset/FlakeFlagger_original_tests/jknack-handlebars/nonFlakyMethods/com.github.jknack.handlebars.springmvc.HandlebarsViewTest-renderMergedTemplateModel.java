@Test @SuppressWarnings("unchecked") public void renderMergedTemplateModel() throws Exception {
  Map<String,Object> model=createMock(Map.class);
  PrintWriter writer=createMock(PrintWriter.class);
  Template template=createMock(Template.class);
  Capture<Context> context=new Capture<Context>();
  template.apply(capture(context),isA(PrintWriter.class));
  HttpServletRequest request=createMock(HttpServletRequest.class);
  HttpServletResponse response=createMock(HttpServletResponse.class);
  expect(response.getWriter()).andReturn(writer);
  replay(template,model,request,response);
  HandlebarsView view=new HandlebarsView();
  view.setValueResolver(MapValueResolver.INSTANCE);
  view.setTemplate(template);
  view.renderMergedTemplateModel(model,request,response);
  assertNotNull(context.getValue());
  verify(template,model,request,response);
}
