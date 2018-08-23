package com.test;

class Annoyance extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

class Sneeze extends Annoyance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}


public class Test {

	public static void main(String[] args) throws Exception {
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) {
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}

