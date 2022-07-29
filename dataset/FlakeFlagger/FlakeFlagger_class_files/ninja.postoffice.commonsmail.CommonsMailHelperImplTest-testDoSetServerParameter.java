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

    @Test public void testDoSetServerParameter() throws Exception{Mail mail=MailImplTestHelper.getMailImplWithDemoContent();MultiPartEmail multiPartEmail=commonsmailHelper.createMultiPartEmailWithContent(mail);commonsmailHelper.doSetServerParameter(multiPartEmail,"mail.superserver.com",33,true,"username","password",true);assertEquals("33",multiPartEmail.getSmtpPort());assertEquals("mail.superserver.com",multiPartEmail.getHostName());assertEquals(true,multiPartEmail.getMailSession().getDebug());}

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
