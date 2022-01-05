@Test public void addComplexResourceAdapterWithAppSecurity_UserPassRecovery() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.APPLICATION,ConnectionSecurityType.USER_PASSWORD);
}
