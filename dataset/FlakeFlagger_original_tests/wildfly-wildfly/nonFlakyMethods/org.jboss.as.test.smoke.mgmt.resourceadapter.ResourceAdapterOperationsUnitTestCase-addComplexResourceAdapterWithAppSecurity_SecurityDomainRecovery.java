@Test public void addComplexResourceAdapterWithAppSecurity_SecurityDomainRecovery() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.APPLICATION,ConnectionSecurityType.SECURITY_DOMAIN);
}
