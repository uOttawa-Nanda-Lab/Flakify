@Test public void assertJobPropertiesEnumFromInvalidValue(){
  assertNull(JobProperties.JobPropertiesEnum.from("invalid"));
}
