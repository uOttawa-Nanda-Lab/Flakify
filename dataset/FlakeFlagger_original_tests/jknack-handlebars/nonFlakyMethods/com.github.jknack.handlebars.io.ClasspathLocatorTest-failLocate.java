@Test(expected=FileNotFoundException.class) public void failLocate() throws IOException {
  TemplateLoader locator=new ClassPathTemplateLoader();
  locator.load(URI.create("notExist"));
}
