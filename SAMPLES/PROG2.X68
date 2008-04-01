**       Prog2.x68
*       By Greg Colley
*       This program wil add the contents of word address 2020
*       hex and 2030 hex together, and add the number 1234 hex to the 
*       result, and put the result in word location 2040 hex

*       Location Declarations
LOC1    EQU     $2020
LOC2    EQU     $2030


RESULT  EQU     $2040

        ORG     $1000

*Main Program
        MOVE.W  LOC1,D0         Move location 2020 HEX to data address 0
        ADD.W   LOC2,D0         Adds location 2030 HEX to data address 0
        ADD.W   #NUM1,D0        Adds 1234 HEX to the data address 0
        MOVE.W  D0,RESULT       Write the sum to memory
        STOP    #$2700          Stop the program

        END     $1000           End of assembly



