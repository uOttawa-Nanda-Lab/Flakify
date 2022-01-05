@Test public void quoteParam() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.registerHelper("quote",new Helper<String>(){
    @Override public CharSequence apply(    final String context,    final Options options) throws IOException {
      return context;
    }
  }
);
  Template template=handlebars.compile("{{{quote \"2\\\"secs\"}}}");
  assertEquals("2\"secs",template.apply(new Object()));
}
