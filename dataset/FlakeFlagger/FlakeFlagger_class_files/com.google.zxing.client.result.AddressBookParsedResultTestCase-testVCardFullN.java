/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link AddressBookParsedResult}.
 *
 * @author Sean Owen
 */
public final class AddressBookParsedResultTestCase extends Assert {

  @Test public void testVCardFullN(){doTest("BEGIN:VCARD\r\nVERSION:2.1\r\nN:Owen;Sean;T;Mr.;Esq.\r\nEND:VCARD",null,new String[]{"Mr. Sean T Owen Esq."},null,null,null,null,null,null,null,null);}

  private static void doTest(String contents,
                             String title,
                             String[] names,
                             String pronunciation,
                             String[] addresses,
                             String[] emails,
                             String[] phoneNumbers,
                             String org,
                             String[] urls,
                             String birthday,
                             String note) {
    Result fakeResult = new Result(contents, null, null, BarcodeFormat.QR_CODE);
    ParsedResult result = ResultParser.parseResult(fakeResult);
    assertSame(ParsedResultType.ADDRESSBOOK, result.getType());
    AddressBookParsedResult addressResult = (AddressBookParsedResult) result;
    assertEquals(title, addressResult.getTitle());
    assertArrayEquals(names, addressResult.getNames());
    assertEquals(pronunciation, addressResult.getPronunciation());
    assertArrayEquals(addresses, addressResult.getAddresses());
    assertArrayEquals(emails, addressResult.getEmails());
    assertArrayEquals(phoneNumbers, addressResult.getPhoneNumbers());
    assertEquals(org, addressResult.getOrg());
    assertArrayEquals(urls, addressResult.getURLs());
    assertEquals(birthday, addressResult.getBirthday());
    assertEquals(note, addressResult.getNote());
  }

}