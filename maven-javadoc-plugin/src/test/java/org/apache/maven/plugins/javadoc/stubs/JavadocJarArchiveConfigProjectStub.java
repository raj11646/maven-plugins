package org.apache.maven.plugins.javadoc.stubs;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.model.Build;
import org.apache.maven.model.Scm;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Project stub for testing archive configuration.
 */
public class JavadocJarArchiveConfigProjectStub
    extends MavenProjectStub
{
    private Scm scm;

    public JavadocJarArchiveConfigProjectStub()
    {
        File projectFile = new File( getBasedir(), "javadocjar-archive-config.xml" );
        readModel( new File( getBasedir(), "javadocjar-archive-config.xml" ) );

        setFile( projectFile );

        setGroupId( getModel().getGroupId() );
        setArtifactId( getModel().getArtifactId() );
        setVersion( getModel().getVersion() );
        setName( getModel().getName() );
        setUrl( getModel().getUrl() );
        setPackaging( getModel().getPackaging() );

        Scm scm = new Scm();
        scm.setConnection( "scm:svn:http://svn.apache.org/maven/sample/trunk" );
        setScm( scm );

        JavadocPluginArtifactStub artifact =
                new JavadocPluginArtifactStub( getGroupId(), getArtifactId(), getVersion(), getPackaging() );
        artifact.setArtifactHandler( new DefaultArtifactHandlerStub() );
        artifact.setType( "jar" );
        artifact.setBaseVersion( "1.0-SNAPSHOT" );
        setArtifact( artifact );

        Build build = new Build();
        build.setFinalName( "javadocjar-archive-config" );
        build.setDirectory( super.getBasedir() + "/target/test/unit/javadocjar-archive-config/target" );
        setBuild( build );

        List<String> compileSourceRoots = new ArrayList<>();
        compileSourceRoots.add( getBasedir() + "/javadocjar/def" );
        setCompileSourceRoots( compileSourceRoots );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scm getScm()
    {
        return scm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScm(Scm scm)
    {
        this.scm = scm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getBasedir()
    {
        return new File( super.getBasedir() + "/src/test/resources/unit/javadocjar-archive-config" );
    }
}