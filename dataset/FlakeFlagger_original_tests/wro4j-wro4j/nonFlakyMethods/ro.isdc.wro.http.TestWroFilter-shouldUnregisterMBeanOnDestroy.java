@Test public void shouldUnregisterMBeanOnDestroy() throws Exception {
  when(mockMBeanServer.isRegistered(Mockito.any(ObjectName.class))).thenReturn(true);
  victim.init(mockFilterConfig);
  victim.destroy();
  verify(mockMBeanServer).unregisterMBean(Mockito.any(ObjectName.class));
}
