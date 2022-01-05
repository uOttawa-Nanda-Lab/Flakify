@Test public void testGetAndPostArticleViaJson() throws Exception {
  String response=ninjaTestBrowser.makeJsonRequest(getServerAddress() + "api/bob@gmail.com/articles.json");
  System.out.println("response: " + response);
  ArticlesDto articlesDto=getGsonWithLongToDateParsing().fromJson(response,ArticlesDto.class);
  assertEquals(3,articlesDto.articles.size());
  ArticleDto articleDto=new ArticleDto();
  articleDto.content="contentcontent";
  articleDto.title="new title new title";
  response=ninjaTestBrowser.postJson(getServerAddress() + "api/bob@gmail.com/article.json",articleDto);
  assertTrue(response.contains("Error. Forbidden."));
  doLogin();
  response=ninjaTestBrowser.postJson(getServerAddress() + "api/bob@gmail.com/article.json",articleDto);
  assertFalse(response.contains("Error. Forbidden."));
  response=ninjaTestBrowser.makeJsonRequest(getServerAddress() + "api/bob@gmail.com/articles.json");
  articlesDto=getGsonWithLongToDateParsing().fromJson(response,ArticlesDto.class);
  assertEquals(4,articlesDto.articles.size());
}
