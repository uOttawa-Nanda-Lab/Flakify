@Test(expected=HandlebarsException.class) public void partialNotFound() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.compile("{{> text}}");
}
