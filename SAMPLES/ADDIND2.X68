*       Addind2.x68
*       Write the byte value $BF to locations $2800 to $28FF

*       Definitions used in this code.
FIRST   EQU     $2800           First Location.
BYTES   EQU     $100            Number of locations to be changed.
VALUE   EQU     $BF             Value to be written to memory.

        ORG     $1000           Start of code location.

*       Main program starts here.
        MOVEA.L #FIRST,A0       Pointer to first location in memory.
        MOVE.W  #BYTES,D0       Set up the counter.
NEXT    MOVE.B  #VALUE,(A0)     Write the value to current memory location.
        ADDA.L  #1,A0           Increment memory pointer.
        SUB.W   #1,D0           Decrement counter.
        BNE     NEXT            Loop back if not finished.
        STOP    #$2700          ...else stop.

        END     $1000           End of program.

