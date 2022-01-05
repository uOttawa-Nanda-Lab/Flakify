@Test public void testThatSetupInitializationCreatesTheTestUsers(){
  Injector applicationInjector=getInjector();
  ArticleDao articleDao=applicationInjector.getInstance(ArticleDao.class);
  assertEquals(articleDao.getAllArticles().articles.size(),3);
}
