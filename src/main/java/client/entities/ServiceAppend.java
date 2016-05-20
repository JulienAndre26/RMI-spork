package client.entities;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.ServiceInterface;

/**
 * The Class ServiceAppend which is a Service that can be put in the collection
 * server.
 */
public class ServiceAppend implements ServiceInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -780991566430964466L;

	/** The service name. */
	private String serviceName;

	/**
	 * Instantiates a new service append.
	 * 
	 * @param name
	 *            the name
	 */
	public ServiceAppend(String name) {
		serviceName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see client.interfaces.ServiceInterface#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return serviceName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see client.interfaces.ServiceInterface#executeService(java.util.List)
	 */
	@Override
	public List<Object> executeService(List<Object> args) {
		List<Object> res = new ArrayList<>();

		for (Object o : args) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> list = (List<Object>) o;

				for (Object o2 : list)
					res.add(o2);

			} catch (ClassCastException e) {
				System.out.println("Can't cast into List<Object>");
			}
		}

		return res;
	}

}
