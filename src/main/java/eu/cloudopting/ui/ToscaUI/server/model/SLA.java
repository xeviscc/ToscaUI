package eu.cloudopting.ui.ToscaUI.server.model;

import java.io.Serializable;

/**
 * 
 * @author xeviscc
 *
 */
public class SLA implements Serializable {
	
	private static final long serialVersionUID = -5165489227612516594L;
	
	private String id;
	private String numCpus;
	private String memory;
	private String price;
	private String disk;
	private String chosen;
	
	public String getNumCpus() {
		return numCpus;
	}

	public void setNumCpus(String numCpus) {
		this.numCpus = numCpus;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getChosen() {
		return chosen;
	}

	public void setChosen(String chosen) {
		this.chosen = chosen;
	}

	@Override
	public String toString() {
		return "numCpus " +  this.numCpus
				+ ", memory " + this.memory
				+ ", price " + this.price
				+ ", disk " + this.disk 
				+ ", chosen " + this.chosen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
