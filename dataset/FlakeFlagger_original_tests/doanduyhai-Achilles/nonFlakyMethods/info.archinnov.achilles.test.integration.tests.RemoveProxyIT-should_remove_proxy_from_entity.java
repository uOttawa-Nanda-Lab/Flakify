@Test public void should_remove_proxy_from_entity() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().randomId().name("Jonathan").buid();
  Tweet tweet=TweetTestBuilder.tweet().randomId().content("tweet").buid();
  bean.setWelcomeTweet(tweet);
  bean=manager.persist(bean);
  bean=manager.removeProxy(bean);
  assertThat(bean).isNotInstanceOf(Factory.class);
}
