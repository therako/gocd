/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.agent.testhelpers;

import java.io.File;

import com.thoughtworks.go.security.Registration;
import com.thoughtworks.go.security.X509CertificateGenerator;
import com.thoughtworks.go.util.TestFileUtil;

public class AgentCertificateMother {
    private static String authorityKeystorePath = "tempAuthorityKeystore";

    public static Registration agentCertificate() {
        File tempKeystoreFile = TestFileUtil.createUniqueTempFile(authorityKeystorePath);
        X509CertificateGenerator certificateGenerator = new X509CertificateGenerator();
        certificateGenerator.createAndStoreCACertificates(tempKeystoreFile);
        return certificateGenerator.createAgentCertificate(tempKeystoreFile, "blah");
    }

}
