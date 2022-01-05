@Test public void addComplexResourceAdapterWithSecurityDomain_ElytronRecovery() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.SECURITY_DOMAIN,ConnectionSecurityType.ELYTRON);
}
