@Test public void iterator() throws Exception {
  Iterator<Dependency> iterator=this.dependencies.iterator();
  assertThat(iterator.next().toString(),equalTo("org.sample:sample01:1.0.0"));
  assertThat(iterator.next().toString(),equalTo("org.sample:sample02:2.0.0"));
  assertThat(iterator.next().toString(),equalTo("org.springframework.boot:spring-boot:1.0.0.BUILD-SNAPSHOT"));
  assertThat(iterator.next().toString(),equalTo("org.sample:sample03:2.0.0"));
  assertThat(iterator.hasNext(),equalTo(false));
}
