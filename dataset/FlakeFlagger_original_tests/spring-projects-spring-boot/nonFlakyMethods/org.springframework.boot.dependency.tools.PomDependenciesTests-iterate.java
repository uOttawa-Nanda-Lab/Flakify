@Test public void iterate() throws Exception {
  Iterator<Dependency> iterator=this.dependencies.iterator();
  assertThat(iterator.next().toString(),equalTo("org.sample:sample01:1.0.0"));
  assertThat(iterator.next().toString(),equalTo("org.sample:sample02:1.0.0"));
  assertThat(iterator.next().toString(),equalTo("org.springframework.boot:spring-boot:1.0.0.BUILD-SNAPSHOT"));
  assertThat(iterator.hasNext(),equalTo(false));
}
