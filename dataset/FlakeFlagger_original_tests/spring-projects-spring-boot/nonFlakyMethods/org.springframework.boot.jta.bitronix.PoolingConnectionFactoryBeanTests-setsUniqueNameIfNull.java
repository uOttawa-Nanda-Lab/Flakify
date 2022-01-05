@Test public void setsUniqueNameIfNull() throws Exception {
  this.bean.setBeanName("beanName");
  this.bean.afterPropertiesSet();
  assertThat(this.bean.getUniqueName(),equalTo("beanName"));
}
