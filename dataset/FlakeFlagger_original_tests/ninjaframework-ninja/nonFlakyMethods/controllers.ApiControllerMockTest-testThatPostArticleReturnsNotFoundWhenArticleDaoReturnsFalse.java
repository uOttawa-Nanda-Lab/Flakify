@Test public void testThatPostArticleReturnsNotFoundWhenArticleDaoReturnsFalse(){
  when(articleDao.postArticle(null,null)).thenReturn(false);
  Result result=apiController.postArticleJson(null,null);
  assertEquals(404,result.getStatusCode());
}
