@Test public void issue95() throws IOException {
  TemplateLoader loader=new ClassPathTemplateLoader("/issue95");
  Handlebars handlebars=new Handlebars(loader);
  handlebars.setAllowInfiniteLoops(true);
  Template template=handlebars.compile(URI.create("hbs/start"));
  assertNotNull(template);
}
