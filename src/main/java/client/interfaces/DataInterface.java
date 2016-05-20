package client.interfaces;

import java.io.Serializable;

/**
 * The Interface DataInterface which defines the Data model.
 */
public interface DataInterface extends Serializable {

	/**
	 * Gets the data name.
	 * 
	 * @return the data name
	 */
	public String getDataName();

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue();

}
