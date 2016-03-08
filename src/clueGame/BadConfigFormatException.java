package clueGame;

public class BadConfigFormatException extends Exception
{
      //Parameterless Constructor
      public BadConfigFormatException() {}

      //Constructor that accepts a message
      public BadConfigFormatException(String message)
      {
         super(message);
      }
 }
