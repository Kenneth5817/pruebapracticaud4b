package org.iesvdm.sudoku;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assert;
import org.assertj.core.api.IntegerAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SudokuTest {

    @Test
    void failTest() {
        Sudoku sudoku = new Sudoku();
        sudoku.fillBoardBasedInCluesRandomlySolvable();
        //sudoku.fillBoardBasedInCluesRandomly();
        sudoku.printBoard();
    }

    @Test
    void getNumClues(){
        //given
        getBoard();
        //when
        getNumClues();
        //then: Nos aseguramos de quew sean 63;
        assertThat(63);
    }
    @Test
    void setNumCLues(){
        //given
        int NumClues=63;
        int nuevoNumClues= 34;
        assertThat(NumClues != nuevoNumClues);
    }

    @Test
    void getBoard(){
        //given
        int [][] board= new int [9][9];
        int [][] newBoard= new int[9][9];
        //when ->PAra cada posicion del array bidimensional
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length;j++);
            //Imprimimos la pantalla
            printBoard();
        }

        //Nos aseguramos con el assert que sean iguales las longitudes de las tablas
        //Assertions.assertThat(board.length, newBoard.length);
    }

    @Test
    void setBoard(){

        int [][] board= new int[63][9];
        int [][] newBoard= new int [3][9];

        Arrays.copyOf(board, newBoard.length);
    }

    @Test
    void fillBoardRandomly(){

        //given
        int [][] board= new int[3][3];
        //then  //Yo espero que esté entre los valores 0 y 20 por ej
        for (int i=0; i<20; i++) {
            getBoard();
        }
        //ESpero que funcione al ser menor la longitud de la tabla,  pero está fallando
        assertFalse(board.length-1> board.length+2);
    }

    @Test
    void fillBoardBasedInCluesRandmoly(){

        //given
        final Integer i=9;
        final Integer j=9;
        getNumClues();

        //then when DAdo un arrray con valores ver la tabla
        final Integer [][] board= new Integer[i][j];
        final Integer[][] newBoard={};
        //Actualizamos la tabla con los valores y lo mostramos por pantalla
        setBoard();
        getBoard();

    }

    @Test
    void fillBoardSolvable(){
        //given
        //Creamos el array para acceder a la posicion que se encuentre en la tabla
        final Integer[][] tbl1= new Integer[2][3];
        //ACtualizamos la tabla y las claves
        setBoard();
        setNumCLues();
        //When (Nos aseguramos se que sea correcto), a unas malas muestra mesnaje de fallo
        Assertions.assertTrue(true, "NO se ha guardado en la tabla");
    }


    @Test
    void copyBoard(){
        //given
        //Dado los valores de la tabla
        final Integer[][] tabla=[9][9];
        //then (que cogemos)
        getBoard();
        //Creamos una nueva tabla
        final Integer[][] tablaNueva=new Integer[2][3];

        //Cogemos ambos valores y los añadimos a la nueva tabla.
        Arrays.copyOf(tabla, tablaNueva);
    }
    @Test
    void fillBoardUnsolvable(){
        //given
        //Creamos el array para acceder a la posicion, en este caso será incorrecta aasi que mostrará mensaje de fallo
        final Integer[][] tbl1= new Integer[52][23];
        //ACtualizamos la tabla
        setBoard();
        setNumCLues();
        //When (Nos aseguramos de que no se pueda acceder. SI se puede, mostramos mensaje)
        Assertions.fail("se ha guardado en la tabla");
    }

    @Test
    void putNumberInBoard(){

        //given añadimos el numero
        final int number= 3;
        final Integer[][] tabla= new Integer[9][9];
        final Integer[][] newTabla= new Integer[9][9];
        //HAcemos una copia de la tabla
        //Arrays.copyOf(tabla.length, newTabla);
        //NOs aseguramos de que sea true, el metodo en este caso no creo que sea correcto
        Assertions.assertTrue(tabla[tabla.length-1]), newTabla);

    }

    @Test
    void printBoard(){
        //given PARTIENDO de que tengo un array bidimensioanl board
        final String[][]board = new String[9][9];
        //when  Cuando obtengamos la tabla
        getBoard();
        //then Imprimimos por pantalla
        for( int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++){
                System.out.println(board);
            }
        }

    }

    @Test
    void isNumbewrinRow(){
        final Integer[][] Tabla= new Integer[3][5];
        //given (Dado el numero y fila
        final int num=3;
        final int fila=5;
        //when cuando tenemos la tabla
        getBoard();
        //NOs aseguramos de que busque las posiciones 3 y 5 de la tabla
        //tabla no me lo coge
        Assertions.assertTrue(tabla.IntegerAssert(3,5),"NO se ha encontrado");
    }

    @Test
    void isNumberINColumn(){
        //given DAdo el numero y el esperado
        int num= (3);
        int expected= (5);
        //when-then
        //NOs aseguramos de que sea iguales el num y el expected
        Assertions.assertTrue(num, expected);
    }

    @Test
    void isNumberInBox() {
        //given DAdo el numero y el esperado
        int num1= (3);
        int box= (5);
        //when-then
        //NOs aseguramos de que este en el box el num1
        Assertions.assertTrue(isNumberInBox(num1));
    }

    @Test
    void isValidPlacement(){
        //given when then
        //Comprobamos que sea correcto
        final String[][] place= new String[3][5];
        Assertions.assertTrue(true);
    }

    @Test
    void solveBoard(){
        //given
        //Creamos el array para acceder a la posicion
        final Integer[][] tbl1= new Integer[2][3];
        //ACtualizamos la tabla
        setBoard();
        setNumCLues();
        //When (Nos aseguramos se que sea correcto)
        Assertions.assertTrue(true, "NO se ha guardado en la tabla");
    }
}
