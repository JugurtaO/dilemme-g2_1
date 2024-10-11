package fr.uga.l3miage.pc.prisonersdilemma;

import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




class UtilsTests {
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	private final InputStream standardIn = System.in;

	@BeforeEach
	 void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	 void tearDown() {
		System.setOut(standardOut);
		System.setIn(standardIn);
	}
	@Test
	void chooseStrategyOK() {
		String input = "5\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		int result = Utils.chooseStrategy("Jugurta");
		assertEquals(5, result, "Should return the valid strategy number entered");
	}

	@Test
	 void testInvalidThenValidStrategyChoice() {
		String input = "0\n19\n10\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		int result = Utils.chooseStrategy("Bob");
		assertEquals(10, result, "Should return the valid strategy number after invalid attempts");
	}

	@Test
	 void getLastTwoTurnsOK(){
	List<Tour> history= new ArrayList<>();
	Tour t1=new Tour(1,true,true);
	Tour t2=new Tour(2,false,true);
	Tour t3=new Tour(3,false,true);
	history.add(t1);
	history.add(t2);
	history.add(t3);

	Tour[] expectedResult=Utils.getLastTwoTurns(history);
	assertEquals(2, expectedResult.length, "Le tableau devrait contenir exactement 2 tours");
	assertEquals(t2, expectedResult[0], "Le premier élément devrait être l'avant-dernier tour");
	assertEquals(t3, expectedResult[1], "Le deuxième élément devrait être le dernier tour");


	}
	@Test
	 void askPlayer1ForNbToursOK(){
		String input = "5\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		int result = Utils.askPlayer1ForNbTours();
		assertEquals(5, result, "Should return the valid strategy number entered");
	}
	@Test
	 void askPlayer1ForNbToursNotOK(){
		String input = "-1\n5\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		int result = Utils.askPlayer1ForNbTours();
		assertEquals(5, result, "Should return the valid strategy number after invalid attempts");
	}

	@Test
	 void calculateScoresOK1(){
		Player p1=new Player("Alex",null);
		Player p2=new Player("Bob",null);
		GameEncounter gameEncounter=new GameEncounter(5,p1,p2);
		p1.setGameEncounter(gameEncounter);
		p2.setGameEncounter(gameEncounter);

		Utils.calculateScores(p1,true,p2,true);
		assertEquals(3,p1.getScore());
		assertEquals(3,p2.getScore());

	}
	@Test
	 void calculateScoresOK2(){
		Player p1=new Player("Alex",null);
		Player p2=new Player("Bob",null);
		GameEncounter gameEncounter=new GameEncounter(5,p1,p2);
		p1.setGameEncounter(gameEncounter);
		p2.setGameEncounter(gameEncounter);

		Utils.calculateScores(p1,false,p2,true);
		assertEquals(5,p1.getScore());
		assertEquals(0,p2.getScore());

	}

	@Test
	 void calculateScoresOK3(){
		Player p1=new Player("Alex",null);
		Player p2=new Player("Bob",null);
		GameEncounter gameEncounter=new GameEncounter(5,p1,p2);
		p1.setGameEncounter(gameEncounter);
		p2.setGameEncounter(gameEncounter);

		Utils.calculateScores(p1,false,p2,false);
		assertEquals(1,p1.getScore());
		assertEquals(1,p2.getScore());

	}

	@Test
	void playerLeaveGameHandlerOK1(){
		Player p1=new Player("Bob",null);
		Player p2=new Player("Alex",null);
		GameEncounter gameEncounter=new GameEncounter(5,p1,p2);
		p1.setGameEncounter(gameEncounter);

		String input = "no\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		Utils.playerLeaveGameHandler(p1);
        assertFalse(p1.getAiMode());
	}


}
