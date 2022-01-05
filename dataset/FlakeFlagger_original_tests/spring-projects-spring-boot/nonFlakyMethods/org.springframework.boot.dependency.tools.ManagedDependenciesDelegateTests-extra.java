@Test public void extra() throws Exception {
  assertThat(this.dependencies.find("org.sample","sample03").toString(),equalTo("org.sample:sample03:2.0.0"));
}
