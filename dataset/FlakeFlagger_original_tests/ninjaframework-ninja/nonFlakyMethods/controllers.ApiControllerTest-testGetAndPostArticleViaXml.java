@Test public void testGetAndPostArticleViaXml() throws Exception {
  String response=ninjaTestBrowser.makeXmlRequest(getServerAddress() + "api/bob@gmail.com/articles.xml");
  System.out.println("response xml: " + response);
  JacksonXmlModule module=new JacksonXmlModule();
  module.setDefaultUseWrapper(false);
  XmlMapper xmlMapper=new XmlMapper(module);
  ArticlesDto articlesDto=xmlMapper.readValue(response,ArticlesDto.class);
  assertEquals(3,articlesDto.articles.size());
  ArticleDto articleDto=new ArticleDto();
  articleDto.content="contentcontent";
  articleDto.title="new title new title";
  response=ninjaTestBrowser.postXml(getServerAddress() + "api/bob@gmail.com/article.xml",articleDto);
  assertTrue(response.contains("Error. Forbidden."));
  doLogin();
  response=ninjaTestBrowser.postXml(getServerAddress() + "api/bob@gmail.com/article.xml",articleDto);
  assertFalse(response.contains("Error. Forbidden."));
  response=ninjaTestBrowser.makeXmlRequest(getServerAddress() + "api/bob@gmail.com/articles.xml");
  articlesDto=xmlMapper.readValue(response,ArticlesDto.class);
  assertEquals(4,articlesDto.articles.size());
}
