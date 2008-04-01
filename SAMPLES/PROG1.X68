*       Prog1.x68
*       By Greg Colley
*       This program wil add the number 4321 hex to the word location
*       at 2000 hex and save the result in word location 2010 hex.


*       Location Declarations
NUM1    EQU     $4321
LOC1    EQU     $2000

RESULT  EQU     $2010

        ORG     $1000

*Main Program
        MOVE.W  #NUM1,D0         Move 4321 HEX to data address 0
        ADD.W   LOC1,D0         Adds location 2000 to data address 0
        MOVE.W  D0,RESULT       Write the sum to memory
        STOP    #$2700          Stop the program

        END     $1000           End of assembly



