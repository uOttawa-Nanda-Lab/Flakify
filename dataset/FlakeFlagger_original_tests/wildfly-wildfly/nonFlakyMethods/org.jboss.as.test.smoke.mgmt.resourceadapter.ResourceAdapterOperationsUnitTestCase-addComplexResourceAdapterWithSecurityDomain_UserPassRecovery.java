@Test public void addComplexResourceAdapterWithSecurityDomain_UserPassRecovery() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.SECURITY_DOMAIN,ConnectionSecurityType.USER_PASSWORD);
}
