@Test public void testThatPostArticleReturnsOkWhenArticleDaoReturnsTrue(){
  when(articleDao.postArticle(null,null)).thenReturn(true);
  Result result=apiController.postArticleJson(null,null);
  assertEquals(200,result.getStatusCode());
}
