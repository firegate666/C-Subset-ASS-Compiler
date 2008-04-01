*       Prog4.x68
*       By Greg Colley
*       This program will subtract the number 1234 hex from the contents
*       of word location 2070 hex, putting the result into word
*       location 2080 hex.

*       Location Declarations

LOC1    EQU     $2070
    
RESULT  EQU     $2080

        ORG     $1000

*       Main Program
        MOVE.W  #$1234,D0       *Move 1234 HEX to data address 0
        SUB.W   LOC1,D0         *Take away location 2070 from data address 0
        MOVE.W  D0,RESULT       *Write the sum to memory
        STOP    #$2700          *Stop the program

        END     $1000           *End of assembly



