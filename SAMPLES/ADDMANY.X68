*       Addmany.x68
*       Author : Greg Colley
*       Add the contents of Word locations $2000, $2002, $2004, $2006 and 
*       Stores the result in word locations $200A.

*       Definitions used in this code.
FIRST   EQU     $2000           First Location.
BYTES   EQU     $5              Number of locations to be changed.

        ORG     $1000           Start of code location.
 
*       Main program starts here.
        MOVEA.L #FIRST,A0       Pointer to first location in memory.
        MOVE.W  #BYTES,D0       Set up the counter.
        MOVE.W  #$0,D1          Set Data Register 1 to Equal 0

NEXT    ADD.W   (A0),D1         Add the Contens of A0 to Data Register 1.
        ADDA.L  #2,A0           Increment memory pointer.
        SUB.W   #1,D0           Decrement counter.
        BNE     NEXT            Loop back if not finished.
        MOVE.W  D1,$200A        Move the Result to word location $200A
        STOP    #$2700          stop.

        END     $1000           End of program.

