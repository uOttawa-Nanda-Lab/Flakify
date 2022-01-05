@Test public void testSslLogging() throws Exception {
  LOG.info("Testing sign");
  certMan.configs.getConfigsMap().put(Configuration.PASSPHRASE_KEY,"123123");
  LOG.info("key dir = " + certMan.configs.getConfigsMap().get(Configuration.SRVR_KSTR_DIR_KEY));
  SignCertResponse signAgentCrt=certMan.signAgentCrt("somehost","gdfgdfg","123123");
  LOG.info("-------------RESPONCE-------------");
  LOG.info("-------------MESSAGE--------------");
  LOG.info(signAgentCrt.getMessage());
  LOG.info("---------------------------------");
  LOG.info("-------------RESULT--------------");
  LOG.info(signAgentCrt.getResult());
  LOG.info("---------------------------------");
  assertTrue(SignCertResponse.ERROR_STATUS.equals(signAgentCrt.getResult()));
}
