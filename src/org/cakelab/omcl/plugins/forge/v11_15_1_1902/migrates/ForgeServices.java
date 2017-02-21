package org.cakelab.omcl.plugins.forge.v11_15_1_1902.migrates;


import java.io.File;

import org.cakelab.omcl.plugins.ServicesBase;
import org.cakelab.omcl.plugins.StubException;
import org.cakelab.omcl.plugins.interfaces.ServicesListener;

public class ForgeServices extends ServicesBase {

	/** 
	 * To be called by the stub.
	 * 
	 * @param listener
	 * @throws StubException
	 */
	public ForgeServices(ServicesListener listener) throws StubException {
		super(listener);
	}

	
	public boolean installClient(File workDir) throws StubException {
		try {
			super.prepareContext();
	
			ModdedClientInstall clientInstall = new ModdedClientInstall();
			return clientInstall.run(workDir, listener);
		} catch (Throwable t) {
			throw new StubException(t);
		} finally {
			super.resetContext();
		}
	}
}
