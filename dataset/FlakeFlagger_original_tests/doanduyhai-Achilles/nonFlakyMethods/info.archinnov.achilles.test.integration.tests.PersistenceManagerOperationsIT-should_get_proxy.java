@Test public void should_get_proxy() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  manager.persist(entity);
  CompleteBean foundBean=manager.getProxy(CompleteBean.class,entity.getId());
  assertThat(foundBean).isNotNull();
  CompleteBean realObject=manager.removeProxy(foundBean);
  assertThat(realObject.getId()).isEqualTo(entity.getId());
  assertThat(realObject.getName()).isNull();
  assertThat(realObject.getAge()).isNull();
  assertThat(realObject.getFriends()).isNull();
  assertThat(realObject.getFollowers()).isNull();
  assertThat(realObject.getPreferences()).isNull();
  assertThat(foundBean.getId()).isEqualTo(entity.getId());
  assertThat(foundBean.getName()).isEqualTo("DuyHai");
  assertThat(foundBean.getAge()).isEqualTo(35L);
  assertThat(foundBean.getFriends()).containsExactly("foo","bar");
  assertThat(foundBean.getFollowers()).contains("George","Paul");
  assertThat(foundBean.getPreferences()).containsKey(1);
  assertThat(foundBean.getPreferences()).containsKey(2);
  assertThat(foundBean.getPreferences()).containsKey(3);
  assertThat(foundBean.getPreferences()).containsValue("FR");
  assertThat(foundBean.getPreferences()).containsValue("Paris");
  assertThat(foundBean.getPreferences()).containsValue("75014");
}
