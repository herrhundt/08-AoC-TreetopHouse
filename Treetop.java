import java.io.*;
import java.util.*;

public class Treetop
{
  //static String filename = "input-sample.txt"; //for testing purposes 
  static String filename = "input.txt"; //the original input  
  static String []input;
  static int[][] trees; 
  static int arraySize =0; 
  
  public static void solvePuzzlePartTwo() throws IOException
  {
      int maxScore =0;  
      int tempScore; 
      readInputIntoArray(); 
      buildTreeMap(); 
      printTreeMap();
      for(int i=0; i<input.length;i++)
      {
          for(int j=0; j<input.length;j++)
          {
            tempScore = countScenicScore(i,j); 
            
              if (tempScore >maxScore) maxScore = tempScore; //save highscore   
          }
      }
      System.out.println("Highscore: "+maxScore);
  }
  
  public static void solvePuzzlePartOne() throws IOException
  {
      int cntVisibleTrees=0; 
      readInputIntoArray(); 
      buildTreeMap(); 
      printTreeMap();
      for(int i=0; i<input.length;i++)
      {
          for(int j=0; j<input.length;j++)
          {
            if (isVisible(i,j)) cntVisibleTrees++;   
          }
      }
      System.out.println("Sum visible trees: "+cntVisibleTrees);
  }
  
  public static void buildTreeMap()
  {
      trees = new int[arraySize][arraySize]; 
      String s=""; 
      for(int i=0; i<input.length;i++)
      {
          for(int j=0;j<input[i].length();j++)
          {
              s="";
              s += input[i].charAt(j); 
              trees[i][j] = Integer.parseInt(s);
          }
      }
  }
  
  public static int countScenicScore(int pRow, int pCol)
  {
      int a=0,b=0,c=0,d=0;  
      
      for(int i=pRow-1; i>=0;i--) //check to left  |<-x
      {
        if(trees[i][pCol] >= trees[pRow][pCol]) //stopping tree? 
        {
            a++; 
            break; 
        }
        
        if(trees[i][pCol] < trees[pRow][pCol]) a++;     
      }
      
      for(int i=pRow+1; i<arraySize;i++) //check to right  |  x->|
      {
        if(trees[i][pCol] >= trees[pRow][pCol])
        {
            b++; 
            break; 
        }
        
        if(trees[i][pCol] < trees[pRow][pCol]) b++;     
      }
      
      for(int j=pCol-1; j>=0;j--) //check up 
      {
        if(trees[pRow][j] >= trees[pRow][pCol])
        {
            c++; 
            break; 
        }
        
        if(trees[pRow][j] < trees[pRow][pCol]) c++;     
      }
      
      for(int j=pCol+1; j<arraySize;j++) //check down 
      {
        if(trees[pRow][j] >= trees[pRow][pCol])
        {
            d++; 
            break; 
        }
        
        if(trees[pRow][j] < trees[pRow][pCol]) d++;     
      } 
      return (a*b*c*d); 
  }
  
  public static boolean isVisible(int pRow, int pCol)
  {
      //check rows from left 
      int cntVisibility = 0;  
      
      cntVisibility++; // visibility annehmen 
      for(int i=0; i<pRow;i++)
      {
        if(trees[i][pCol] >= trees[pRow][pCol]) 
        { 
            cntVisibility--; //keine visibility 
            break;
        }    
      }
      
      //check rows from right 
      cntVisibility++; // visibility annehmen 
      for(int i=pRow+1; i<arraySize;i++)
      {
        if(trees[i][pCol] >= trees[pRow][pCol]) 
        { 
            cntVisibility--; //keine visibility 
            break;// stop loop, one bigger tree is enough to stop visibility 
        }    
      }
      
      //check cols from top 
      cntVisibility++; // visibility annehmen 
      for(int j=0; j<pCol;j++)
      {
        if(trees[pRow][j] >= trees[pRow][pCol]) 
        { 
            cntVisibility--; //keine visibility 
            break;
        }    
      }
      
      //check cols to bottom 
      cntVisibility++; // visibility annehmen 
      for(int j=pCol+1; j<arraySize;j++)
      {
        if(trees[pRow][j] >= trees[pRow][pCol]) 
        { 
            cntVisibility--; //keine visibility 
            break;
        }    
      }
      System.out.println("Tree at ["+pRow+"]["+pCol+"] with value "+trees[pRow][pCol]+" visibility: "+cntVisibility);
      if(cntVisibility>0) return true; else return false; 
  }

  
  public static void printTreeMap()
  {
     for(int i=0; i<input.length;i++)
     {
         for(int j=0; j<input.length;j++)
         {
             System.out.print(trees[i][j] +" ");
         }
         System.out.println();
     } 
  }
  
  public static void solvePuzzle1() throws IOException
   {
     
     
   }
  
   
    /**
     * Die Methode liest den Input der Textdatei in ein Array ein. 
     */
    public static void readInputIntoArray() throws IOException
    {
    arraySize =0; 
    try
    {
        arraySize = getInputLength(); 
    }
    catch (IOException e) 
    {
        System.out.println("Es ist ein Fehler beim Einlesen passiert!"); 
        System.exit(1); //Programm beenden
    }
    input = new String[arraySize]; //Erstelle leeres Array mit der Anzahl 
    
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);      
    String zeile = "";
    
    for(int i=0; i<arraySize;i++)
    {
         input[i] = br.readLine();
    }
      br.close();
      //array should be read correctly  
    }  
    
    /**
     * Methode bestimmt die Länge der Texteingabe 
     */
    public static int getInputLength() throws IOException
    {
     int size = 0;  
     FileReader fr = new FileReader(filename);
     BufferedReader br = new BufferedReader(fr);  
      //Bestimme die Anzahl der Werte! 
     String zeile = "";
     while( (zeile = br.readLine()) != null )
     {
        System.out.println(zeile);
        size++; 
      }
      br.close();
    return size; 
    }    


}
