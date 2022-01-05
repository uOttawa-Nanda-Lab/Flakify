@Test public void serverSendsEmptyDataClientDoesntSendWindowUpdateHttp2() throws Exception {
  serverSendsEmptyDataClientDoesntSendWindowUpdate(HTTP_20_DRAFT_09);
}
