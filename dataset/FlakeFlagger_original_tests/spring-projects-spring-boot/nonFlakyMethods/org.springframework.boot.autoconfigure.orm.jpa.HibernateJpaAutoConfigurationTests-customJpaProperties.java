@Test public void customJpaProperties() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jpa.properties.a:b","spring.jpa.properties.a.b:c","spring.jpa.properties.c:d");
  setupTestConfiguration();
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean bean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Map<String,Object> map=bean.getJpaPropertyMap();
  assertThat(map.get("a"),equalTo((Object)"b"));
  assertThat(map.get("c"),equalTo((Object)"d"));
  assertThat(map.get("a.b"),equalTo((Object)"c"));
}
