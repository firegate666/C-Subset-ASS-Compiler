*       LOOP4.x68
*       Author : Greg Colley
*       Date   : 04/11/1998

*       Definitions used in this code.
FIRST   EQU     $3000           First Location.
BYTES   EQU     $2000           Number of locations to be changed.
VALUE   EQU     1               Value to be written to memory.

        ORG     $1000           Start of code location.

*       Main program starts here.
        MOVEA.L #FIRST,A0       Pointer to first location in memory.
        MOVE.W  #BYTES,D0       Set up the counter.
NEXT    ADD.B   #VALUE,(A0)+    Write the value to current memory location. and increament memory pointer.
        BCS     ENDBIT          Branch to the end of the program is Carry Set.
        SUB.W   #1,D0           Decrement counter.
        BNE     NEXT            Loop back if not finished.
ENDBIT  STOP    #$2700          ...else stop.

        END     $1000           End of program.

