package client.entities;

import client.interfaces.DataInterface;

/**
 * The Class DataInteger which is a Data that can be put in the server
 * collection.
 */
public class DataInteger implements DataInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -422685837211604429L;

	/** The data name. */
	private String dataName;

	/** The data value. */
	private int integer;

	/**
	 * Instantiates a new data integer.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public DataInteger(String name, int value) {
		dataName = name;
		integer = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see client.interfaces.DataInterface#getDataName()
	 */
	@Override
	public String getDataName() {
		return dataName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see client.interfaces.DataInterface#getValue()
	 */
	@Override
	public Integer getValue() {
		return integer;
	}

}
