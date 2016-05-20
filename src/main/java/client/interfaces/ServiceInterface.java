package client.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface ServiceInterface which defines the Service model.
 */
public interface ServiceInterface extends Serializable {

	/**
	 * Gets the service name.
	 * 
	 * @return the service name
	 */
	public String getServiceName();

	/**
	 * Execute service.
	 * 
	 * @param args
	 *            the args
	 * @return the object
	 */
	public Object executeService(List<Object> args);

}
