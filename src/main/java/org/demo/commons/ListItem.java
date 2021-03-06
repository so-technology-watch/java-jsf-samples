/*
 * Created on 2017-01-30 ( Time 10:21:49 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */
package org.demo.commons;

/**
 * List item interface <br>
 * This interface defines an item identified by a "value" and having a "label".<br>
 * This kind of item is designed to be used in a list populating a listbox or a combobox in a GUI. <br>
 * 
 * @author Telosys
 *
 */
public interface ListItem {

	/**
	 * Returns the item's value 
	 * @return
	 */
	public String getValue();
	
	/**
	 * Returns the item's label to be displayed 
	 * @return
	 */
	public String getLabel();
}
