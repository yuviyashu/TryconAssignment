/**
 * 
 */
package com.assgnmnt.aud;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author ykrishnamurthy
 * Main calss which implements all the fucntionality like play random, play next, play prev etc
 */
public class Main implements Runnable {
	private static ArrayList<Song> sngLst = new ArrayList<Song>(); // It contains the list of songs
	private static ArrayList<Integer> playdLst = new ArrayList<Integer>(); // It has the index of the songs which is played atleast once
	private static volatile int i=0; // It helps to maintain the current song index which is being played also helps in play next and play prev song functionalities
	Thread mainThr = null; // Thread object which helps in playing the song in other thread.
	
	public static void main(String[] args) {	
		/*
		 * Initialising the songs list
		 */
		for(int i=0;i<10;i++) {
			sngLst.add(new Song("Song "+i));
		}
		//Adding the 0th song to already played songs list
		playdLst.add(i);
		/*
		 * Creating the main class object thread object 
		 */
		Main main = new Main();
		Thread mainThr = new Thread(main);
		mainThr.start();
		/*
		 * Asking input repeatedly
		 */
		Scanner scan =  new Scanner(System.in);
		while(true) {
			System.out.println("Please provide any option below\n");
			System.out.println("Press 1 to play in random order\n");
			System.out.println("Press 2 to play next\n");
			System.out.println("Press 3 to play previous\n");
			System.out.println("Press 4 to exit\n");
			
			int inp = scan.nextInt();
			
			
			switch(inp) {
				case 1: playRandomSong();
						break;
				case 2: playNextSong();
						break;
				case 3: playPrevSong();
						break;
				case 4: scan.close();
						System.out.println("Exited Successfully..\n");
						System.exit(1);
				default:System.err.println("Improper Input");
			}
		}
	}
	
	/**
	 * Method to play the songs in random order
	 */
	public static void playRandomSong() {
		try {
			int rndInd = getRandomNumberInRange(0,sngLst.size()-1);
			if(playdLst.contains(rndInd) && playdLst.size()<sngLst.size()) {
				playRandomSong();
			}else if(playdLst.size()<sngLst.size()) {
				i = rndInd;
				playdLst.add(rndInd);
			}else {
				System.err.println("All songs are played\n No Unplayed songs");
			}
		}catch(StackOverflowError e) {
			System.err.println("Stack Overflow Error occured");
			return;
			
		}
	}
	
	/**
	 * Method to play the next song
	 */
	public static void playNextSong() {
		//If it is playing the last song then next song is not available
		if(i<sngLst.size()-1) {
			i++;
		}else {
			System.err.println("Next Song not available\n\n");
		}
	}
	
	/**
	 * Method to play the previous song
	 */
	public static void playPrevSong() {
		//If is playing the first song then previous song is not available
		if(i>0) {
			i--;
		}else {
			System.err.println("Prev Song not available\n");
		}
		
	}
	
	/**
	 * Method to get the random integer between the provided number range
	 * @param min
	 * @param max
	 * @return
	 */
	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Below run method which runs in separate thread other than
	 * main thread helps on playing the song
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(sngLst.get(i));
		}
	}

}
