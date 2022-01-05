@Test public void addComplexResourceAdapterWithElytron_NoRecoverySec() throws Exception {
  complexResourceAdapterAddTest(ConnectionSecurityType.ELYTRON,null);
}
