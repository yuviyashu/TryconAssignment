/**
 * 
 */
package com.assgnmnt.aud;

/**
 * @author ykrishnamurthy
 * This class represents the song
 */
public class Song {
	//It represents the name of the song
	private String name;
	
	public Song(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName()+ " Playing";
	}
		
}
