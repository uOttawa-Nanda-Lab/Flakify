@Test public void findValue(){
  String missingMetaDataSessionId="no-meta-data";
  String missingAttributesSessionId="no-attributes";
  String existingSessionId="existing";
  SessionCreationMetaData creationMetaData=mock(SessionCreationMetaData.class);
  SessionAccessMetaData accessMetaData=mock(SessionAccessMetaData.class);
  AtomicReference<Object> localContext=new AtomicReference<>();
  InfinispanSessionMetaData<Object> metaData=new InfinispanSessionMetaData<>(creationMetaData,accessMetaData,localContext);
  Object attributes=new Object();
  when(this.metaDataFactory.findValue(missingMetaDataSessionId)).thenReturn(null);
  when(this.metaDataFactory.findValue(missingAttributesSessionId)).thenReturn(metaData);
  when(this.metaDataFactory.findValue(existingSessionId)).thenReturn(metaData);
  when(this.attributesFactory.findValue(missingAttributesSessionId)).thenReturn(null);
  when(this.attributesFactory.findValue(existingSessionId)).thenReturn(attributes);
  Map.Entry<InfinispanSessionMetaData<Object>,Object> missingMetaDataResult=this.factory.findValue(missingMetaDataSessionId);
  Map.Entry<InfinispanSessionMetaData<Object>,Object> missingAttributesResult=this.factory.findValue(missingAttributesSessionId);
  Map.Entry<InfinispanSessionMetaData<Object>,Object> existingSessionResult=this.factory.findValue(existingSessionId);
  assertNull(missingMetaDataResult);
  assertNull(missingAttributesResult);
  assertNotNull(existingSessionResult);
  assertSame(metaData,existingSessionResult.getKey());
  assertSame(attributes,existingSessionResult.getValue());
}
