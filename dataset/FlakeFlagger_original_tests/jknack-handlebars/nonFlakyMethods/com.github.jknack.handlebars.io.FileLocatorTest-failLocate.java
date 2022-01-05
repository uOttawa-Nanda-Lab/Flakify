@Test(expected=FileNotFoundException.class) public void failLocate() throws IOException {
  TemplateLoader locator=new FileTemplateLoader(new File("src/test/resources"));
  locator.load(URI.create("notExist"));
}
