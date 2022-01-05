@Test public void assertRegisterFailure() throws NoSuchFieldException {
  jobEventBus=new JobEventBus(new TestJobEventFailureConfiguration());
  assertIsRegistered(false);
}
