@Test(expected=FileNotFoundException.class) public void templateNotFound() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.compile(URI.create("template.hbs"));
}
