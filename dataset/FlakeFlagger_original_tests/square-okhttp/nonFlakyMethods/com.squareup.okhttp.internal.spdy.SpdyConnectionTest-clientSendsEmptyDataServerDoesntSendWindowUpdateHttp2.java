@Test public void clientSendsEmptyDataServerDoesntSendWindowUpdateHttp2() throws Exception {
  clientSendsEmptyDataServerDoesntSendWindowUpdate(HTTP_20_DRAFT_09);
}
