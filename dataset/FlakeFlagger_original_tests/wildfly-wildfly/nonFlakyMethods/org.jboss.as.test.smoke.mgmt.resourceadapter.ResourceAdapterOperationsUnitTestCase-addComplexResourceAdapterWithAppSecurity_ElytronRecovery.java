@Test public void addComplexResourceAdapterWithAppSecurity_ElytronRecovery() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.APPLICATION,ConnectionSecurityType.ELYTRON);
}
