*       Prog3.x68
*       By Greg Colley
*       This program will exchange the contents of word locations 2050 hex
*       and 2060.

*       Location Declarations
LOC1    EQU     $2050
LOC2    EQU     $2060

        ORG     $1000

*Main Program
        MOVE.W  LOC1,D0         Move location 2050 HEX to data address 0
        MOVE.W  LOC2,D1         Move location 2060 HEX to data address 1           
        MOVE.W  D0,LOC2         Move data address contents to location 2060
        MOVE.W  D1,LOC1         Move data address contents to location 2050
        STOP    #$2700          Stop the program

        END     $1000           End of assembly



