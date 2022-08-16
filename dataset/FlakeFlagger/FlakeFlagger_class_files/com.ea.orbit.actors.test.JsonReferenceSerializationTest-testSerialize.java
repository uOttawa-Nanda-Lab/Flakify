/*
Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1.  Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
2.  Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
    its contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.ea.orbit.actors.test;

import com.ea.orbit.actors.runtime.ReferenceFactory;
import com.ea.orbit.actors.providers.json.ActorReferenceModule;
import com.ea.orbit.actors.providers.json.ReflectionReferenceFactory;
import com.ea.orbit.actors.test.actors.ISomeActor;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonReferenceSerializationTest
{
    private ReferenceFactory factory = new ReflectionReferenceFactory();

    @Test
    public void testSerialize() throws Exception
    {
        String json = "\"123\"";
        ISomeActor actor = factory.getReference(ISomeActor.class, "123");
        ObjectMapper mapper = createMapper();
        assertEquals(json, mapper.writeValueAsString(actor));
    }

    public static class ComplexData
    {
        ISomeActor a1;
        ISomeActor a2;
        List<ISomeActor> list;

        @Override
        public boolean equals(final Object o)
        {
            if (this == o) return true;
            if (!(o instanceof ComplexData)) return false;

            final ComplexData that = (ComplexData) o;

            if (!a1.equals(that.a1)) return false;
            if (!a2.equals(that.a2)) return false;
            if (!list.equals(that.list)) return false;

            return true;
        }
    }

    private ObjectMapper createMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        mapper.registerModule(new ActorReferenceModule(factory));
        return mapper;
    }
}
