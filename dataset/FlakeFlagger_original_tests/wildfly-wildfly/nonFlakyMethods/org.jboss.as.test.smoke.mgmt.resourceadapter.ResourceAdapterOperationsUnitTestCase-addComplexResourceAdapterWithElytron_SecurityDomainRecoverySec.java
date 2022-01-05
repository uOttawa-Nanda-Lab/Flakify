@Test public void addComplexResourceAdapterWithElytron_SecurityDomainRecoverySec() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.ELYTRON,ConnectionSecurityType.SECURITY_DOMAIN);
}
