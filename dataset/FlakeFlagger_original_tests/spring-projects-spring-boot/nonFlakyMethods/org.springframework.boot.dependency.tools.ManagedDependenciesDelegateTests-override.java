@Test public void override() throws Exception {
  assertThat(this.dependencies.find("org.sample","sample02").toString(),equalTo("org.sample:sample02:2.0.0"));
}
