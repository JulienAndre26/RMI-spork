package client.entities;

import java.util.List;

import client.interfaces.ServiceInterface;

/**
 * The Class ServiceSum which is a Service that can be put in the collection
 * server.
 */
public class ServiceSum implements ServiceInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4284595140873870496L;

	/** The service name. */
	private String serviceName;

	/**
	 * Instantiates a new service sum.
	 * 
	 * @param name
	 *            the name
	 */
	public ServiceSum(String name) {
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
	public Integer executeService(List<Object> args) {
		Integer res = 0;
		for (Object o : args) {
			Integer i = 0;

			try {
				i = (Integer) o;
			} catch (ClassCastException e) {
				System.out.println("Can't cast " + o + " into integer");
			}

			res += i;
		}

		return res;

	}

}
