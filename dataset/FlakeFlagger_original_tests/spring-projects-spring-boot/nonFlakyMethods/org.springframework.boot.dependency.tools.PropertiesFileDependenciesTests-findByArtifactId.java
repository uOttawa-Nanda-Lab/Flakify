@Test public void findByArtifactId() throws Exception {
  assertThat(this.dependencies.find("sample02").toString(),equalTo("org.sample:sample02:1.0.0"));
}
