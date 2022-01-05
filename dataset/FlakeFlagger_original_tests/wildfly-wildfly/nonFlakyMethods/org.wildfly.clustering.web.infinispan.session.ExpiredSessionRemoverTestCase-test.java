@Test public void test(){
  SessionFactory<UUID,UUID,Object> factory=mock(SessionFactory.class);
  SessionMetaDataFactory<UUID,Object> metaDataFactory=mock(SessionMetaDataFactory.class);
  SessionAttributesFactory<UUID> attributesFactory=mock(SessionAttributesFactory.class);
  SessionExpirationListener listener=mock(SessionExpirationListener.class);
  ImmutableSessionAttributes expiredAttributes=mock(ImmutableSessionAttributes.class);
  ImmutableSessionMetaData validMetaData=mock(ImmutableSessionMetaData.class);
  ImmutableSessionMetaData expiredMetaData=mock(ImmutableSessionMetaData.class);
  ImmutableSession expiredSession=mock(ImmutableSession.class);
  String missingSessionId="missing";
  String expiredSessionId="expired";
  String validSessionId="valid";
  UUID expiredMetaDataValue=UUID.randomUUID();
  UUID expiredAttributesValue=UUID.randomUUID();
  UUID validMetaDataValue=UUID.randomUUID();
  ExpiredSessionRemover<UUID,UUID,Object> subject=new ExpiredSessionRemover<>(factory);
  try (Registration regisration=subject.register(listener)){
    when(factory.getMetaDataFactory()).thenReturn(metaDataFactory);
    when(factory.getAttributesFactory()).thenReturn(attributesFactory);
    when(metaDataFactory.tryValue(missingSessionId)).thenReturn(null);
    when(metaDataFactory.tryValue(expiredSessionId)).thenReturn(expiredMetaDataValue);
    when(metaDataFactory.tryValue(validSessionId)).thenReturn(validMetaDataValue);
    when(metaDataFactory.createImmutableSessionMetaData(expiredSessionId,expiredMetaDataValue)).thenReturn(expiredMetaData);
    when(metaDataFactory.createImmutableSessionMetaData(validSessionId,validMetaDataValue)).thenReturn(validMetaData);
    when(expiredMetaData.isExpired()).thenReturn(true);
    when(validMetaData.isExpired()).thenReturn(false);
    when(attributesFactory.findValue(expiredSessionId)).thenReturn(expiredAttributesValue);
    when(attributesFactory.createImmutableSessionAttributes(expiredSessionId,expiredAttributesValue)).thenReturn(expiredAttributes);
    when(factory.createImmutableSession(same(expiredSessionId),same(expiredMetaData),same(expiredAttributes))).thenReturn(expiredSession);
    subject.remove(missingSessionId);
    subject.remove(expiredSessionId);
    subject.remove(validSessionId);
    verify(factory).remove(expiredSessionId);
    verify(factory,never()).remove(missingSessionId);
    verify(factory,never()).remove(validSessionId);
    verify(listener).sessionExpired(expiredSession);
  }
 }
