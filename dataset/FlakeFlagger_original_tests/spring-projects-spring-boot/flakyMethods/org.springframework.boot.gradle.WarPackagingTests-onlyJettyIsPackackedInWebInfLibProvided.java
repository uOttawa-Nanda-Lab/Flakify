@Test public void onlyJettyIsPackackedInWebInfLibProvided() throws IOException {
  checkWebInfEntriesForServletContainer("jetty",JETTY_EXPECTED_IN_WEB_INF_LIB_PROVIDED);
}
