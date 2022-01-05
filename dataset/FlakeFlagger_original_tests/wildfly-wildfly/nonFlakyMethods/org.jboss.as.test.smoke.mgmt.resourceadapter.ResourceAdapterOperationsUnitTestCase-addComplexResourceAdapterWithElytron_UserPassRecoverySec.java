@Test public void addComplexResourceAdapterWithElytron_UserPassRecoverySec() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.ELYTRON,ConnectionSecurityType.USER_PASSWORD);
}
