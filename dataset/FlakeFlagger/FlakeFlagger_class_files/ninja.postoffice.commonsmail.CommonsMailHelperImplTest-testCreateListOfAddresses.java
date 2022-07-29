/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.postoffice.commonsmail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import ninja.postoffice.Mail;
import ninja.postoffice.common.MailImpl;
import ninja.postoffice.common.MailImplTestHelper;
import ninja.postoffice.common.Tuple;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.junit.Before;
import org.junit.Test;

public class CommonsMailHelperImplTest {

    CommonsmailHelper commonsmailHelper;

    @Before
    public void setUp() {

        commonsmailHelper = new CommonsmailHelperImpl();
    }

    @Test public void testCreateListOfAddresses() throws Exception{List<String> list=new ArrayList<String>();list.add("The user <the.user@me.com>");list.add("another.user@me.com");List<Tuple<String, String>> tupleList=commonsmailHelper.createListOfAddresses(list);assertEquals(2,tupleList.size());assertEquals("the.user@me.com",tupleList.get(0).x);assertEquals("The user",tupleList.get(0).y);assertEquals("another.user@me.com",tupleList.get(1).x);assertEquals(null,tupleList.get(1).y);}

    /**
     * Convenience method as commonsmail does not use generics...
     * 
     * @param list
     * @return
     */
    private List<InternetAddress> doConvertAdressesToInternetAddressList(List<?> list) {

        List<InternetAddress> returnList = new ArrayList<InternetAddress>();

        for (Object object : list) {
            InternetAddress internetAddress = (InternetAddress) object;
            returnList.add(internetAddress);
        }

        return returnList;

    }

}
