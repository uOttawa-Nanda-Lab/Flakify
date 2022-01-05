@Test public void addComplexResourceAdapterWithSecurityDomain_NoRecoverySec() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.SECURITY_DOMAIN,null);
}
