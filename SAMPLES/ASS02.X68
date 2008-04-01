*       ASS02.x68
*       Author : Greg Colley
*       Date   : 13/11/198

*       Program Description :
*       This program will write the byte 88 decimal into D1, add the contents
*       of byte location 2000 hex to it, subtract the byte 18 decimal and write
*       the result into D2 as a byte.

*       Definitions used in this code.

        ORG     $1000           Start of code location.

*       Main program starts here.
        MOVE.B  #88,D1          Move the byte 88 decimal to D1
        ADD.B   $2000,D1        Add the contents of address 2000 hex to D1
        SUB.B   #18,D1          Subtract the byte 18 decimal
        MOVE.B  D1,D2           Move the contents of D1 to D2 (puts the result into D2)           
        

        STOP    #$2700
        END     $1000           End of program.
