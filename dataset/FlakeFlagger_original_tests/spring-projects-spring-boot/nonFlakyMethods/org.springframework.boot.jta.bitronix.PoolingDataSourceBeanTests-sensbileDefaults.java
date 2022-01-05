@Test public void sensbileDefaults() throws Exception {
  assertThat(this.bean.getMaxPoolSize(),equalTo(10));
  assertThat(this.bean.getAutomaticEnlistingEnabled(),equalTo(true));
  assertThat(this.bean.isEnableJdbc4ConnectionTest(),equalTo(true));
}
