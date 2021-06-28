package org.ares.spring.springcli.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityBuilder {

    public VelocityEngine createVelocityEngineSpring() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, System.getProperty("user.home") + "\\OpenTerminal\\spring\\");
        velocityEngine.init();

        return velocityEngine;
    }

}
