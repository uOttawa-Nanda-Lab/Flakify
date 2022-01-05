@Test public void quoteHash() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.registerHelper("quote",new Helper<String>(){
    @Override public CharSequence apply(    final String context,    final Options options) throws IOException {
      return (CharSequence)options.hash.get("q");
    }
  }
);
  Template template=handlebars.compile("{{{quote q=\"2\\\"secs\"}}}");
  assertEquals("2\"secs",template.apply(null));
}
