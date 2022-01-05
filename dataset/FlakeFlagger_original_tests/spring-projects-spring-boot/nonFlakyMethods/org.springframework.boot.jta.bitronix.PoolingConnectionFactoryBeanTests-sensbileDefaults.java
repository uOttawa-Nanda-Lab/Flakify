@Test public void sensbileDefaults() throws Exception {
  assertThat(this.bean.getMaxPoolSize(),equalTo(10));
  assertThat(this.bean.getTestConnections(),equalTo(true));
  assertThat(this.bean.getAutomaticEnlistingEnabled(),equalTo(true));
  assertThat(this.bean.getAllowLocalTransactions(),equalTo(true));
}
