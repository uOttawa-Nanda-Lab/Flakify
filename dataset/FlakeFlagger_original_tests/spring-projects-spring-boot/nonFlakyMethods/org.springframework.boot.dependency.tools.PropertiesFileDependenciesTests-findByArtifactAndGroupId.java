@Test public void findByArtifactAndGroupId() throws Exception {
  assertThat(this.dependencies.find("org.sample","sample02").toString(),equalTo("org.sample:sample02:1.0.0"));
}
