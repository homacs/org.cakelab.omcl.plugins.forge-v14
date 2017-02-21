package org.cakelab.omcl.plugins.forge.v11_15_1_1902;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

import org.cakelab.omcl.plugins.StubException;
import org.cakelab.omcl.plugins.interfaces.ServicesListener;

public class ForgeServicesStub extends org.cakelab.omcl.plugins.forge.ForgeServicesStub {
	private static final String SERVICES_PACKAGE = "org.cakelab.omcl.plugins.forge.v11_15_1_1902.migrates";
	private static final String SERVICES_CLASS = SERVICES_PACKAGE + ".ForgeServices";
	private Object serviceInstance;
	private Method method_installClient;


	public ForgeServicesStub(ClassLoader classLoader, Object serviceInstance, Method method_installClient) {
		super(classLoader);
		this.serviceInstance = serviceInstance;
		this.method_installClient = method_installClient;
	}


	public static ForgeServicesStub create(File jar, ServicesListener listener) throws StubException, IOException {
		
		final String className = SERVICES_CLASS;
		final String methodName_install = "installClient";
		
		Object stubInstance;
		Method method_installClient;
		URLClassLoader classLoader = null;
		if (jar.exists()) {
			try {
				classLoader = createURLClassLoader(ForgeServicesStub.class, jar, new String[]{SERVICES_PACKAGE});
				enterPluginContext(classLoader);
				
				Class<?> stubClass = classLoader.loadClass(className);
				
				Constructor<?> constructor = stubClass.getConstructor(ServicesListener.class);
				
				stubInstance = constructor.newInstance(listener);
				
				
				// method void initWorkDir(File workDir) 
				method_installClient = stubClass.getDeclaredMethod (methodName_install, File.class);
			} catch (Throwable t) {
				throw new StubException("jar " + jar.getName() + " incompatible", t);
			} finally {
				leavePluginContext(classLoader);
			}
		} else {
			// bail
			throw new IOException("Could not load forge installer jar-file at given location '" + jar + "'");
		}		
		
		
		return new ForgeServicesStub(classLoader, stubInstance, method_installClient);
	}

	
	public boolean installClient(File workDir) throws StubException {
		super.enterPluginContext();
		try {
			return (Boolean) this.method_installClient.invoke(serviceInstance, workDir);
		}catch(Throwable t) {
			if (t instanceof StubException) t = t.getCause();
			throw new StubException(t);
		} finally {
			super.leavePluginContext();
		}
	}
	
}
