@Test public void subProperties() throws Exception {
  this.source.put("x.y.my-sub.a.b","1");
  this.source.put("x.y.mySub.a.c","2");
  this.source.put("x.y.MY_SUB.a.d","3");
  this.resolver=new RelaxedPropertyResolver(this.environment,"x.y.");
  Map<String,Object> subProperties=this.resolver.getSubProperties("my-sub.");
  assertThat(subProperties.size(),equalTo(3));
  assertThat(subProperties.get("a.b"),equalTo((Object)"1"));
  assertThat(subProperties.get("a.c"),equalTo((Object)"2"));
  assertThat(subProperties.get("a.d"),equalTo((Object)"3"));
}
