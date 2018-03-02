package com.wyn.automation.base;

import org.apache.log4j.Logger;

public abstract class CustomLoadableComponent<T extends CustomLoadableComponent<T>>  {

	private static Logger Log = Logger.getLogger(CustomLoadableComponent.class.getName());

	@SuppressWarnings("unchecked")
	public T get() {
		try {
			isLoaded();
			return (T) this;	
		} catch (Error e) {
			Log.error("Error encountered during page load: " + e.getMessage());
			load();
		}

		isLoaded();

		return (T) this;
	}

	protected abstract void load();

	protected abstract void isLoaded() throws Error;
	

}