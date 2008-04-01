*       UPDATE.X68
*       Author : Greg Colley
*       Puts $00 in byte location $1900, $01 in location $1901 etc.

*       Definitions used in this code.
FIRST   EQU     $1900           First Location. for the address to goto
BYTES   EQU     $100            Number of locations to be changed.
SECOND  EQU     $00             Second Location for the address which have came from.

        ORG     $1000           Start of code location.

*       Main program starts here.
        MOVEA.L #FIRST,A0       Pointer to first location in memory.
        MOVE.W  #BYTES,D0       Set up the counter.
        MOVE.L  #SECOND,D1      Move $00 to the data register D1.

NEXT    MOVE.B  D1,(A0)         Move the contents of the data register to the
* 					  contents of memory pointed to by A0.
        ADDA.L  #1,A0           Increment 'first' memory pointer.
        ADD.B   #1,D1           Increment 'second' data register.
        SUB.W   #1,D0           Decrement counter.
        BNE     NEXT            Loop back if not finished.
        STOP    #$2700          stop.

        END     $1000           End of program.

