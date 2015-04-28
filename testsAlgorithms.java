
	public static void main(String[] args) {

		GameRules r = new ReversiRules();
		Counter valid;
		int row, column;

		// Test 1 
		row = 3;
		column = 3;
		Board b = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				if ((i % 2 == 1 && j % 2 == 1)) b.setPosition(i, j, Counter.BLACK);
				else 		  b.setPosition(i, j, Counter.WHITE);
			}
		}
		b.setPosition(2, 2, Counter.BLACK);
		
		b.printBoard();
		ReversiMove m = new ReversiMove(3, 3, b.getPosition(2, 2));
		valid =  r.winningMove(m, b); 

		// Test 2 
		row = 6;
		column = 6;
		Board b2 = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				if ((i % 2 == 1 && j % 2 == 1)) b2.setPosition(i, j, Counter.BLACK);
				else 		  b2.setPosition(i, j, Counter.WHITE);
			}
		}
		b2.setPosition(2, 2, Counter.BLACK);
		
		b2.printBoard();
		ReversiMove m2 = new ReversiMove(3, 3, b2.getPosition(2, 2));
		valid =  r.winningMove(m2, b2); 

	}
	
	
	
	
	
	
	/** 
	 * 	DESCOMENTA ESTO SI QUIERES HACER EL
	 *  TEST PARA COMPROBAR LOS ELEMENTOS A LA IZQUIERDA
	 * 
	public static void main(String[] args) {
		
		// Test  
		GameRules r = new ReversiRules();
		Board b = new Board(3, 1);
		Counter valid;
		
		for (int i = 1; i <= 3; i++) {
			if (i % 2==1) b.setPosition(i, 1, Counter.BLACK);
			else 		  b.setPosition(i, 1, Counter.WHITE);
		}
		
		b.printBoard();
		ReversiMove m = new ReversiMove(3, 1, b.getPosition(3, 1));
		valid =  r.winningMove(m, b);
		

		// Test 2 
		Board b2 = new Board(2, 1); 
		
		for (int i = 1; i <= 2; i++) {
			if (i % 2==1) b2.setPosition(i, 1, Counter.BLACK);
			else 		  b2.setPosition(i, 1, Counter.WHITE);
		}
		
		b2.printBoard();
		ReversiMove m2 = new ReversiMove(2, 1, b2.getPosition(2, 1));
		valid =  r.winningMove(m2, b2);

		// Test 4 
		Board b3 = new Board(1, 1); 
		
		for (int i = 1; i <= 1; i++) {
			if (i % 2==1) b3.setPosition(i, 1, Counter.BLACK);
			else 		  b3.setPosition(i, 1, Counter.WHITE);
		}
		
		b3.printBoard();
		ReversiMove m3 = new ReversiMove(1, 1, b3.getPosition(1, 1));
		valid =  r.winningMove(m3, b3);
		
		// Test 6 
		Board b4 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i ==1) b4.setPosition(i, 1, Counter.BLACK);
			else if (i == 6) b4.setPosition(i, 1, Counter.BLACK);
			else 		  b4.setPosition(i, 1, Counter.WHITE);
		}
		
		b4.printBoard();
		ReversiMove m4 = new ReversiMove(6, 1, b4.getPosition(6, 1));
		valid =  r.winningMove(m4, b4);

		// Test 7 
		Board b5 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i == 6) b5.setPosition(i, 1, Counter.BLACK);
			else 		  b5.setPosition(i, 1, Counter.WHITE);
		}
		
		b5.printBoard();
		ReversiMove m5 = new ReversiMove(6, 1, b5.getPosition(6, 1));
		valid =  r.winningMove(m5, b5);
		

		// Test 8  
		Board b8 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i ==1) b8.setPosition(i, 1, Counter.WHITE);
			else if (i ==2) b8.setPosition(i, 1, Counter.BLACK);
			else if (i == 6) b8.setPosition(i, 1, Counter.BLACK);
			else 		  b8.setPosition(i, 1, Counter.WHITE);
		}
		
		b8.printBoard();
		ReversiMove m8 = new ReversiMove(6, 1, b8.getPosition(6, 1));
		valid =  r.winningMove(m8, b8);
	}
	
	*/
	
	
	
	
	 
	
	
	
	
	
	
	/** 
	 * 	ESTOS TESTS PARA PROBAR SI LOS ALGORITMOS A LA DERECHA
	 * 	FUNCTIÓN
	 *  
	 *  
	public static void main(String[] args) {
		
		// Test 1 
		GameRules r = new ReversiRules();
		Board b = new Board(3, 1);
		Counter valid;
		
		for (int i = 1; i <= 3; i++) {
			if (i % 2==1) b.setPosition(i, 1, Counter.BLACK);
			else 		  b.setPosition(i, 1, Counter.WHITE);
		}
		
		b.printBoard();
		ReversiMove m = new ReversiMove(1, 1, b.getPosition(1, 1));
		valid =  r.winningMove(m, b);
		

		// Test 2 
		Board b2 = new Board(2, 1); 
		
		for (int i = 1; i <= 2; i++) {
			if (i % 2==1) b2.setPosition(i, 1, Counter.BLACK);
			else 		  b2.setPosition(i, 1, Counter.WHITE);
		}
		
		b2.printBoard();
		ReversiMove m2 = new ReversiMove(1, 1, b2.getPosition(1, 1));
		valid =  r.winningMove(m2, b2);

		// Test 3 
		Board b3 = new Board(1, 1); 
		
		for (int i = 1; i <= 1; i++) {
			if (i % 2==1) b3.setPosition(i, 1, Counter.BLACK);
			else 		  b3.setPosition(i, 1, Counter.WHITE);
		}
		
		b3.printBoard();
		ReversiMove m3 = new ReversiMove(1, 1, b3.getPosition(1, 1));
		valid =  r.winningMove(m3, b3);
		
		// Test 4 
		Board b4 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i ==1) b4.setPosition(i, 1, Counter.BLACK);
			else if (i == 6) b4.setPosition(i, 1, Counter.BLACK);
			else 		  b4.setPosition(i, 1, Counter.WHITE);
		}
		
		b4.printBoard();
		ReversiMove m4 = new ReversiMove(1, 1, b4.getPosition(1, 1));
		valid =  r.winningMove(m4, b4);

		// Test 5 
		Board b5 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i == 1) b5.setPosition(i, 1, Counter.BLACK);
			else 		  b5.setPosition(i, 1, Counter.WHITE);
		}
		
		b5.printBoard();
		ReversiMove m5 = new ReversiMove(1, 1, b5.getPosition(1, 1));
		valid =  r.winningMove(m5, b5);
		

		// Test 6 
		Board b8 = new Board(6, 1); 
		
		for (int i = 1; i <= 6; i++) {
			if (i ==1) b8.setPosition(i, 1, Counter.WHITE);
			else if (i ==2) b8.setPosition(i, 1, Counter.BLACK);
			else if (i == 6) b8.setPosition(i, 1, Counter.BLACK);
			else 		  b8.setPosition(i, 1, Counter.WHITE);
		}
		
		b8.printBoard();
		ReversiMove m8 = new ReversiMove(2, 1, b8.getPosition(2, 1));
		valid =  r.winningMove(m8, b8);
	}
	*/

	
	
	
	
	
	
	
	
	
	/** 
	 *  TESTS PARA PROBAR ALGORITMOS HACIA ARRIBA
	public static void main(String[] args) {

		GameRules r = new ReversiRules();
		Counter valid;
		int row, column;

		// Test 1 
		row = 3;
		column = 1;
		Board b = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 2==1) b.setPosition(1, i, Counter.BLACK);
			else 		  b.setPosition(1, i, Counter.WHITE);
		}
		
		b.printBoard();
		ReversiMove m = new ReversiMove(column, row, b.getPosition(1, row));
		valid =  r.winningMove(m, b); 

		// Test 2 
		row = 2;
		column = 1;
		Board b2 = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 2==1) b2.setPosition(1, i, Counter.BLACK);
			else 		  b2.setPosition(1, i, Counter.WHITE);
		}
		
		b2.printBoard();
		ReversiMove m2 = new ReversiMove(column, row, b2.getPosition(1, row));
		valid =  r.winningMove(m2, b2); 

		// Test 3 
		row = 3;
		column = 1;
		Board b3 = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 3==1) b3.setPosition(1, i, Counter.BLACK);
			else 		  b3.setPosition(1, i, Counter.WHITE);
		}
		
		b3.printBoard();
		ReversiMove m3 = new ReversiMove(column, row, b3.getPosition(1, row));
		valid =  r.winningMove(m3, b3); 
		
	}
	*/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 
	 *  TESTS PARA PROBAR ALGORITMOS HACIA ABAJO

	public static void main(String[] args) {

		GameRules r = new ReversiRules();
		Counter valid;
		int row, column;

		// Test 1 
		row = 3;
		column = 1;
		Board b = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 2==1) b.setPosition(1, i, Counter.BLACK);
			else 		  b.setPosition(1, i, Counter.WHITE);
		}
		
		b.printBoard();
		ReversiMove m = new ReversiMove(column, 1, b.getPosition(1, row));
		valid =  r.winningMove(m, b); 

		// Test 2 
		row = 2;
		column = 1;
		Board b2 = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 2==1) b2.setPosition(1, i, Counter.BLACK);
			else 		  b2.setPosition(1, i, Counter.WHITE);
		}
		
		b2.printBoard();
		ReversiMove m2 = new ReversiMove(column, 1, b2.getPosition(1, row));
		valid =  r.winningMove(m2, b2); 

		// Test 3 
		row = 3;
		column = 1;
		Board b3 = new Board(column, row);

		for (int i = 1; i <= row; i++) {
			if (i % 3==1) b3.setPosition(1, i, Counter.BLACK);
			else 		  b3.setPosition(1, i, Counter.WHITE);
		}
		
		b3.printBoard();
		ReversiMove m3 = new ReversiMove(column, 1, b3.getPosition(1, row));
		valid =  r.winningMove(m3, b3); 
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	